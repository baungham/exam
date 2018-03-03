package org.marker.certificate.printer;

import java.awt.Font;



/**
 * 打印文字包含内容、位置信息
 * 应该设置字体
 * @author marker
 * @version 1.0
 */
public final class PrintTextObject {

	// 内容
	private String text;
	// 字体样式
	private Font font;
	// x
	private int x;
	// y
	private int y;
	
	
	
	public PrintTextObject(String text,Font font, int x, int y) {
		super();
		this.font = font;
		this.text = text;
		this.x = x;
		this.y = y;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
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

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
	
	
	
	
}
