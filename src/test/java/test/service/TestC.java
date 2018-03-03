package test.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.marker.certificate.bean.Certificate;
import org.marker.certificate.bean.Food;
import org.marker.certificate.bean.ServiceMessage;
import org.marker.certificate.bean.Unit;
import org.marker.certificate.service.impl.CertificateServiceImpl;
import org.marker.certificate.service.impl.EnterpriseServiceImpl;



/**
 * 
 * @author marker
 * @version 1.0
 */
public class TestC {

	
	
	//@Test
	public void testAdd(){
		CertificateServiceImpl service = new CertificateServiceImpl();
		Certificate cer = new Certificate();
		cer.setCode("123132");
		cer.setEffectiveTime(new Date());
		cer.setSendTime(new Date());
		cer.setVerifyMode("1");
		Unit u = new Unit();
		u.setDesc("申证单元1");
		u.getFoods().add(new Food("香蕉","吃"));
		u.getFoods().add(new Food("苹果","吃"));
		cer.addUnit(u);
		service.save(cer); 
		
	}
	
	//@Test
	public void testDel(){ 
		CertificateServiceImpl service = new CertificateServiceImpl(); 
		
		ServiceMessage msg = service.deleteById(11);
		Assert.assertEquals(msg.isStatus(), true);
	}
	
	
	
	@Test
	public void testFind(){ 
		CertificateServiceImpl service = new CertificateServiceImpl(); 
		
		Certificate msg = service.findById(16);
		Assert.assertEquals(msg, null);
	}
	
	public static void main(String[] args) {
		EnterpriseServiceImpl service = new EnterpriseServiceImpl(); 
		service.findById(1); 
	}
}
