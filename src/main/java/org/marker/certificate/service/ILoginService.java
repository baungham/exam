package org.marker.certificate.service;

import org.marker.certificate.bean.User;


/**
 * 用户登录业务处理
 * @author marker
 * @version 1.0
 */
public interface ILoginService {

	/**
	 * 用户登录
	 * @param username 帐号
	 * @param password 密码
	 * @return
	 */
	User login(String username, String password);
	
	
}
