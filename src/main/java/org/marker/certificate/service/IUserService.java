package org.marker.certificate.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.marker.certificate.bean.User;



/**
 * 用户管理Service
 * 实现根据用户名模糊查询，不需要分页
 * (注意：不用问为什么做)
 * @author marker
 * @version 1.0
 */
public interface IUserService extends ICommonService<User> {

	/**
	 * 查询全部用户
	 * 条件：
	 * 		用户名:name
	 *  
	 *      注意：模糊查询
	 * 
	 * @param condition 条件
	 * @return
	 */
	List<User> queryList(Map<String, Object> condition);

	
	
	/**
	 * 通过Id查询
	 * @param id
	 * @return
	 */
	User findById(Serializable id);
	
	
	
	/**
	 * login
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username,String password);
	
}
