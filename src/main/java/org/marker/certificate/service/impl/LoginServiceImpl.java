package org.marker.certificate.service.impl;

import org.marker.certificate.bean.User;
import org.marker.certificate.dao.DaoEngine;
import org.marker.certificate.dao.mapper.ObjectMapper.MapperUser;
import org.marker.certificate.service.ILoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 登录系统
 * @author marker
 * @version 1.0
 */
public class LoginServiceImpl implements ILoginService{

	// 日志记录器
	protected Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	// dao
	private DaoEngine dao = new DaoEngine();
	
	
	@Override
	public User login(String username, String password) {
		// 加密处理，暂时不做
		
		
		// status=1的用户才行
		String sql = "select * from cer_user where name = ? and pass = ? and status = 1"; 
		return dao.queryForObject(sql, new MapperUser(), new Object[]{username, password});
	}

}
