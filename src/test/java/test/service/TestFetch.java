package test.service;

import org.junit.Assert;
import org.junit.Test;
import org.marker.certificate.bean.Certificate;
import org.marker.certificate.service.impl.CertificateServiceImpl;

public class TestFetch {

	
	@Test
	public void testFind(){ 
		CertificateServiceImpl service = new CertificateServiceImpl(); 
		
		Certificate msg = service.fetch(); 
	}
}
