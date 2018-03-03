package org.marker.certificate.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.marker.certificate.bean.Certificate;
import org.marker.certificate.bean.Enterprise;
import org.marker.certificate.bean.Food;
import org.marker.certificate.bean.Page;
import org.marker.certificate.bean.ServiceMessage;
import org.marker.certificate.bean.Unit;
import org.marker.certificate.dao.DaoEngine;
import org.marker.certificate.dao.mapper.ObjectMapper.MapperCertificate;
import org.marker.certificate.dao.mapper.ObjectMapper.MapperCertificateForFind;
import org.marker.certificate.dao.mapper.ObjectMapper.MapperCertificateForQueue;
import org.marker.certificate.dao.mapper.ObjectMapper.MapperEnterprise;
import org.marker.certificate.dao.mapper.ObjectMapper.MapperFood;
import org.marker.certificate.dao.mapper.ObjectMapper.MapperUnit;
import org.marker.certificate.service.ICertificateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 证书管理
 * @author marker
 * @version 1.0
 */
public class CertificateServiceImpl implements ICertificateService {
	
	// 日志记录器
	protected Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	// dao
	private DaoEngine dao = new DaoEngine();
	
	
	
	// 保存操作，包含了多条记录的更新
	@Override
	public ServiceMessage save(Certificate cer) {
		String sql = "INSERT INTO `cer_certificate`(code,product,verifymode,stime,etime,enterprise_id,printer) VALUES (?,?,?,?,?,?,0)";  
		Object[] params = new Object[]{
				cer.getCode(),
				cer.getProduct(),
				cer.getVerifyMode(),
				cer.getSendTime(),
				cer.getEffectiveTime(),
				cer.getEnterpriseId()};
		// 取得证书ID
		int cid = dao.updateReturnId(sql, params);
		List<Unit> units = cer.getUnits();
		for(Unit u : units){
			sql = "INSERT INTO `cer_unit`(cid,`desc`) VALUES (?,?)";  
			int uid = dao.updateReturnId(sql, new Object[]{cid,u.getDesc()});
			List<Food> foods = u.getFoods();
			for(Food f : foods){
				sql = "INSERT INTO `cer_unit_food`(cid,uid,name,used) VALUES (?,?,?,?)";
				dao.update(sql, new Object[]{cid, uid, f.getName(), f.getUsed()}); 
			} 
		} 
		return new ServiceMessage(true, "添加成功!"); 
	}

	
	// 更新操作
	@Override
	public ServiceMessage update(Certificate cer) {
		
		// 更新证书信息
		String sql = "update `cer_certificate` set code=?,product=?,verifymode=?,stime=?,etime=?,enterprise_id=? where id=?";  
		Object[] params = new Object[]{
				cer.getCode(),
				cer.getProduct(),
				cer.getVerifyMode(),
				cer.getSendTime(),
				cer.getEffectiveTime(),
				cer.getEnterpriseId(),
				cer.getId()}; 
		dao.update(sql, params); 
		
		
		
		// 删除unit和food
		Connection conn = null;
		try {
			conn = dao.getConnection();
			PreparedStatement ps ;
			
			ps = conn.prepareStatement("delete from cer_unit_food where cid = ?");
			dao.insertParams(ps, cer.getId());// 插入参数 
		    ps.executeUpdate(); ps.close();
		    
			ps = conn.prepareStatement("delete from cer_unit where cid = ?");
			dao.insertParams(ps, cer.getId());// 插入参数 
		    ps.executeUpdate(); ps.close(); 
			
		}catch(Exception e) {
			log.error("queryForObject error!", e);
			return new ServiceMessage(false, "操作失败!");
		}finally{
			try {
				conn.close();
			} catch (SQLException e) { 
				log.error("service deleteById error!", e);
			}
		} 
		
		
		// 插入新的unitfood 
		List<Unit> units = cer.getUnits();
		for(Unit u : units){
			sql = "INSERT INTO `cer_unit`(cid,`desc`) VALUES (?,?)";  
			int uid = dao.updateReturnId(sql, new Object[]{cer.getId(),u.getDesc()});
			List<Food> foods = u.getFoods();
			for(Food f : foods){
				sql = "INSERT INTO `cer_unit_food`(cid,uid,name,used) VALUES (?,?,?,?)";
				dao.update(sql, new Object[]{cer.getId(), uid, f.getName(), f.getUsed()}); 
			} 
		}  
		return new ServiceMessage(true, "修改成功!"); 
	}

	
	// 删除实现
	@Override
	public ServiceMessage deleteById(Serializable cid) {
		Connection conn = null;
		try {
			conn = dao.getConnection();
			 
			PreparedStatement ps;
			ps = conn.prepareStatement("delete from cer_unit_food where cid = ?");
			dao.insertParams(ps, cid);// 插入参数 
		    ps.executeUpdate(); ps.close();
		    
		    
			ps = conn.prepareStatement("delete from cer_unit where cid = ?");
			dao.insertParams(ps, cid);// 插入参数 
		    ps.executeUpdate(); ps.close();
					
			ps = conn.prepareStatement("delete from cer_certificate where id = ?");
			dao.insertParams(ps, cid);// 插入参数 
		    ps.executeUpdate(); ps.close();
			
			return new ServiceMessage(true, "添加成功!"); 
		     
		} catch (SQLException e) { 
			log.error("deleteById error!", e);
			return new ServiceMessage(false, "操作失败!");
		}finally{
			try {
				conn.close();
			} catch (SQLException e) { 
				log.error("service deleteById error!", e);
			}
		} 
	}

	
	
