package org.marker.certificate.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.marker.certificate.bean.Certificate;
import org.marker.certificate.bean.Food;
import org.marker.certificate.bean.Unit;
import org.marker.certificate.service.ICertificateService;
import org.marker.certificate.service.impl.CertificateServiceImpl;


/**
 * 导出数据工具
 * @author marker
 * @version 1.0
 */
public class ExportDataUtil {

	// 证书业务
	private static ICertificateService service = new CertificateServiceImpl();
	
	
	
	
	/**
	 * 
	 * @param path
	 * @throws Exception
	 */
	public static void process(String path) throws Exception{
		HSSFWorkbook wb = loadTempate();// 加载模板

		List<Certificate> list = service.query(); 
		HSSFSheet sheet = wb.getSheetAt(0);// 创建Excel工作表对象      
//		sheet.setDefaultRowHeight((short) 350);
		
		Iterator<Certificate> it = list.iterator();
		int count = 1;
		while(it.hasNext()){
			Certificate cer = it.next(); 
			deal(sheet, cer, count);
			count++;
		} 
		FileOutputStream fos = new FileOutputStream(path); 
		wb.write(fos);
		fos.flush();
		fos.close();
	}
	
	
	/**
	 * 读取模板
	 * @throws Exception 
	 */
	private static HSSFWorkbook loadTempate() throws Exception{
		InputStream is = new FileInputStream("doc/template.xls");
		POIFSFileSystem pfs = new POIFSFileSystem(is);
		return new HSSFWorkbook(pfs);
	}
	
	
	
	
	// 一行记录
	private static void deal(HSSFSheet sheet, Certificate cer, int count){
		HSSFRow row = sheet.createRow(count); 
		row.setHeightInPoints(35);// 设置行高
		HSSFCell cell1 = row.createCell(1);// 企业名称
		HSSFCell cell2 = row.createCell(2);// 产品名称
		HSSFCell cell3 = row.createCell(3);// 住所
		HSSFCell cell4 = row.createCell(4);// 生产地址
		HSSFCell cell5 = row.createCell(5);// 检验方式
		HSSFCell cell6 = row.createCell(6);// 证书编号
		HSSFCell cell7 = row.createCell(7);// 有效期
		HSSFCell cell8 = row.createCell(8);// 发证日期
		
		
		cell1.setCellValue(cer.getEnterprise().getName());
		cell2.setCellValue(cer.getProduct()); 
		cell3.setCellValue(cer.getEnterprise().getCityName());
		cell4.setCellValue(cer.getEnterprise().getAddress());
		cell5.setCellValue(cer.getVerifyMode());
		cell6.setCellValue(cer.getCode());
		cell7.setCellValue(cer.getEffectiveTimeFormat());
		cell8.setCellValue(cer.getSendTimeFormat());
		
		
		// 生成申证单元
		int cell = 9;
		List<Unit> units = cer.getUnits();
		for(int i=0;i<units.size();i++){
			Unit unit = units.get(i);
			row.createCell(cell++).setCellValue(unit.getDesc());
			List<Food>  foods = unit.getFoods();
			for(int j =0;j<foods.size();j++){
				Food food = foods.get(j);
				row.createCell(cell++).setCellValue(food.getName());
				row.createCell(cell++).setCellValue(food.getUsed()); 
			}
		}
		
		
	}
	
}
