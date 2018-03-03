package org.marker.certificate.printer;

import java.awt.Font;
import java.io.Serializable;


/**
 * 打印位置描述
 * @author marker
 * @version 1.0
 */
public class PrinterLocation implements Serializable {

	private static final long serialVersionUID = 681270384950050969L;

	
	// 文字相对面板位置
	private int x;
	private int y;
	
	// 绝对定位
	private int absoluteX;
	private int absoluteY;
	
	// 文字行高
	private int lineheight;
	// 文字字体
	private Font font;
	
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getLineheight() {
		return lineheight;
	}
	public void setLineheight(int lineheight) {
		this.lineheight = lineheight;
	}
	public Font getFont() {
		return font;
	}
	public void setFont(Font font) {
		this.font = font;
	}
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
	
	
	
	
}