	// 分页查询
	@Override
	public Page<Certificate> queryByPage(int currentPageNo, int pageSize,
			Map<String, Object> condition) {

		StringBuilder sql = new StringBuilder("select c.*,e.name ename,p.name pname,city.name cname from cer_certificate c "
				+ "left join cer_enterprise e on e.id=c.enterprise_id "
				+ "join cer_city city on city.id=e.city_id "
				+ "join cer_province p on p.id=e.province_id where 1=1 ");
		StringBuilder count = new StringBuilder("select count(*) from cer_certificate c join cer_enterprise e on e.id=c.enterprise_id join cer_city city on city.id=e.city_id join cer_province p on p.id=e.province_id where 1=1 "
				+ "");
		List<Certificate> list ;
		
		 /*
		  *     全部信息：all
	 *      证书编号：ccode
	 *      企业名称：ename 
	 *      企业地址：ecity 
		  */
		List<Object> params = new ArrayList<Object>();
		if(condition != null){
			String ccode = (String) condition.get("ccode");
			String ename = (String) condition.get("ename");  
			if(ccode != null){  
				sql.append(" and c.code like ?");params.add('%'+ccode+'%');
				count.append(" and c.code like ?");
			}else if(ename != null){ 
				sql.append(" and e.name like ?"); params.add('%'+ename+'%');
				count.append(" and e.name like ?");
			}
		} 
		
		int c = dao.queryForInt(count.toString(), params.toArray());
		
		Page<Certificate> page = new Page<Certificate>();
		page.setCurrentPageNo(currentPageNo);
		page.setTotalRows(c);
		page.setPageSize(pageSize);
		
		
		
		
		
		sql.append(" order by c.id desc limit ?,?");
		params.add((currentPageNo-1)*pageSize);
		params.add(pageSize);
		list = dao.queryForList(sql.toString(), new MapperCertificate() , params.toArray()); 
		
		page.setData(list); 
		return page; 
	}

	
	// 根据ID查询证书详情
	@Override
	public Certificate findById(Serializable id){
		String sql = "select * from cer_certificate where id=?";
		Certificate cer = dao.queryForObject(sql, new MapperCertificateForFind(), id); 
		// 查询企业
		sql = "select e.*,c.name cname,p.name pname from cer_enterprise e join cer_city c on c.id=e.city_id join cer_province p on p.id=e.province_id where e.id = ?";
		Enterprise enter = dao.queryForObject(sql, new MapperEnterprise(), cer.getEnterpriseId()); 
		cer.setEnterprise(enter); 
		
		// 查询申证单元
		sql = "select * from cer_unit where cid = ?";
		List<Unit> units = dao.queryForList(sql, new MapperUnit(), id);
		for(Unit unit : units){
			sql = "select * from cer_unit_food where cid=? and uid=?";
			List<Food> foods = dao.queryForList(sql, new MapperFood(), id,unit.getId()); 
			unit.setFoods(foods);
		}
		cer.setUnits(units); 
		return cer;
	}

	
	// 查询队列中的任务
	@Override
	public List<Certificate> queryCurrentQueue() { 
		String sql = "select c.*,e.name ename,p.name pname,city.name cname from cer_certificate c "
				+ "left join cer_enterprise e on e.id=c.enterprise_id "
				+ "join cer_city city on city.id=e.city_id "
				+ "join cer_province p on p.id=e.province_id "
				+ "join cer_printer_queue pq on pq.cid = c.id where 1=1";
		
		return dao.queryForList(sql.toString(), new MapperCertificate());
	}

	
	// 提交到队列
	@Override
	public void submitPrintQueue(Serializable cid) { 
		String sql = "INSERT INTO `cer_printer_queue`(cid) VALUES (?)";  
		dao.update(sql, cid);
		sql = "update cer_certificate set printer=1 where id=?"; 
		dao.update(sql,cid);// 更新状态为排队中...
	}

	
	// 抓取一条记录
	@Override
	public Certificate fetch() {
		String sql = "select c.*, pq.id qid from cer_certificate c join cer_printer_queue pq on pq.cid=c.id order by id asc limit 1 ";
		Certificate cer = dao.queryForObject(sql, new MapperCertificateForQueue()); // 查询一个最前面的证书
		
		if(cer != null){
			// 查询企业
			sql = "select e.*,c.name cname,p.name pname from cer_enterprise e join cer_city c on c.id=e.city_id join cer_province p on p.id=e.province_id where e.id = ?";
			Enterprise enter = dao.queryForObject(sql, new MapperEnterprise(), cer.getEnterpriseId()); 
			cer.setEnterprise(enter); 
			
			// 查询申证单元
			sql = "select * from cer_unit where cid = ?";
			List<Unit> units = dao.queryForList(sql, new MapperUnit(), cer.getId());
			for(Unit unit : units){
				sql = "select * from cer_unit_food where cid=? and uid=?";
				List<Food> foods = dao.queryForList(sql, new MapperFood(), cer.getId(),unit.getId()); 
				unit.setFoods(foods);
			}
			cer.setUnits(units);
			
			// 查询完毕删除这条数据
			deleteQueueById(cer.getQid());
		}else{
			return null;
		}
		return cer;
	}

	
	// 打印队列删除实现
	private void deleteQueueById(Serializable cid) {
		Connection conn = null;
		try {
			conn = dao.getConnection(); 
			PreparedStatement ps = conn.prepareStatement("delete from cer_printer_queue where id = ?");
			dao.insertParams(ps, cid);// 插入参数 
		    ps.executeUpdate(); ps.close(); 
		} catch (SQLException e) { 
			log.error("deleteQueueById error!", e); 
		}finally{
			try {
				conn.close();
			} catch (SQLException e) { 
				log.error("service deleteQueueById error!", e);
			}
		} 
	}


