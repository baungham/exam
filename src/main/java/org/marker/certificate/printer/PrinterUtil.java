
package org.marker.certificate.printer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.marker.certificate.bean.Certificate;
import org.marker.certificate.bean.Enterprise;
import org.marker.certificate.bean.Food;
import org.marker.certificate.bean.Unit;
import org.marker.certificate.config.impl.PrinterConfig;



/**
 * 打印工具类
 * @author marker
 * @version 1.0
 */
public class PrinterUtil {

 
	// 格式化年、月、日
	private static SimpleDateFormat yearSDF =  new SimpleDateFormat("yyyy");
	private static SimpleDateFormat monthSDF =  new SimpleDateFormat("MM");
	private static SimpleDateFormat daySDF =  new SimpleDateFormat("dd");
	
	
	/**
	 * 处理证书打印应对象
	 * @param cer
	 */
	public static List<PrintTextObject> progcess(Certificate cer){
		
		PrinterConfig config = PrinterConfig.getInstance();
		String paper = config.get("paper.current");// 获取当前选择的纸张
		
		PrinterLocation local = loadLocation(paper,"enterprise");
		List<PrintTextObject> list = new ArrayList<PrintTextObject>();
		
		
		
		int ax = local.getAbsoluteX();
		int ay = local.getAbsoluteY();
		int x = local.getX();
		int y = local.getY();
		int lh = local.getLineheight();
		Font font = local.getFont();
		
		// 计算企业信息位置                     
		Enterprise enter = cer.getEnterprise(); // 企业对象                            
		list.add(new PrintTextObject(enter.getName(), font, ax + x, ay+y ));// 企业名称	                                                                        
	    list.add(new PrintTextObject(cer.getProduct(), font, ax + x, ay+y + lh ));// 产品名称
	    list.add(new PrintTextObject(enter.getCityName(), font, ax + x, ay+y + lh*2 ));// 住所       
	    list.add(new PrintTextObject(enter.getAddress(), font, ax + x, ay+y + lh*3 ));// 生产地址                                                                                      
		list.add(new PrintTextObject(cer.getVerifyMode(),font, ax + x, ay+y + lh*4 ));// 检验方式    
		list.add(new PrintTextObject(cer.getCode(),font, ax + x, ay+y + lh*5 ));// 证书编号
		list.add(new PrintTextObject(cer.getSendTimeFormat(),font, ax + x, ay+y + lh*6 ));// 发证日期
		
		
		// 有效期位置
		PrinterLocation local2 = loadLocation(paper,"effective");
		font = local2.getFont();
		list.add(new PrintTextObject(cer.getEffectiveTimeFormat(),font, local2.getAbsoluteX()+local2.getX(), local2.getAbsoluteY()+local2.getY() ));// 发证日期
		
		
		// 申证单元表格位置
		PrinterTableLocation local3 = loadTableLocation(paper,"unittable");
		font = local3.getFont();
		
		
		int tax = local3.getAbsoluteX();
		int tay = local3.getAbsoluteY();
		int tnx = local3.getNumberX();
		int tunx = local3.getUnitX();
		int tlh = local3.getLineheight();
		int tux = local3.getUsedX();
		int tfx = local3.getFoodX();
		int tfl = local3.getFold_line();// 折行
		
		List<Unit> units = cer.getUnits();
		for(int i=0; i<units.size(); i++){
			Unit cunit = units.get(i);
			int tlht = 20 + tlh  * i * 3;
			
			List<PrintTextObject> a = splitTextString(cunit.getDesc(), font, tfl, tax +tunx , tay + tlht );// 申证单元
			list.addAll(a);
			
			List<Food> foods = cunit.getFoods();
			for(int j = 0; j < foods.size(); j++){
				Food food = foods.get(j);
				int baseY = tay + tlht + tlh * j ; 
				// 序号
				list.add(new PrintTextObject(j+1+"", font, tax + tnx ,  baseY));
				
				// 食品
				list.add(new PrintTextObject(food.getName(), font, tax + tfx ,baseY));
				list.add(new PrintTextObject(food.getUsed(), font, tax + tux , baseY));
			} 
		}
		
		
		
		// 底部发证日期 (此算法完全按拖动计算的)
		PrinterLocation local4 = loadLocation(paper, "ymd"); 
		font = local4.getFont();
		int fontSize = font.getSize();
		ax = local4.getAbsoluteX();
		ay = local4.getAbsoluteY();
		x = local4.getX();
		y = local4.getY();
		lh = local4.getLineheight();
		
		Date date = cer.getSendTime(); 
		String year = yearSDF.format(date); 
		String month = monthSDF.format(date);
		String day = daySDF.format(date);
		
		// year
		int year_x = ax + x;
		list.add(new PrintTextObject(year,font, year_x , ay + y));
		// month
		int month_x  = year_x + year.length()*fontSize + lh ;
		list.add(new PrintTextObject(month,font,  month_x, ay + y));
		//day
		int day_x = month_x + month.length()* fontSize + lh;
		list.add(new PrintTextObject(day,font,  day_x , ay + y ));
		
		
		
		return list;
	}
	
	
	
	
	/**
	 * 字符串拆分为打印字符串对象
	 * @param String text 字符串
	 * @param int size   每行字数
	 * @param int x      打印X基位置
	 * @param int y      打印Y基位置
	 * @return 
	 * */
	public static List<PrintTextObject> splitTextString(String text, Font font,  int size, int x, int y){ 
		List<PrintTextObject> list = new ArrayList<PrintTextObject>(5);
		int y_step = 0;
		int index = 0;//截取索引值
		String tempStr = "";
		while(index < text.length()){
			int a = index + size;
			if(a <= text.length()){
				tempStr = text.substring(index, a);
				index  += size;
			}else{
				tempStr = text.substring(index, text.length());
				index  += size;
			}
			list.add(new PrintTextObject(tempStr,font, x, y+y_step)); 
			y_step += font.getSize();
		}
		return list;
	}
	
	
	/**
	 * 读取位置信息
	 * @param paper
	 * @param module
	 * @return
	 */
	public static PrinterLocation loadLocation(String paper, String module){
		PrinterConfig config = PrinterConfig.getInstance();
	
		String x = config.get(paper+"."+module+".x");
		String y = config.get(paper+"."+module+".y");
		String lineheight = config.get(paper+"."+module+".lineheight");
		String font = config.get(paper+"."+module+".font");
		String size = config.get(paper+"."+module+".size");
		String style = config.get(paper+"."+module+".style");
		String ax = config.get(paper+"."+module+".absolutex" );
		String ay = config.get(paper+"."+module+".absolutey" );
		 
		PrinterLocation location = new PrinterLocation();
		location.setFont(new Font(font,Integer.parseInt(style),Integer.parseInt(size)));
		location.setLineheight(Integer.parseInt(lineheight));
		location.setX(Integer.parseInt(x));
		location.setY(Integer.parseInt(y));
		location.setAbsoluteX(Integer.parseInt(ax));
		location.setAbsoluteY(Integer.parseInt(ay));
		return location;
		
	}
	
	

