package org.marker.certificate;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.marker.certificate.bean.User;
import org.marker.certificate.service.IUserService;
import org.marker.certificate.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 登录界面
 * 在用户没有登录的情况下会提示登录
 * 【这个功能最后做】
 * @author marker
 * @version 1.0
 */
public class LoginDialog extends JDialog {
	// 日志记录器
	protected static final Logger log = LoggerFactory.getLogger(LoginDialog.class);
	
	
	private static final long serialVersionUID = 124191790818101259L;
	
	// 内容区域
	private final JPanel contentPanel = new JPanel();

  
	// 窗口宽度
	private int D_WIDTH = 450;
	// 窗口高度
	private int D_HEIGHT = 300;
	private JTextField textField_username;
	private JPasswordField passwordField_pass;
	
	
	
	// loginService
	private IUserService loginService = new UserServiceImpl();

	// 主界面
	private Main main;
	
	/**
	 * Create the dialog.
	 */
	public LoginDialog(final Main main) {
		this.main = main;
		setFont(new Font("微软雅黑", Font.PLAIN, 12));
		setTitle("登录系统");
		this.setResizable(false);
		this.setModal(true);// 模态
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		// 设置窗口居中
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension winSize = kit.getScreenSize();
		setLocation(winSize.width/2 - D_WIDTH/2, winSize.height/2 - D_HEIGHT/2);  
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("用户名：");
		label.setFont(new Font("微软雅黑", Font.BOLD, 12));
		label.setBounds(80, 129, 54, 15);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("密   码：");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 12));
		label_1.setBounds(80, 171, 54, 15);
		contentPanel.add(label_1);
		
		textField_username = new JTextField("");
		textField_username.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_username.setBounds(144, 126, 165, 21);
		contentPanel.add(textField_username);
		textField_username.setColumns(10);
		
		passwordField_pass = new JPasswordField("");
		passwordField_pass.setBounds(144, 168, 163, 21);
		contentPanel.add(passwordField_pass);
		
		JLabel lblNewLabel = new JLabel("");

		lblNewLabel.setIcon(new ImageIcon("resource/images/header_logo.png"));
		lblNewLabel.setBounds(0, 0, 394, 90);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("登 录"); 
				okButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
				okButton.addActionListener(new ActionListener() { 
					public void actionPerformed(ActionEvent e) {
						login();// 
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("取 消"); 
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();// 关闭登录窗体
						Main.closeFrame();
					}
				});
				cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
				buttonPane.add(cancelButton);
			}
		}
		
		
		// 关闭
		this.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent e) {
				dispose();// 关闭登录窗体
				Main.closeFrame();
			}
		});
		this.setVisible(true);
	}
	
	
	/**
	 * 登录系统
	 */
	public void login(){
		String name = this.textField_username.getText();
		String pass = new String(passwordField_pass.getPassword());
		
//		User user = loginService.login(name, pass);
		User user = new User();
		user.setName("admin");
		if(user != null){// 登录成功 
			main.setUserName(user.getName());// 设置主界面的登录名
			LoginDialog.this.dispose();
			log.info("a user login system username={} loginstatus:{}",name,true);
			main.setVisible(true); 
		}else{
			log.error("a user login system username={} loginstatus:{}",name,false);
			JOptionPane.showMessageDialog(this, "用户名或者密码错误!");
		}
		
	}
}