	// 获取未打印证书数量
	@Override
	public int getNotPrinter() {
		String sql = "select count(id) from cer_certificate where printer = 0"; 
		Connection conn = null;
		try {
			conn = dao.getConnection(); 
			PreparedStatement ps = conn.prepareStatement(sql); 
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
			   return rs.getInt(1);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) { 
			log.error("deleteQueueById error!", e); 
		}finally{
			try {
				conn.close();
			} catch (SQLException e) { 
				log.error("service getNotPrinter error!", e);
			}
		} 
		return 0;
	}


	// 提交未打印记录到队列
	@Override
	public void submitNotPrintToQueue() {
		String sql = "insert into cer_printer_queue(cid) select id from cer_certificate where printer = 0";
		// 查询未打印记录
		boolean status = dao.update(sql);// 批量提交打印到队列
		if(status){
			sql = "update cer_certificate set printer=1 where printer=0";
			dao.update(sql);// 批量更新打印状态 
		}
	}


	// 更新证书状态为已经打印
	@Override
	public void updateCertificate(Serializable cid) {
		String sql = "update cer_certificate set printer=2 where id=?";
		// 查询未打印记录
		dao.update(sql,cid);// 批量提交打印到队列  
	}


	// 查询所有
	@Override
	public List<Certificate> query() {
		
		String sql = "select * from cer_certificate";
		List<Certificate> list = dao.queryForList(sql, new MapperCertificateForFind()); 
		for(int i =0; i<list.size(); i++){
			Certificate cer = list.get(i);
			// 查询企业
			sql = "select e.*,c.name cname,p.name pname from cer_enterprise e join cer_city c on c.id=e.city_id join cer_province p on p.id=e.province_id where e.id = ?";
			Enterprise enter = dao.queryForObject(sql, new MapperEnterprise(), cer.getEnterpriseId()); 
			cer.setEnterprise(enter); 
			
			// 查询申证单元
			sql = "select * from cer_unit where cid = ?";
			List<Unit> units = dao.queryForList(sql, new MapperUnit(), cer.getId());
			for(Unit unit : units){
				sql = "select * from cer_unit_food where cid=? and uid=?";
				List<Food> foods = dao.queryForList(sql, new MapperFood(), cer.getId(),unit.getId()); 
				unit.setFoods(foods);
			}
			cer.setUnits(units); 
		}
		return list;
	}


	@Override
	public boolean checkCode(String code) { 
		String sql = "select count(id) from cer_certificate where code = ?";
		int a = dao.queryForInt(sql, code); 
		return a > 0?true : false; 
	}
	


}
