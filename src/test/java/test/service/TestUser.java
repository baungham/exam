package test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.marker.certificate.bean.User;
import org.marker.certificate.service.IUserService;
import org.marker.certificate.service.impl.UserServiceImpl;

public class TestUser {
	
	
	// 有条件
	@org.junit.Test
	public void testOneUser(){
		IUserService service = new UserServiceImpl();
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("name", "a");
		List<User> users  = service.queryList(condition);
		Assert.assertNotNull(users);
		Assert.assertEquals(users.size(), 1);
	}
	
	
	// 没有条件
	@org.junit.Test
	public void testUsers(){
		IUserService service = new UserServiceImpl();
		Map<String, Object> condition = new HashMap<String, Object>(); 
		List<User> users  = service.queryList(null);
		Assert.assertNotNull(users);
		Assert.assertEquals(users.size(), 2);
	}
	
	// 添加
	@org.junit.Test
	public void testUserAdd(){
		IUserService service = new UserServiceImpl();
		User u = new User();
		u.setName("123");
		u.setPass("123");
		
		service.save(u); 
	}
	
	
	// 修改
	@org.junit.Test
	public void testUserUpdate(){
		IUserService service = new UserServiceImpl();
		User u = new User();
		u.setId(1);
		u.setName("123");
		u.setPass("123");
		
		service.update(u); 
	}
}
