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
 * 年月日
 * @author marker
 * @version 1.0
 */
public class YearMonthDayMovePanel extends MoveJPanel {
 
	private static final long serialVersionUID = 1L;

	
	// 设置窗口
	private DateSetupDialog dialog;
	
	
	private void initData() { 
		dialog = new DateSetupDialog(this); 
	}

	
	
	public YearMonthDayMovePanel(Main frame) {
		super(frame);
		
		setToolTipText("证书底部年月日");
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
		
		this.setSize(100, 100);
		this.line_height = 30;
		this.setAlignmentX(10);
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
		/* 模拟文字 */
		String year = "2015";
		String month = "01";
		String day = "25";
		
		g.drawString(year, relative_x, relative_y);
		int yearWidth = year.length()*fonSize;
		int range = relative_x + yearWidth + line_height;
		g.drawString(month, range, relative_y);
		int mouthWidth = month.length() * fonSize;
		range = range + mouthWidth + line_height;
		g.drawString(day, range, relative_y);
		int dayWitdh = day.length() * fonSize; 
		
		this.setSize(new Dimension(relative_x + yearWidth + mouthWidth + dayWitdh + line_height*2, relative_y + fonSize));
	}
	
	
	
	

}
