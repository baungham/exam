package org.marker.certificate.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.marker.certificate.bean.ServiceMessage;
import org.marker.certificate.bean.User;
import org.marker.certificate.dao.DaoEngine;
import org.marker.certificate.dao.mapper.ObjectMapper.MapperUser;
import org.marker.certificate.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 用户管理
 * @author marker
 * @version 1.0
 */
public class UserServiceImpl implements IUserService{

	// 日志记录器
	protected Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	// dao
	private DaoEngine dao = new DaoEngine();
	
	
	
	@Override
	public ServiceMessage save(User user) {
		String sql = "INSERT INTO `cer_user`(name,pass,phone,`desc`,status) VALUES (?,?,?,?,?)";
		Object[] params = new Object[]{user.getName(),user.getPass(),user.getPhone(),user.getDesc(),user.isStatus()};
		if(dao.update(sql, params)){
			return new ServiceMessage(true, "添加成功!");
		}
		return new ServiceMessage(true, "添加失败!"); 
	}

	@Override
	public ServiceMessage update(User user) {
		String sql = "update `cer_user` set name=?,pass=?,phone=?,`desc`=?,status=? where id=?";
		Object[] params = new Object[]{user.getName(),user.getPass(),user.getPhone(),user.getDesc(),user.isStatus(),user.getId()}; 
		if(dao.update(sql, params)){
			return new ServiceMessage(true, "操作成功!");
		}
		return new ServiceMessage(true, "操作失败!");
	}

	
	// 通过ID删除
	@Override
	public ServiceMessage deleteById(Serializable id) {
		String sql = "delete from cer_user where id = ?"; 
		if(dao.update(sql, id)){
			return new ServiceMessage(true, "操作成功!");
		}
		return new ServiceMessage(true, "操作失败!");
	}

	// 查询全部用户
	@Override
	public List<User> queryList(Map<String, Object> condition) {
		String name = "%";
		if(condition != null){
			name = (String) condition.get("name");
			if (null == name)
				name = "";
			name = '%' + name + '%';
		}
		
		String sql = "select * from cer_user where name like ? order by id"; 
		return dao.queryForList(sql, new MapperUser() , name); 
	}

	
	
	@Override
	public User findById(Serializable id) {
		String sql = "select * from cer_user where id = ?";
		return dao.queryForObject(sql, new MapperUser(), id);
	}

	
	// login
	@Override
	public User login(String username, String password) {
		String sql = "select * from cer_user where name=? and pass=?";
		User user = dao.queryForObject(sql, new MapperUser(), username, password);
		if(user != null){ 
			// 更新登录时间
			dao.update("update cer_user set time=sysdate() where id=?",user.getId());
		}
		return user;
	}

}
