package org.marker.certificate.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.marker.certificate.bean.ServiceMessage;
import org.marker.certificate.bean.User;
import org.marker.certificate.service.IUserService;
import org.marker.certificate.view.UserPanel;

public class UserAddDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 502247128878582860L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JEditorPane editorPane;
	
  
	// 窗口宽度
	private int D_WIDTH = 368;
	// 窗口高度
	private int D_HEIGHT = 337;
	
	
	/**
	 * Create the dialog.
	 * @param userPanel 
	 */
	public UserAddDialog(final UserPanel userPanel, final IUserService service) {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("添加用户"); 
		this.setModal(true);
		this.setResizable(false);//设置不可改变大小
		setSize(D_WIDTH, D_HEIGHT);
		// 设置窗口居中
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension winSize = kit.getScreenSize();
		setLocation(winSize.width/2 - D_WIDTH/2, winSize.height/2 - D_HEIGHT/2);  
		
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField.setBounds(114, 32, 151, 21);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		final JLabel label = new JLabel("用户名：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBounds(50, 35, 54, 15);
		contentPanel.add(label);
		
		final JLabel label_1 = new JLabel("密   码：");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_1.setBounds(50, 76, 54, 15);
		contentPanel.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_1.setBounds(114, 73, 151, 21);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("手   机：");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_2.setBounds(50, 114, 54, 15);
		contentPanel.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_2.setBounds(114, 111, 151, 21);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_3 = new JLabel("描   述：");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_3.setBounds(50, 150, 54, 15);
		contentPanel.add(label_3);
		
		  editorPane = new JEditorPane();
		editorPane.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		editorPane.setBounds(114, 142, 181, 77);
		contentPanel.add(editorPane);
		
		JLabel label_4 = new JLabel("状   态：");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_4.setBounds(50, 232, 54, 15);
		contentPanel.add(label_4);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(
				new Object[] {new Select("正常", true), new Select("禁用", false)}));
		comboBox.setBounds(114, 229, 71, 21);
		contentPanel.add(comboBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确 定");
				
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						User user = new User();
						user.setName(textField.getText());
						user.setPass(textField_1.getText());
						user.setPhone(textField_2.getText());
						user.setDesc(editorPane.getText());
						Select s = (Select) comboBox.getSelectedItem();
						
						user.setStatus(s.isValue());
						ServiceMessage msg = service.save(user);
						System.out.println(msg.getMessage());
						UserAddDialog.this.setVisible(false);
						userPanel.initData();// 数据初始化
						UserAddDialog.this.reset();
					}
				});
				okButton.setFont(new Font("微软雅黑", Font.PLAIN, 12)); 
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("取 消");
				cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) { 
						UserAddDialog.this.reset();
						UserAddDialog.this.setVisible(false);
					}
				}); 
				buttonPane.add(cancelButton);
			}
		}
	}
 
	
	
	/**
	 * 清空数据
	 */
	public void reset(){
		 textField.setText("");
		 textField_1.setText("");
		 textField_2.setText("");
		 editorPane.setText(""); 
	}
}

/**
 * 选项
 * @author marker
 * @version 1.0
 */
class Select{
	private String name;
	private boolean value;
	
	
	
	
 
	public Select(String name, boolean value) {
		super();
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isValue() {
		return value;
	}
	public void setValue(boolean value) {
		this.value = value;
	}
 
	@Override
	public String toString() { 
		return this.name;
	}
	
}
