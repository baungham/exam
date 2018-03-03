import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class TestXls {

	public static void main(String[] args) throws IOException {
		String path = "D:\\works\\MyEclipseProfessional2014\\certificate\\doc\\template.xls";
		InputStream is = new FileInputStream(path);
		
		
		HSSFWorkbook wb = new HSSFWorkbook(is);
		
		  
		
		 FileOutputStream fileOut = new FileOutputStream("c://a.xls");     
		
		 
		 wb.write(fileOut);  
	}
}
