package org.marker.certificate.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;

import org.marker.certificate.Main;
import org.marker.certificate.component.printer.InfoSetupDialog;
import org.marker.certificate.printer.PrinterLocation;


/**
 * 可拖拽JPanel,继承此类可实现拖拽功能
 * @author marker
 * @version 1.0
 */
public class MoveJPanel extends JPanel implements MouseListener, MouseMotionListener {
 
	private static final long serialVersionUID = 1L;
	

	// 字体
	public Font currentFont = new Font("微软雅黑", Font.PLAIN, 18);
	
	
	// 拖动
	private boolean dragged = false;
	// JPanel位置
	private Point currentPoint;
	// 鼠标的位置
	private Point mousePoint;
	

	// 设置菜单
	protected JPopupMenu menu = new JPopupMenu();
	
	// 设置窗口
	protected InfoSetupDialog dialog;
	
	
	// 行高
	protected int line_height = 60;
	
	// 相对当前面板位置定位X
	protected int relative_x = 20;
	// 相对当前面板位置定位Y
	protected int relative_y = 30;
	
	
	// 主界面
	protected Main frame;
	
	public MoveJPanel(Main frame) { 
		this.frame = frame;
		this.setOpaque(true);
		
		this.setBorder(new LineBorder(Color.red, 1));
		this.setBackground(null);
		this.setLayout(null);// 绝对布局
		
		// 添加到组件中
		this.setComponentPopupMenu(menu);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(dragged){
			int new_x = currentPoint.x +  e.getXOnScreen() - mousePoint.x;
			int new_y = currentPoint.y +  e.getYOnScreen() - mousePoint.y;
			this.setLocation(new_x, new_y); 
	 		
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) { 
	
	}

	@Override
	public void mouseClicked(MouseEvent e) { 
	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int key = e.getButton(); 
		switch (key) {
		case 1:// 左键
			currentPoint = this.getLocation(); 
			mousePoint   = new Point(e.getXOnScreen(),e.getYOnScreen());
			frame.setCursor(new Cursor(Cursor.MOVE_CURSOR));//设置鼠标样式 
			dragged = true;
			break;
		case 3 : 
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// 设置鼠标样式
		dragged = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * 获取位置信息
	 * @return
	 */
	public PrinterLocation getPrinterLocation() {
		PrinterLocation pl = new PrinterLocation();
		pl.setFont(currentFont);
		pl.setLineheight(line_height);
		pl.setX(relative_x);
		pl.setY(relative_y);
		pl.setAbsoluteX(getLocation().x);
		pl.setAbsoluteY(getLocation().y);
		return pl;
	}
	
	
	
	
	

	public void setLine_height(int line_height) {
		this.line_height = line_height;
	}

	/**
	 * 设置面板位置信息
	 * @param location
	 */
	public void setPrinterLocation(PrinterLocation loc) {
		currentFont = loc.getFont();
		line_height = loc.getLineheight();
		relative_x = loc.getX();
		relative_y = loc.getY();
		
		// 设置显示位置
		setLocation(loc.getAbsoluteX(), loc.getAbsoluteY());
		
		repaint();// 重新绘制
	}




	public int getLine_height() {
		return line_height;
	}



	public Font getCurrentFont() {
		return currentFont;
	}



	public void setCurrentFont(Font currentFont) {
		this.currentFont = currentFont;
	}

}
