package test.service;

import org.junit.Assert;
import org.marker.certificate.bean.User;
import org.marker.certificate.service.ILoginService;
import org.marker.certificate.service.impl.LoginServiceImpl;

/**
 * 登录测试
 * @author marker
 * @version 1.0
 */
public class TestLogin {

	@org.junit.Test
	public void testLogin(){
		ILoginService loginService = new LoginServiceImpl();
		User user  = loginService.login("admin", "123"); 
		Assert.assertNotNull(user);
	}
		
}
