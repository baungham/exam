package org.marker.certificate.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.marker.certificate.bean.Certificate;
import org.marker.certificate.bean.Page;
import org.marker.certificate.bean.PrinterLog;
import org.marker.certificate.dao.DaoEngine;
import org.marker.certificate.dao.mapper.ObjectMapper.MapperPrinterLog;
import org.marker.certificate.service.IPrinterLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 
 * @author marker
 * @version 1.0
 */
public class PrinterLogServiceImpl implements IPrinterLogService {

	// 日志记录器
	protected Logger log = LoggerFactory.getLogger(PrinterLogServiceImpl.class);
	
	// dao
	private DaoEngine dao = new DaoEngine();
	
	
	
	
	
	@Override
	public Page<PrinterLog> queryByPage(int currentPageNo, int pageSize,
			Map<String, Object> condition) {
		
		StringBuilder sql = new StringBuilder("select * from cer_printer_log where 1=1 ");
		StringBuilder count = new StringBuilder("select count(*) from cer_printer_log where 1=1 ");
		List<PrinterLog> list ;
		
		 
		List<Object> params = new ArrayList<Object>();
//		if(condition != null){
//			String ename = (String) condition.get("ename");
//			String ecity = (String) condition.get("ecity"); 
//			String eaddress = (String)condition.get("eaddress");
//			String all = (String)condition.get("all");
//			if(ename != null){  
//				sql.append(" and e.name like ?");params.add('%'+ename+'%');
//				count.append(" and e.name like ?");
//			}else if(ecity != null){ 
//				sql.append(" and c.name like ?"); params.add('%'+ecity+'%');
//				count.append(" and c.name like ?");
//			} else if(eaddress != null){
//				sql.append(" and e.address like ?"); params.add('%'+eaddress+'%');
//				count.append(" and e.address like ?");
//			}else if(all != null){
//				sql.append(" and e.name like ? or c.name like ? or e.address like ? ");
//				count.append(" and e.name like ? or c.name like ? or e.address like ? ");
//				params.add('%'+all+'%');
//				params.add('%'+all+'%');
//				params.add('%'+all+'%');
//			}
//		} 
		
		int c = dao.queryForInt(count.toString(), params.toArray());
		
		Page<PrinterLog> page = new Page<PrinterLog>();
		page.setCurrentPageNo(currentPageNo);
		page.setTotalRows(c);
		page.setPageSize(pageSize);
		 
		sql.append(" limit ?,? ");
		params.add((currentPageNo-1)*pageSize);
		params.add(pageSize);
		list = dao.queryForList(sql.toString(), new MapperPrinterLog() , params.toArray()); 
		
		page.setData(list); 
		return page; 
	}

	@Override
	public int save(Certificate log) {
		String sql = "INSERT INTO `cer_printer_log`(time,code,product,verifyMode,enterprise_name,province,city,address) VALUES (sysdate(),?,?,?,?,?,?,?)";  
		Object[] params = new Object[]{
				log.getCode(),
				log.getProduct(),
				log.getVerifyMode(),
				log.getEnterprise().getName(),
				log.getEnterprise().getProvinceName(),
				log.getEnterprise().getCityName(),
				log.getEnterprise().getAddress()
		};
		// 取得证书ID
		return dao.updateReturnId(sql, params);
	}

 
	
}
