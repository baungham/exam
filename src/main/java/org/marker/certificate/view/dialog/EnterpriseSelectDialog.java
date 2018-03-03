 package org.marker.certificate.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.marker.certificate.bean.Enterprise;
import org.marker.certificate.bean.Page;
import org.marker.certificate.service.impl.EnterpriseServiceImpl;

public class EnterpriseSelectDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2379250755037557316L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_keyword;
	private JList list ;
 
	// 企业业务对象
	private EnterpriseServiceImpl service = new EnterpriseServiceImpl(); 
	
	
	
	
	// 窗口宽度
	private int D_WIDTH = 200;
	// 窗口高度
	private int D_HEIGHT = 300;
	
	
	
	
	
	// 初始化依赖数据
	public void initDependentData(){  
		
		
		
		
	}

	/**
	 * Create the dialog.
	 * @param certificateAddDialog 
	 */
	public EnterpriseSelectDialog(final CertificateAddDialog certificateAddDialog) {
		setTitle("选择企业");
		this.setResizable(false); 
		this.setModal(true);
		setBounds(100, 100, 200, 300);
		
		setSize(D_WIDTH, D_HEIGHT);
		// 设置窗口居中
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension winSize = kit.getScreenSize();
		setLocation(winSize.width/2 - D_WIDTH/2, winSize.height/2 - D_HEIGHT/2);  
		
		
		
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		
		
		
		  list = new JList();
		  list.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		list.setLocation(10, 42);
		list.setSize(174, 197);
		contentPanel.add(list);
		
		
		
		
		
		
		{
			textField_keyword = new JTextField();
			textField_keyword.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			textField_keyword.getDocument().addDocumentListener(new DocumentListener() {
				
				@Override
				public void removeUpdate(DocumentEvent e) {
					initData();
				}
				
				@Override
				public void insertUpdate(DocumentEvent e) {
					initData();
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) { 
				}
			});
			textField_keyword.setBounds(10, 10, 174, 21);
			contentPanel.add(textField_keyword);
			textField_keyword.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确定");
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Enterprise enter =  (Enterprise)list.getSelectedValue();
						if(enter != null){ 
							certificateAddDialog.setEnterpriseId(enter.getId());
						}else{
							JOptionPane.showMessageDialog(null, "获取列表数据失败!");
						}
						setVisible(false);
					}
				});
				okButton.setFont(new Font("微软雅黑", Font.PLAIN, 12)); 
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("取消");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 12)); 
				buttonPane.add(cancelButton);
			}
		}
		
		initDependentData();// 依赖数据
		initData();
	}

	// 初始化
	public void initData(){
		String keyword = textField_keyword.getText();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ename", keyword);
		
		Page<Enterprise> page = service.queryByPage(1, 10, params);
		
		List<Enterprise> li =	page.getData();
		
		DefaultListModel lm = new DefaultListModel();
		Iterator<Enterprise> it = li.iterator();
		while(it.hasNext()){
			Enterprise e = it.next();
			lm.addElement(e);
		}
		list.setModel(lm);
	}
}
