package org.marker.certificate.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.marker.certificate.bean.City;
import org.marker.certificate.bean.Enterprise;
import org.marker.certificate.bean.ServiceMessage;
import org.marker.certificate.service.ICityService;
import org.marker.certificate.service.IEnterpriseService;
import org.marker.certificate.service.impl.CityServiceImpl;
import org.marker.certificate.view.EnterprisePanel;




/**
 * 添加企业
 * @author marker
 * @version 1.0
 */
public class EnterpriseAddDialog extends JDialog {
 
	private static final long serialVersionUID = 1089279433599822686L;
	
	private final JPanel contentPanel = new JPanel();

	
	  
	// 窗口宽度
	private int D_WIDTH = 368;
	// 窗口高度
	private int D_HEIGHT = 337;
	private JTextField textField;
	private JTextField textField_1;
	private JEditorPane editorPane;
	
	// 城市
	private ICityService serviceCity = new CityServiceImpl();
	
	// 城市
	private JComboBox comboBox;
	
	
	// 初始化依赖数据
	public void initDependentData(){ 
		
		
		// 初始化成都市信息。1代表成都
		List<City> list = serviceCity.queryByProvinceId(1);
		DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
		for(City c : list){ 
			dcbm.addElement(c);
		} 
		comboBox.setModel(dcbm); 
	}
		
	/**
	 * Create the dialog.
	 * @param service 
	 * @param enterprisePanel 
	 */
	public EnterpriseAddDialog(final EnterprisePanel enterprisePanel, final IEnterpriseService service) {
		setTitle("添加企业");	 
		this.setModal(true);
		this.setResizable(false);//设置不可改变大小
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
		setSize(D_WIDTH, D_HEIGHT);
		// 设置窗口居中
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension winSize = kit.getScreenSize();
		setLocation(winSize.width/2 - D_WIDTH/2, winSize.height/2 - D_HEIGHT/2);  
		
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("企业名称：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBounds(29, 34, 67, 15);
		contentPanel.add(label);
		
		JLabel lblNewLabel = new JLabel("所  在  地：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel.setBounds(29, 62, 68, 15);
		contentPanel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField.setBounds(106, 31, 148, 21);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		comboBox.setBounds(107, 59, 97, 21);
		contentPanel.add(comboBox);

		JLabel label_1 = new JLabel("企业描述：");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_1.setBounds(29, 139, 67, 15);
		contentPanel.add(label_1);
		
		editorPane = new JEditorPane();
		editorPane.setBounds(107, 139, 191, 70);
		contentPanel.add(editorPane);
		
		JLabel label_2 = new JLabel("生产地址：");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_2.setBounds(29, 97, 67, 15);
		contentPanel.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(106, 90, 191, 21);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确 定"); 
				okButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Enterprise ee = new Enterprise();
						
						ee.setName(textField.getText());
						
						City c = (City) comboBox.getSelectedItem();
						
						ee.setCityId(c.getId());
						ee.setProvinceId(c.getPid());// 省份ID
						ee.setAddress(textField_1.getText());
						ee.setDesc(editorPane.getText());
						
						ServiceMessage msg = service.save(ee);
						JOptionPane.showMessageDialog(null, msg.getMessage()); 
						if(msg.isStatus()){
							enterprisePanel.initData();
							setVisible(false);
							reset();// 数据初始化
						} 
						
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("取 消");
				cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		} 
		initDependentData();
	}
	
	

	/**
	 * 重置数据
	 */
	public void reset() {
		textField.setText("");
		textField_1.setText("");
		editorPane.setText("");
		comboBox.setSelectedIndex(0);
		
	}
}
