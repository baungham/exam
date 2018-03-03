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
 *  有效期拖动模块
 * 
 * @author marker
 * @version 1.0
 */
public class DateTimeMovePanel extends MoveJPanel{
 
	private static final long serialVersionUID = -5696086631196798516L;
	
	
	
	// 组价初始化完成，就初始化这个
	public void initData(){
		dialog = new InfoSetupDialog(DateTimeMovePanel.this); 
	}
	
	
	
	public DateTimeMovePanel(Main frame) {  
		super(frame);
		setToolTipText("有效期");
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
		
		
		this.line_height = 0;
		initData();// 初始化
		repaint();
	}
	
	
	// 重写绘制
	@Override
	public void paint(Graphics g) { 
		super.paint(g); 
		g.setFont(currentFont);
		int fonSize = currentFont.getSize();
		g.setColor(Color.black);
		
		g.drawString("2015年01月02日", relative_x, relative_y);
		
		this.setSize(new Dimension(relative_x+fonSize*8 + 20, relative_y + 1*line_height + 20));
	}

	
	


}
