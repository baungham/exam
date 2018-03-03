package org.marker.certificate.component;

import java.awt.Font;

import javax.swing.JLabel;


/***
 * 面包屑导航标签
 * @author marker
 * @version 1.0
 */
public class BreadcrumLabel extends JLabel {
 
	private static final long serialVersionUID = -5180908157038654860L;

	
	
	
	public BreadcrumLabel(String label) {
		super(label); 
		this.init();// 初始化
	}
	
	
	private void init(){
		this.setFont(new Font("微软雅黑", Font.PLAIN, 13));
	}
}
