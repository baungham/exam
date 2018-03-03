package org.marker.certificate.component.printer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import org.marker.certificate.Main;
import org.marker.certificate.component.MoveJPanel;



/**
 * 企业信息移动面板
 * @author marker
 * @version 1.0
 */
public class EnterpriseMovePanel extends MoveJPanel {
 
	private static final long serialVersionUID = -138208147759902878L;
 

	
	/**
	 * Create the panel.
	 */
	public EnterpriseMovePanel(Main frame) {
		super(frame);
		setToolTipText("企业证书基本信息");
		// 初始化位置
		this.setLocation(100, 100);
		JMenuItem item = new JMenuItem("模块设置");
		item.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) { 
				dialog.setVisible(true);
				menu.setVisible(false);
			}
		});
		menu.add(item); 
		
				
		initData();// 初始化
		repaint();
	}
	

	
	
	
	
	// 组价初始化完成，就初始化这个
	public void initData(){
		dialog = new InfoSetupDialog(EnterpriseMovePanel.this); 
	}
	
	
	
	@Override
	public void paint(Graphics g) { 
		super.paint(g); 
		g.setFont(currentFont);
		int fonSize = currentFont.getSize();
		g.setColor(Color.black);
		
		g.drawString("产品名称", relative_x, relative_y);
		g.drawString("住所", relative_x, relative_y+(1*line_height));
		g.drawString("生产地址", relative_x, relative_y+(2*line_height));
		g.drawString("检验方式", relative_x, relative_y+(3*line_height));
		g.drawString("证书编号", relative_x, relative_y+(4*line_height));
		g.drawString("检验方式", relative_x, relative_y+(5*line_height));
		g.drawString("发证日期", relative_x, relative_y+(6*line_height)); 
		
		this.setSize(new Dimension(relative_x+fonSize*4 + 20, relative_y + 6*line_height + 20));
	}
	
}
