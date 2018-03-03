package test.service;

import org.junit.Test;
import org.marker.certificate.bean.Page;
import org.marker.certificate.bean.PrinterLog;
import org.marker.certificate.service.impl.PrinterLogServiceImpl;

public class TestLog {

	
	
	@Test
	public   void main( ) {
		
		
		PrinterLogServiceImpl service = new PrinterLogServiceImpl();
		
		
		
		
		Page<PrinterLog> page  = service.queryByPage(1, 2, null);
	}
}
