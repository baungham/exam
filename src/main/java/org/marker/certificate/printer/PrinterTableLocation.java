package org.marker.certificate.printer;

import java.awt.Font;



/**
 * 表格打印
 * @author marker
 * @version 1.0
 */
public class PrinterTableLocation {

	
	
	
	// 绝对定位
	private int absoluteX;
	private int absoluteY;
	
	// 文字行高
	private int lineheight;
	
	// 申证单元折行字数
	private int fold_line;
	
	// 文字字体
	private Font font;
	
	
	// 列位置
	private int numberX = 0;
	private int unitX = 50;
	private int foodX = 200;
	private int usedX = 300;// 使用标准
	public int getAbsoluteX() {
		return absoluteX;
	}
	public void setAbsoluteX(int absoluteX) {
		this.absoluteX = absoluteX;
	}
	public int getAbsoluteY() {
		return absoluteY;
	}
	public void setAbsoluteY(int absoluteY) {
		this.absoluteY = absoluteY;
	}
	public int getFold_line() {
		return fold_line;
	}
	public void setFold_line(int fold_line) {
		this.fold_line = fold_line;
	}
	public Font getFont() {
		return font;
	}
	public void setFont(Font font) {
		this.font = font;
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
	public int getLineheight() {
		return lineheight;
	}
	public void setLineheight(int lineheight) {
		this.lineheight = lineheight;
	}
	
	
	
	
	
}