	/**
	 * 获取申证单元
	 * @return
	 */
	public static PrinterTableLocation loadTableLocation(String paper, String module){
		PrinterConfig config = PrinterConfig.getInstance();
		String foldline = config.get(paper+"."+module+".foldline");
		String numberx = config.get(paper+"."+module+".numberx" );
		String unitx = config.get(paper+"."+module+".unitx");
		String foodx = config.get(paper+"."+module+".foodx");
		String usedx = config.get(paper+"."+module+".usedx");
		String lineheight = config.get(paper+"."+module+".lineheight");
		String font = config.get(paper+"."+module+".font");
		String size = config.get(paper+"."+module+".size");
		String style = config.get(paper+"."+module+".style");
		String ax = config.get(paper+"."+module+".absolutex");
		String ay = config.get(paper+"."+module+".absolutey");
		
		PrinterTableLocation location = new PrinterTableLocation();
		location.setFont(new Font(font,Integer.parseInt(style),Integer.parseInt(size)));
		location.setLineheight(Integer.parseInt(lineheight)); 
		location.setAbsoluteX(Integer.parseInt(ax));
		location.setAbsoluteY(Integer.parseInt(ay));

		location.setFold_line(Integer.parseInt(foldline));
		location.setNumberX(Integer.parseInt(numberx));
		location.setUnitX(Integer.parseInt(unitx));
		location.setFoodX(Integer.parseInt(foodx));
		location.setUsedX(Integer.parseInt(usedx));
		
		
		return location;
	}
 
	
	public static void test(List<PrintTextObject> p){
		Image img = null;
		try {
			img = ImageIO.read(new File("demo.jpg"));
		} catch (IOException e) { 
			e.printStackTrace();
		}
				 
		BufferedImage buffImg = new BufferedImage(2480, 3248,  BufferedImage.TYPE_INT_RGB); 
		//创建画笔 
				Graphics2D g = buffImg.createGraphics();   
		 
		 
		 
		        g.setColor(Color.black); 
		        g .drawImage(img,0,0,2480,3248,0,0,2480,3248,null); 
		        	         
		        Iterator<PrintTextObject> it2 = p.iterator(); 
		        while(it2.hasNext()){ 		        	PrintTextObject pto = it2.next(); 
		        	g.setFont(pto.getFont());
		        			            g.drawString(pto.getText(), pto.getX(), pto.getY()); 		        } 				 		        try {
					ImageIO.write(buffImg, "jpg", new File("c:\\a\\"+Math.random()+".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
}