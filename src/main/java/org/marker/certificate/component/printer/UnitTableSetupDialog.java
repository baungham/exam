package org.marker.certificate.component.printer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.text.Document;

import org.marker.certificate.component.MQFontChooser;
import org.marker.certificate.view.listener.DocumentAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnitTableSetupDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4491358286592399464L;
	
	// 日志记录器
	protected Logger log = LoggerFactory.getLogger(InfoSetupDialog.class);
	

	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	JLabel label_font;
	

	private int D_WIDTH = 386;
	private int D_HEIGHT = 201;
	private JTextField textField_number;
	private JTextField textField_unit;
	private JTextField textField_food;
	private JTextField textField_used;

	  JSlider slider_unit ;
	  JSlider slider_used ;
	  JSlider slider_food ;
	  private JTextField textField_count;
	  
	  
	  
	 private UnitTableMovePanel panel;
	/**
	 * Create the dialog.
	 * @param enterpriseMovePanel 
	 */
	public UnitTableSetupDialog( UnitTableMovePanel a) {
		this.panel = a;
		setTitle("模块设置");
		setModal(true);
		setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(386,364 );
		// 设置窗口居中
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension winSize = kit.getScreenSize();
		setLocation(winSize.width/2 - D_WIDTH/2, winSize.height/2 - D_HEIGHT/2);  
		
		
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
	
		
		JLabel lblNewLabel = new JLabel("行  高：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel.setBounds(20, 25, 54, 15);
		contentPanel.add(lblNewLabel);
		
		JLabel label = new JLabel("字体：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBounds(23, 234, 54, 15);
		contentPanel.add(label);
		
	 
		
		
		JButton button = new JButton("选择");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 打开一个字体选择器窗口，参数为父级所有者窗体。返回一个整型，代表设置字体时按下了确定或是取消，可参考MQFontChooser.APPROVE_OPTION和MQFontChooser.CANCEL_OPTION
				
				// 构造字体选择器，参数字体为预设值
				MQFontChooser fontChooser = new MQFontChooser(panel.getCurrentFont()); 
				int returnValue = fontChooser.showFontDialog(null);
				// 如果按下的是确定按钮
				if (returnValue == MQFontChooser.APPROVE_OPTION) {
					// 获取选择的字体
					Font font = fontChooser.getSelectFont();
					//  
					setFontStyle(font);
					panel.setCurrentFont(font);
					panel.repaint();// 重绘制
				} 
				
			}
		});
		button.setBounds(293, 243, 67, 37);
		contentPanel.add(button);
		
		textField = new JTextField(panel.getLine_height());
		textField.getDocument().addDocumentListener(new DocumentAdapter(){ 
			@Override
			public void insertUpdate(DocumentEvent e) {
				int val = 30;
				
				try{
					Document doc = e.getDocument();  
				    String s = doc.getText(0, doc.getLength()); //返回文本框输入的内容 
					if(!"".equals(s)){
						val = Integer.parseInt(s);
						panel.setLine_height(val);
						panel.repaint();// 重绘
					}
				}catch(Exception ee){
					log.error("设置行高异常!", ee);
				}
			}
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				insertUpdate(e);
			}
		});
		textField.setBounds(276, 24, 47, 21);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		
		
		
		final JSlider slider = new JSlider();
		slider.addChangeListener(new ChangeListener() { 
			@Override
			public void stateChanged(ChangeEvent e) { 
				textField.setText(slider.getValue()+"");
			}
		});
		slider.setBounds(69, 24, 200, 23);
		slider.setValue(panel.getLine_height());
		contentPanel.add(slider);
		
		
		
		
		JLabel lblPx = new JLabel("px");
		lblPx.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblPx.setBounds(333, 24, 25, 15);
		contentPanel.add(lblPx); 
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(69, 234, 214, 92);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		Font font = panel.getCurrentFont();
		label_font = new JLabel(font.getFamily()+" " + font.getSize());
		label_font.setBounds(0, 0, 214, 92);
		panel_1.add(label_font);
		label_font.setHorizontalAlignment(SwingConstants.CENTER);
		label_font.setFont(font); 
		
		
		JButton button_1 = new JButton("确定");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		button_1.setBounds(293, 290, 67, 36);
		contentPanel.add(button_1);
		
		JLabel label_1 = new JLabel("序号列：");
		label_1.setBounds(20, 55, 54, 15);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("单元列：");
		label_2.setBounds(20, 90, 83, 15);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel("食品列：");
		label_3.setBounds(20, 123, 54, 15);
		contentPanel.add(label_3);
		
		JLabel label_4 = new JLabel("使用列：");
		label_4.setBounds(20, 150, 54, 15);
		contentPanel.add(label_4);
		
		
		// 序号
		final JSlider slider_number = new JSlider();
		slider_number.addChangeListener(new ChangeListener() { 
			@Override
			public void stateChanged(ChangeEvent e) { 
				textField_number.setText(slider_number.getValue()+"");
				int width = label_font.getFont().getSize();
				textField_unit.setText(width + slider_unit.getValue() + slider_number.getValue()+"");
				int width2 = label_font.getFont().getSize() * panel.getFold_line();
				textField_food.setText(width2+slider_food.getValue()+slider_unit.getValue()+slider_number.getValue()+"");
			
			}
		});
		slider_number.setBounds(69, 52, 200, 23);
		contentPanel.add(slider_number);
		
		slider_unit = new JSlider();
		
		slider_unit.setBounds(69, 87, 200, 23);
		slider_unit.addChangeListener(new ChangeListener() { 
			@Override
			public void stateChanged(ChangeEvent e) { 
				int width = label_font.getFont().getSize();
				textField_unit.setText(width + slider_unit.getValue() + slider_number.getValue()+"");
				int width2 = label_font.getFont().getSize() * panel.getFold_line();
				textField_food.setText(width2+slider_food.getValue()+slider_unit.getValue()+slider_number.getValue()+"");
			
			}
		});
		contentPanel.add(slider_unit);
		
		    slider_food = new JSlider();
		slider_food.setBounds(69, 120, 200, 23);
		slider_food.addChangeListener(new ChangeListener() { 
			@Override
			public void stateChanged(ChangeEvent e) { 
				int width = label_font.getFont().getSize() * panel.getFold_line();
				textField_food.setText(width+slider_food.getValue()+slider_unit.getValue()+slider_number.getValue()+"");
			}
		});
		contentPanel.add(slider_food);
		
		    slider_used = new JSlider();
		slider_used.addChangeListener(new ChangeListener() { 
			@Override
			public void stateChanged(ChangeEvent e) { 
				int width = label_font.getFont().getSize() * panel.getFold_line();
				textField_used.setText(slider_used.getValue()+width+slider_food.getValue()+slider_unit.getValue()+slider_number.getValue()+"");
			}
		});
		slider_used.setBounds(69, 147, 200, 23);
		contentPanel.add(slider_used);
		
		textField_number = new JTextField();
		textField_number.getDocument().addDocumentListener(new DocumentAdapter(){ 
			@Override
			public void insertUpdate(DocumentEvent e) {
				int val = 0;
				try{
					Document doc = e.getDocument();  
				    String s = doc.getText(0, doc.getLength()); //返回文本框输入的内容 
					if(!"".equals(s)){
						val = Integer.parseInt(s);
						panel.setNumberX(val);
						panel.repaint();// 重绘
					}
				}catch(Exception ee){
					log.error("设置行高异常!", ee);
				}
			}
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				insertUpdate(e);
			}
		});
		textField_number.setBounds(276, 55, 47, 21);
		contentPanel.add(textField_number);
		textField_number.setColumns(10);
		
		textField_unit = new JTextField();
		textField_unit.setBounds(276, 87, 47, 21);
		textField_unit.getDocument().addDocumentListener(new DocumentAdapter(){ 
			@Override
			public void insertUpdate(DocumentEvent e) {
				int val = 0;
				try{
					Document doc = e.getDocument();  
				    String s = doc.getText(0, doc.getLength()); //返回文本框输入的内容 
					if(!"".equals(s)){
						val = Integer.parseInt(s);
						panel.setUnitX(val);
						panel.repaint();// 重绘
					}
				}catch(Exception ee){
					log.error("设置行高异常!", ee);
				}
			}
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				insertUpdate(e);
			}
		});
		contentPanel.add(textField_unit);
		textField_unit.setColumns(10);
		
		textField_food = new JTextField();
		textField_food.getDocument().addDocumentListener(new DocumentAdapter(){ 
			@Override
			public void insertUpdate(DocumentEvent e) {
				int val = 0;
				try{
					Document doc = e.getDocument();  
				    String s = doc.getText(0, doc.getLength()); //返回文本框输入的内容 
					if(!"".equals(s)){
						val = Integer.parseInt(s);
						panel.setFoodX(val);
						panel.repaint();// 重绘
					}
				}catch(Exception ee){
					log.error("设置行高异常!", ee);
				}
			}
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				insertUpdate(e);
			}
		});
		textField_food.setBounds(276, 120, 47, 21);
		contentPanel.add(textField_food);
		textField_food.setColumns(10);
		
		textField_used = new JTextField();
		textField_used.getDocument().addDocumentListener(new DocumentAdapter(){ 
			@Override
			public void insertUpdate(DocumentEvent e) {
				int val = 0;
				try{
					Document doc = e.getDocument();  
				    String s = doc.getText(0, doc.getLength()); //返回文本框输入的内容 
					if(!"".equals(s)){
						val = Integer.parseInt(s);
						panel.setUsedX(val);
						panel.repaint();// 重绘
					}
				}catch(Exception ee){
					log.error("设置行高异常!", ee);
				}
			}
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				insertUpdate(e);
			}
		});
		textField_used.setBounds(276, 147, 47, 21);
		contentPanel.add(textField_used);
		textField_used.setColumns(10);
		
		JLabel label_5 = new JLabel("px");
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_5.setBounds(333, 55, 25, 15);
		contentPanel.add(label_5);
		
		JLabel label_6 = new JLabel("px");
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_6.setBounds(333, 90, 25, 15);
		contentPanel.add(label_6);
		
		JLabel label_7 = new JLabel("px");
		label_7.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_7.setBounds(333, 123, 25, 15);
		contentPanel.add(label_7);
		
		JLabel label_8 = new JLabel("px");
		label_8.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_8.setBounds(333, 150, 25, 15);
		contentPanel.add(label_8);
		
		JLabel label_9 = new JLabel("申证单元文字折行(字)：");
		label_9.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_9.setBounds(20, 180, 136, 15);
		contentPanel.add(label_9);
		
		textField_count = new JTextField();
		textField_count.getDocument().addDocumentListener(new DocumentAdapter(){ 
			@Override
			public void insertUpdate(DocumentEvent e) {
				int val = 0;
				try{
					Document doc = e.getDocument();  
				    String s = doc.getText(0, doc.getLength()); //返回文本框输入的内容 
					if(!"".equals(s)){
						val = Integer.parseInt(s);
						panel.setFold_line(val);
						panel.repaint();// 重绘
					}
				}catch(Exception ee){
					log.error("设置折行数异常!", ee);
				}
			}
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				insertUpdate(e);
			}
		});
		
		
		textField_count.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_count.setBounds(153, 177, 66, 21);
		contentPanel.add(textField_count);
		textField_count.setColumns(10);
		
		JLabel label_10 = new JLabel("个");
		label_10.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_10.setBounds(229, 180, 54, 15);
		contentPanel.add(label_10);
	}
	
	
	public void setFontStyle(Font font){
		label_font.setText(font.getFamily()+" " + font.getSize());
		label_font.setFont(font);
	}
	
	
	
	
	@Override
	public void setVisible(boolean b) {
		
		textField_number.setText(panel.getNumberX()+"");
		textField_unit.setText(panel.getUnitX()+"");
		textField_food.setText(panel.getFoodX()+"");
		textField_used.setText(panel.getUsedX()+"");
		textField_count.setText(panel.getFold_line()+"");
		
		
		super.setVisible(b);
		
	}
	
	
	;
}
