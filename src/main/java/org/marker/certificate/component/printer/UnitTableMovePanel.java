package org.marker.certificate.component.printer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;

import org.marker.certificate.Main;
import org.marker.certificate.bean.Food;
import org.marker.certificate.bean.Unit;
import org.marker.certificate.component.MoveJPanel;
import org.marker.certificate.printer.PrinterTableLocation;

/**
 * 申证单元 拖动模块
 * 
 * @author marker
 * @version 1.0
 */
public class UnitTableMovePanel extends MoveJPanel{

	
	private static final long serialVersionUID = 1834977448813078012L;

	
	// 模拟数据
	private List<Unit> units = new ArrayList<Unit>();
	
	private UnitTableSetupDialog dialog;
	
	// 组价初始化完成，就初始化这个
	public void initData(){
		dialog = new UnitTableSetupDialog(this);
		
		
		
		List<Food> list = new ArrayList<Food>();
		list.add(new Food("吃的1","使用标准1"));
		list.add(new Food("吃的2","使用标准2"));
		list.add(new Food("吃的3","使用标准3"));
		
		List<Food> list2 = new ArrayList<Food>();
		list2.add(new Food("用的1","使用标准1"));
		list2.add(new Food("用的2","使用标准2"));
		list2.add(new Food("用的3","使用标准3"));
		
		List<Food> list3 = new ArrayList<Food>();
		list3.add(new Food("喝的1","使用标准1"));
		list3.add(new Food("喝的2","使用标准2"));
		list3.add(new Food("喝的3","使用标准3"));
		
		
		
		
		units.add(new Unit("如阿斯顿拿伞电脑三的纳税年度拿伞大声道啊实打实",list));
		units.add(new Unit("b",list2));
		units.add(new Unit("v",list3));
		
		
		
	}
	
		
		
	public UnitTableMovePanel(Main frame) {  
		super(frame);
		setToolTipText("申证单元");
		// 初始化位置
		this.setLocation(100, 100);
		JMenuItem item = new JMenuItem("模块设置");
		item.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) { 
				dialog.setVisible(true);
			}
		});
		menu.add(item); 
		this.line_height = 35;
		
		
		 
		initData();// 初始化
		repaint();
	}
	
	
	// 折行
	int fold_line = 9;
	
	// 列位置
	int numberX = 0;
	int unitX = 50;
	int foodX = 230;
	int usedX = 300;// 使用标准
	
	// 重写绘制
	@Override
	public void paint(Graphics g) { 
		super.paint(g); 
		g.setFont(currentFont);
		int fonSize = currentFont.getSize();// 字体大小
		g.setColor(Color.black);
		 
		int width = 0  ;
		int height = 0 ;
		for (int i = 0; i < units.size(); i++) {//
			Unit unit = units.get(i); // 获取一个申证单元 
			int y = 20 +line_height * i * 3;
			
			// 绘制申证单元
			graphicsText(g, unit.getDesc(),fold_line, unitX, y);
			
			List<Food> foods = unit.getFoods();
			for(int j=0; j < foods.size();j++){
				Food food = foods.get(j);
				 
				int baseY = y + line_height * j; 
				// 序号
				g.drawString((j+1)+"", numberX, baseY);
				
				 
				
			
				// 食品品种以及使用标准 
				g.drawString(food.getName(), foodX, baseY);
				g.drawString(food.getUsed(), usedX, baseY);
				
				// 计算矩形框高宽
				width  = numberX + 370+food.getUsed().length()*fonSize + fonSize;
				height = baseY  + fonSize +fonSize;
			} 
		}
		
		this.setSize(new Dimension(width , height));
	}

	
	
	/**
	 * 字符串拆分为打印字符串对象
	 * @param String text 字符串
	 * @param int size   每行字数
	 * @param int x      打印X基位置
	 * @param int y      打印Y基位置
	 * */
	public void graphicsText(Graphics g, String text, int size, int x, int y){ 
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
			g.drawString(tempStr, x, y+y_step);
			y_step += currentFont.getSize();
		}
	}

	


	/**
	 * 获取位置信息
	 * @return
	 */
	public PrinterTableLocation getPrinterTableLocation() {
		PrinterTableLocation pl = new PrinterTableLocation();
		pl.setUnitX(unitX);
		pl.setUsedX(usedX);
		pl.setFoodX(foodX);
		pl.setNumberX(numberX);
		pl.setLineheight(line_height);
		pl.setFold_line(fold_line);
		pl.setFont(currentFont);
		pl.setAbsoluteX(getLocation().x);
		pl.setAbsoluteY(getLocation().y);
		return pl;
	}


	/**
	 * 设置位置信息
	 * @param loadTableLocation
	 */
	public void setPrinterTableLocation(PrinterTableLocation l) {
		unitX = l.getUnitX();
		usedX = l.getUsedX();
		foodX = l.getFoodX();
		numberX = l.getNumberX();
		line_height = l.getLineheight();
		fold_line = l.getFold_line();
		currentFont = l.getFont();
		// 设置显示位置
		setLocation(l.getAbsoluteX(), l.getAbsoluteY());
		
		repaint();// 重新绘制 
	}

	


	public int getNumberX() {
		return numberX;
	}



	public void setNumberX(int numberX) {
		this.numberX = numberX;
	}



	public int getUnitX() {
		return unitX;
	}



	public void setUnitX(int unitX) {
		this.unitX = unitX;
	}



	public int getFoodX() {
		return foodX;
	}



	public void setFoodX(int foodX) {
		this.foodX = foodX;
	}



	public int getUsedX() {
		return usedX;
	}



	public void setUsedX(int usedX) {
		this.usedX = usedX;
	}



	public int getFold_line() {
		return fold_line;
	}



	public void setFold_line(int fold_line) {
		this.fold_line = fold_line;
	}




	
	
}
