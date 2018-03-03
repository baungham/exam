package org.marker.certificate.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class XlsUtil {

	/**
	 * 读取数据
	 * PrintTextObject
	 * IRowMapper
	 * */
	public static void load(String xlsPath,
			IRowMapper irowm) throws IOException {
		File file = new File(xlsPath);
		if (!file.exists()) {// 如果文件不存在
			JOptionPane.showMessageDialog(null, "请选择Excel模板文件!");
			return;
		}


		Workbook wb = ExcelUtils.createWorkBook(xlsPath);



		Sheet sheet = wb.getSheetAt(0);
		Iterator<Row> it = sheet.rowIterator();
		while (it.hasNext()) {//每行就是一张证书
			//此集合为当前证书里面的需要打印的字符
			
			Row row = it.next();
			
			if(row.getRowNum() <= 2) //排除第一行
				continue;

			
			irowm.execute(row);
		}
	}

	
	
	
//	/**
//	 * 单行处理生成打印数据集合
//	 * */
//	public static List<PrintTextObject> progcess(Row row) throws IOException {
// 
//		//此集合为当前证书里面的需要打印的字符
//		List<PrintTextObject> paperPrintData = new ArrayList<PrintTextObject>();
//		
//		String enterprise = row.getCell(1).getStringCellValue();//企业名称
//		String product = row.getCell(2).getStringCellValue();//
//		String residence = row.getCell(3).getStringCellValue();//
//		String address = row.getCell(4).getStringCellValue();//
//		String inspection = row.getCell(5).getStringCellValue();//
//		String number = row.getCell(6).getStringCellValue();//
//		String period = row.getCell(7).getStringCellValue();//周期
//		String issue = row.getCell(8).getStringCellValue();//发行时间
//		
//		
//		//添加打印信息到当前证书数据集合
//		int info_x = 195, info_y = 182;//定位基本信息
//		int step = 23;
//		paperPrintData.add(new PrintTextObject(enterprise, info_x, info_y));
//		paperPrintData.add(new PrintTextObject(product, info_x, info_y +step));
//		paperPrintData.add(new PrintTextObject(residence, info_x, info_y +step*2));
//		paperPrintData.add(new PrintTextObject(address, info_x, info_y +step*3));
//		paperPrintData.add(new PrintTextObject(inspection, info_x, info_y +step*4));
//		paperPrintData.add(new PrintTextObject(number, info_x, info_y +step*5));
//		paperPrintData.add(new PrintTextObject(issue, info_x, info_y +step*6));
//		paperPrintData.add(new PrintTextObject(period, info_x + 165, info_y + step*7-10));
//		
//		//下边表格定义
//		int scope_x = 135, scope_y = 406;//定位附加表格信息
//		int scope_step = 22;
//		
//		//此数据可能折行
//		int lbs = 9;
//		for(int j=0; j<3; j++){
//			
//			String scope = row.getCell(lbs).getStringCellValue();//
//			
//			List<PrintTextObject> scopeList = splitStr(scope, 8,  scope_x + 40, scope_y);
//			paperPrintData.addAll(scopeList);
//			
////			paperPrintData.add(new PrintTextObject(scope, scope_x + 40 , scope_y));
//			int tmpIndex = lbs + 1;
//			for (int i = 0; i < 3; i++) {//
//				//序号
//				paperPrintData.add(new PrintTextObject((i+1)+"", scope_x, scope_y + scope_step*i));
//				//食品品种以及使用标准
//				String food = row.getCell(tmpIndex)  .getStringCellValue();//
//				String used = row.getCell(tmpIndex+1).getStringCellValue();//  
//
//				
//				paperPrintData.add(new PrintTextObject(food, scope_x +184,  scope_y  + scope_step*i ));
//				paperPrintData.add(new PrintTextObject(used, scope_x +370 , scope_y  + scope_step*i ));
//				tmpIndex +=2;
//			}
//			scope_y += scope_step*3;//下一个单元格
//			lbs += 7;//下一个申证单元
//		}  
//
//		return paperPrintData;
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日"); 
//	
//	
//	public static List<PrintTextObject> progcess(Vector<String> row) throws IOException {
//		//此集合为当前证书里面的需要打印的字符
//		List<PrintTextObject> paperPrintData = new ArrayList<PrintTextObject>();
//		
//		
//		
//		String enterprise = row.get(0);//企业名称
//		String product = row.get(1);//产品
//		String residence = row.get(2);//
//		String address = row.get(3);//
//		String inspection = row.get(4);//
//		String number = row.get(5);//
//		String period =row.get(6);//周期
//		String issue = row.get(7);//发行时间
//		
//		//如果这条数据无效就排除。以企业名称是否为空判断
//		if (enterprise == null || "".equals(enterprise))
//			return null;
//		 
//		
//		//添加打印信息到当前证书数据集合
//		int info_x = 195, info_y = 182;//定位基本信息
//		int step = 23;
//		paperPrintData.add(new PrintTextObject(enterprise, info_x, info_y));
//		paperPrintData.add(new PrintTextObject(product, info_x, info_y +step));
//		paperPrintData.add(new PrintTextObject(residence, info_x, info_y +step*2));
//		paperPrintData.add(new PrintTextObject(address, info_x, info_y +step*3));
//		paperPrintData.add(new PrintTextObject(inspection, info_x, info_y +step*4));
//		paperPrintData.add(new PrintTextObject(number, info_x, info_y +step*5));
//		paperPrintData.add(new PrintTextObject(issue, info_x, info_y +step*6));
//		paperPrintData.add(new PrintTextObject(period, info_x + 165, info_y + step*7-10));
//		
//		
//		//计算发证书时间 
//		int date_x = 335, date_y = 722;//定位时间信息
//		Date date = null;
//		try {
//			date = sdf.parse(issue); 
//			Calendar c = Calendar.getInstance();
//			c.setTime(date); 
//			int year  = c.get(Calendar.YEAR);
//			int month = c.get(Calendar.MONTH) + 1;
//			int day   = c.get(Calendar.DATE);
//			paperPrintData.add(new PrintTextObject(year+"", date_x, date_y));
//			paperPrintData.add(new PrintTextObject(month+"", date_x + 45, date_y));
//			paperPrintData.add(new PrintTextObject(day+"", date_x   + 90, date_y));
//			
//			
//			
//		} catch (ParseException e) { 
//			System.out.println("time error1");
//		} 
//		
//		//下边表格定义
//		int scope_x = 135, scope_y = 406;//定位附加表格信息
//		int scope_step = 22;
//		//此数据可能折行
//	
//		
//		int lbs = 8;
//		for(int j=0; j<3; j++){
//			//这里要处理个自动换行
//			String scope = row.get(lbs);//
//			List<PrintTextObject> scopeList = splitStr(scope, 8,  scope_x + 30, scope_y);
//			paperPrintData.addAll(scopeList);
//			
////			paperPrintData.add(new PrintTextObject(scope, scope_x + 30 , scope_y));
//			int tmpIndex = lbs +1;
//			for (int i = 0; i < 3; i++) {//
//				//序号
//				paperPrintData.add(new PrintTextObject((i+1)+"", scope_x, scope_y + scope_step*i));
//				//食品品种以及使用标准
//				String food = row.get(tmpIndex);//
//				String used = row.get(tmpIndex+1);//  
//
//				
//				paperPrintData.add(new PrintTextObject(food, scope_x +130,  scope_y  + scope_step*i ));
//				paperPrintData.add(new PrintTextObject(used, scope_x +265 , scope_y  + scope_step*i ));
//				tmpIndex +=2;
//			}
//			scope_y += scope_step*3;//下一个单元格
//			lbs += 7;//下一个申证单元
//		} 
//		return paperPrintData;
//	}
//	
//	
//	
//	
//	
//	
//	/**
//	 * 字符串拆分为打印字符串对象
//	 * @param String text 字符串
//	 * @param int size   每行字数
//	 * @param int x      打印X基位置
//	 * @param int y      打印Y基位置
//	 * */
//	public static List<PrintTextObject> splitStr(String text, int size, int x, int y){
//		List<PrintTextObject> list = new ArrayList<PrintTextObject>();
//		int y_step = 0;
//		int index = 0;//截取索引值
//		String tempStr = "";
//		while(index < text.length()){
//			int a = index + size;
//			if(a <= text.length()){
//				tempStr = text.substring(index, a);
//				index  += size;
//			}else{
//				tempStr = text.substring(index, text.length());
//				index  += size;
//			}
//			list.add(new PrintTextObject(tempStr, x, y+y_step));
//			y_step += 15;
//		}
//		return list;
//	}
	
}
