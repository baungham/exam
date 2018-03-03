package test.service;

import org.junit.Test;
import org.marker.certificate.bean.Enterprise;
import org.marker.certificate.bean.Page;
import org.marker.certificate.service.impl.EnterpriseServiceImpl;

public class TestE {

	
	// 分测试成功
	@Test
	public void testFindPage(){  
		EnterpriseServiceImpl service = 	new EnterpriseServiceImpl(); 
		Page<Enterprise> page = service.queryByPage(1, 1, null);
					System.out.println(page.getTotalPages());
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
