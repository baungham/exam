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
import org.marker.certificate.component.MoveJPanel;
import org.marker.certificate.view.listener.DocumentAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateSetupDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4491358286592399464L;
	
	// 日志记录器
	protected Logger log = LoggerFactory.getLogger(DateSetupDialog.class);
	

	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	JLabel label_font;
	

	private int D_WIDTH = 386;
	private int D_HEIGHT = 201;


	/**
	 * Create the dialog.
	 * @param enterpriseMovePanel 
	 */
	public DateSetupDialog(final MoveJPanel panel) {
		setTitle("模块设置");
		setModal(true);
		setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(D_WIDTH,D_HEIGHT );
		// 设置窗口居中
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension winSize = kit.getScreenSize();
		setLocation(winSize.width/2 - D_WIDTH/2, winSize.height/2 - D_HEIGHT/2);  
		
		
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
	
		
		JLabel lblNewLabel = new JLabel("字距：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel.setBounds(23, 24, 54, 15);
		contentPanel.add(lblNewLabel);
		
		JLabel label = new JLabel("字体：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBounds(23, 61, 54, 15);
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
		button.setBounds(293, 70, 67, 37);
		contentPanel.add(button);
		
		textField = new JTextField(panel.getLine_height());
		textField.getDocument().addDocumentListener(new DocumentAdapter(){ 
			@Override
			public void insertUpdate(DocumentEvent e) {
				int val = 30;
				try{
					  Document doc = e.getDocument();  
				        String s = doc.getText(0, doc.getLength()); //返回文本框输入的内容 
					val = Integer.parseInt(s);
				}catch(Exception ee){log.error("设置行高异常!", ee); } 
				panel.setLine_height(val);
				panel.repaint();// 重绘
			} 
			@Override
			public void removeUpdate(DocumentEvent e) {
				//insertUpdate(e);
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
		panel_1.setBounds(69, 61, 214, 92);
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
		button_1.setBounds(293, 117, 67, 36);
		contentPanel.add(button_1);
	}
	
	
	public void setFontStyle(Font font){
		label_font.setText(font.getFamily()+" " + font.getSize());
		label_font.setFont(font);  
	}
}
