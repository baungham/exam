package org.marker.certificate.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.marker.certificate.bean.Enterprise;
import org.marker.certificate.bean.Page;
import org.marker.certificate.bean.ServiceMessage;
import org.marker.certificate.dao.DaoEngine;
import org.marker.certificate.dao.mapper.ObjectMapper.MapperEnterprise;
import org.marker.certificate.service.IEnterpriseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 企业管理
 * @author marker
 * @version 1.0
 */
public class EnterpriseServiceImpl implements IEnterpriseService {

	// 日志记录器
	protected Logger log = LoggerFactory.getLogger(EnterpriseServiceImpl.class);
	
	// dao
	private DaoEngine dao = new DaoEngine();
	
	
	// 保存
	@Override
	public ServiceMessage save(Enterprise enterprise) { 
		String sql = "INSERT INTO `cer_enterprise`(name,city_id,province_id,`desc`,time,address) VALUES(?,?,?,?,sysdate(),?)";  
		Object[] params = new Object[]{
				enterprise.getName(),
				enterprise.getCityId(),
				enterprise.getProvinceId(),
				enterprise.getDesc(),
				enterprise.getAddress()};
		if(dao.update(sql, params)){
			return new ServiceMessage(true, "添加成功!");
		}
		return new ServiceMessage(true, "添加失败!");  
	}

	@Override
	public ServiceMessage update(Enterprise enterprise) {
		String sql = "update `cer_enterprise` set name=?,city_id=?,province_id=?,`desc`=?,address=? where id=?";
		Object[] params = new Object[]{
				enterprise.getName(),
				enterprise.getCityId(),
				enterprise.getProvinceId(),
				enterprise.getDesc(),
				enterprise.getAddress(),
				enterprise.getId()
		};
		if(dao.update(sql, params)){
			return new ServiceMessage(true, "操作成功!");
		}
		return new ServiceMessage(true, "操作失败!");
	}

	// 通过ID删除
	@Override
	public ServiceMessage deleteById(Serializable id) {
		String sql = "delete from cer_enterprise where id = ?"; 
		if(dao.update(sql, id)){
			return new ServiceMessage(true, "操作成功!");
		}
		return new ServiceMessage(false, "操作失败!");
	}

	
 
	@Override
	public Page<Enterprise> queryByPage(int currentPageNo, int pageSize,
			Map<String, Object> condition) {
		
		StringBuilder sql = new StringBuilder("select e.*,c.name cname,p.name pname from cer_enterprise e "
				+ "join cer_city c on c.id=e.city_id "
				+ "join cer_province p on p.id=e.province_id where 1=1 ");
		StringBuilder count = new StringBuilder("select count(*) from cer_enterprise e join cer_city c on c.id=e.city_id join cer_province p on p.id=e.province_id where 1=1 ");
		List<Enterprise> list ;
		
		 
		List<Object> params = new ArrayList<Object>();
		if(condition != null){
			String ename = (String) condition.get("ename");
			String ecity = (String) condition.get("ecity"); 
			String eaddress = (String)condition.get("eaddress");
			String all = (String)condition.get("all");
			if(ename != null){  
				sql.append(" and e.name like ?");params.add('%'+ename+'%');
				count.append(" and e.name like ?");
			}else if(ecity != null){ 
				sql.append(" and c.name like ?"); params.add('%'+ecity+'%');
				count.append(" and c.name like ?");
			} else if(eaddress != null){
				sql.append(" and e.address like ?"); params.add('%'+eaddress+'%');
				count.append(" and e.address like ?");
			}else if(all != null){
				sql.append(" and e.name like ? or c.name like ? or e.address like ? ");
				count.append(" and e.name like ? or c.name like ? or e.address like ? ");
				params.add('%'+all+'%');
				params.add('%'+all+'%');
				params.add('%'+all+'%');
			}
		} 
		
		int c = dao.queryForInt(count.toString(), params.toArray());
		
		Page<Enterprise> page = new Page<Enterprise>();
		page.setCurrentPageNo(currentPageNo);
		page.setTotalRows(c);
		page.setPageSize(pageSize);
		 
		sql.append(" limit ?,? ");
		params.add((currentPageNo-1)*pageSize);
		params.add(pageSize);
		list = dao.queryForList(sql.toString(), new MapperEnterprise() , params.toArray()); 
		
		page.setData(list); 
		return page;
	}

	
	// 通过ID查询
	@Override
	public Enterprise findById(Serializable id) { 
		String sql = "select e.*,c.name cname,p.name pname from cer_enterprise e join cer_city c on c.id=e.city_id join cer_province p on p.id=e.province_id where e.id = ?";
		return dao.queryForObject(sql, new MapperEnterprise(), id);
	}

	// 通过名称查询
	@Override
	public Enterprise findByName(String ename) {
		String sql = "select e.*,c.name cname,p.name pname from cer_enterprise e join cer_city c on c.id=e.city_id join cer_province p on p.id=e.province_id where e.name = ?";
		return dao.queryForObject(sql, new MapperEnterprise(), ename);
	
	}

	@Override
	public int saveForId(Enterprise enterprise) {
		String sql = "INSERT INTO `cer_enterprise`(name,city_id,province_id,`desc`,time,address) VALUES(?,?,?,?,sysdate(),?)";  
		Object[] params = new Object[]{
				enterprise.getName(),
				enterprise.getCityId(),
				enterprise.getProvinceId(),
				enterprise.getDesc(),
				enterprise.getAddress()};
		return dao.updateReturnId(sql, params); 
	}

}
