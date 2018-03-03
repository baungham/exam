package org.marker.certificate.view.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.marker.certificate.bean.Certificate;
import org.marker.certificate.bean.City;
import org.marker.certificate.bean.Enterprise;
import org.marker.certificate.bean.Unit;
import org.marker.certificate.component.CertificateUnitPanel;
import org.marker.certificate.service.ICertificateService;
import org.marker.certificate.service.ICityService;
import org.marker.certificate.service.IEnterpriseService;
import org.marker.certificate.service.impl.CityServiceImpl;
import org.marker.certificate.service.impl.EnterpriseServiceImpl;
import org.marker.certificate.view.CertificatePanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.toedter.calendar.JCalendar;




/**
 * 证书查看
 * @author marker
 * @version 1.0
 */
public class CertificateLookDialog extends JDialog {
	
	// 日志记录器
	protected Logger log = LoggerFactory.getLogger(CertificateLookDialog.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 4915541855994014636L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_code;
	private JTextField textField_vmode;
 

	private JCalendar calendar = new JCalendar();
	private JTextField textField_sTime;
	private JTextField textField_eTime;
	private JScrollPane scrollPane ;// 申证单元
	
	private JPanel panel;// 申证单元Panel
	
	
	// 窗口宽度
	private int D_WIDTH = 573;
	// 窗口高度
	private int D_HEIGHT = 540;
	private JTextField textField_ename;
	private JTextField textField_address;
	private JComboBox comboBox_city;// 所在地区
	private JEditorPane editorPane_desc ;// 企业描述
	// 城市
	private ICityService serviceCity = new CityServiceImpl();
	// 企业
	private IEnterpriseService serviceEnterprise = new EnterpriseServiceImpl();
	
	private  ICertificateService service;
	
	
	
	// 格式化时间
	//private SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
	 
	private JTextField textField_product;
	
	
	// 初始化依赖数据
	public void initDependentData(){  
		
		// 初始化成都市信息。1代表成都
		List<City> list = serviceCity.queryByProvinceId(1);
		DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
		for(City c : list){ 
			dcbm.addElement(c);
		} 
		comboBox_city.setModel(dcbm); 
	}
	
	/**
	 * Create the dialog.
	 * @param service 
	 * @param certificatePanel 
	 */
	public CertificateLookDialog(final CertificatePanel certificatePanel, final ICertificateService service) {
		this.service = service;
		
		/* 添加日历组件 */
		calendar.setLocation(600, 0);
		calendar.setFont(new Font("微软雅黑", Font.PLAIN, 12)); 
		calendar.setSize(300, 170);
		calendar.setVisible(false);
		 
		this.setModal(true);
		contentPanel.add(calendar); 
		this.setResizable(false);//设置不可改变大小
		setTitle("查看证书");
		setSize(D_WIDTH, D_HEIGHT);
		// 设置窗口居中
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension winSize = kit.getScreenSize();
		setLocation(winSize.width/2 - D_WIDTH/2, winSize.height/2 - D_HEIGHT/2);  
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		
		
		
		/* 申证单元表头 开始 */

		Panel panel_1 = new Panel();
		panel_1.setBackground(new Color(220, 220, 220));
		panel_1.setBounds(0, 235, 569, 23);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_8 = new JLabel("申证单元");
		label_8.setFont(new Font("微软雅黑", Font.BOLD, 12));
		label_8.setBounds(85, 5, 48, 15);
		panel_1.add(label_8);
		
		JLabel lblNewLabel = new JLabel("食品品种明细");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 12));
		lblNewLabel.setBounds(247, 5, 83, 15);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("使用标准");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 12));
		lblNewLabel_1.setBounds(415, 5, 63, 15);
		panel_1.add(lblNewLabel_1);
		
	 
		
		JLabel label_9 = new JLabel("序号");
		label_9.setFont(new Font("微软雅黑", Font.BOLD, 12));
		label_9.setBounds(5, 5, 54, 15);
		panel_1.add(label_9);
		
		/* 申证单元表头 结束 */
		
		
		
		
		
		
		
		
		
		
		
		
		JLabel label = new JLabel("证书编号：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBounds(40, 33, 76, 15);
		contentPanel.add(label);
		
		textField_code = new JTextField();
		textField_code.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_code.setBounds(105, 30, 150, 21);
		contentPanel.add(textField_code);
		textField_code.setColumns(10);
		
		JLabel label_1 = new JLabel("检验方式：");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_1.setBounds(40, 68, 76, 15);
		contentPanel.add(label_1);
		
		textField_vmode = new JTextField();
		textField_vmode.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_vmode.setBounds(105, 65, 150, 21);
		contentPanel.add(textField_vmode);
		textField_vmode.setColumns(10);
	 
	 
		
		JLabel label_2 = new JLabel("发证时间：");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_2.setBounds(316, 33, 76, 15);
		contentPanel.add(label_2);
		
	
		
		
		
		
		JLabel label_3 = new JLabel("有效期至：");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_3.setBounds(316, 68, 76, 15);
		contentPanel.add(label_3);
		
		textField_sTime = new JTextField();
		textField_sTime.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_sTime.setBounds(382, 30, 150, 21);
		contentPanel.add(textField_sTime);
		textField_sTime.setColumns(10);
		
		textField_eTime = new JTextField();
		textField_eTime.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_eTime.setBounds(382, 65, 150, 21);
		contentPanel.add(textField_eTime);
		textField_eTime.setColumns(10);
		
		textField_ename = new JTextField();
		textField_ename.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_ename.setBounds(105, 99, 150, 21);
		contentPanel.add(textField_ename);
		textField_ename.setColumns(10);
		
		JLabel label_4 = new JLabel("企业名称：");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_4.setBounds(40, 102, 87, 15);
		contentPanel.add(label_4);
		
	 
	 	
		JLabel label_5 = new JLabel("所在地区：");
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_5.setBounds(40, 135, 78, 15);
		contentPanel.add(label_5);
		
		comboBox_city = new JComboBox();
		comboBox_city.setLightWeightPopupEnabled(false);// 解决其它组件遮挡问题
		comboBox_city.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		comboBox_city.setBounds(105, 132, 150, 21);
		contentPanel.add(comboBox_city);
		
		JLabel label_6 = new JLabel("生产地址：");
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_6.setBounds(316, 135, 76, 15);
		contentPanel.add(label_6);
		
		JLabel label_7 = new JLabel("企业描述：");
		label_7.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_7.setBounds(40, 165, 60, 15);
		contentPanel.add(label_7);
		
		textField_address = new JTextField();
		textField_address.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_address.setBounds(382, 132, 150, 21);
		contentPanel.add(textField_address);
		textField_address.setColumns(10);
		
		editorPane_desc = new JEditorPane();
		editorPane_desc.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		editorPane_desc.setBounds(105, 163, 300, 62);
		contentPanel.add(editorPane_desc);
		
		scrollPane = new JScrollPane();
		scrollPane.setToolTipText("申证单元编辑器");
		scrollPane.setBounds(0, 257, 569, 210);
		 
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(540, 540));
//		panel.setMinimumSize(new Dimension(600,500));
		scrollPane.setViewportView(panel);
		FlowLayout fl_panel = new FlowLayout(FlowLayout.LEFT, 0, 0); 
		fl_panel.setAlignOnBaseline(true);
		panel.setLayout(fl_panel);
	
		contentPanel.add(scrollPane);
		JLabel label_10 = new JLabel("产品名称：");
		label_10.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_10.setBounds(316, 102, 63, 15);
		contentPanel.add(label_10);
		
		textField_product = new JTextField();
		textField_product.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_product.setBounds(382, 99, 150, 21);
		contentPanel.add(textField_product);
		textField_product.setColumns(10);
		
		
		
		// 窗口关闭监听
		this.addWindowListener(new WindowAdapter() { 
			@Override
			public void windowClosing(WindowEvent e) { 
				reset();
			}
		});
		
		
//		JButton btnT = new JButton("");
//		btnT.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				JButton btn = (JButton) e.getSource();
//				Point p = btn.getLocation();
//				int x = p.x;
//				int y = p.y;
//				calendar.setLocation(x-276, y);
//				calendar.setVisible(true);
//				calendar.updateUI();
//			}
//		});
//		
//
//		btnT.setBounds(535, 29, 24, 23);
//		contentPanel.add(btnT);
		

		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		 
			{
				JButton cancelButton = new JButton("打印证书");
				cancelButton.addActionListener(new ActionListener() { 
					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						service.submitPrintQueue(cer.getId());
						certificatePanel.initData();// 刷新数据
					}
				});
				cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 12)); 
				buttonPane.add(cancelButton);
			}
		}
		initDependentData();// 初始化依赖数据
	}
	
	// 重置表单
	public void reset(){ 
		textField_code.setText("");
		textField_vmode.setText("");
		textField_sTime.setText("");
		textField_eTime.setText(""); 
		
		
		textField_ename.setEditable(true);// 不禁止修改企业名称
		textField_address.setEditable(true);
		editorPane_desc.setEditable(true);// 不禁止修改企业描述
		comboBox_city.setEnabled(true);// 不禁止修改城市 
		
		textField_ename.setText("");
		textField_address.setText("");
		editorPane_desc.setText("");
		comboBox_city.setSelectedIndex(0);;// 设置城市被选择
 
		
	}
	
	
	/**
	 * 由选择企业窗口调用设置企业Id
	 * @param id
	 */
	public void setEnterpriseId(int eid){ 
		try{
			Enterprise enter = serviceEnterprise.findById(eid);
			textField_ename.setText(enter.getName());
			textField_address.setText(enter.getAddress());
			editorPane_desc.setText(enter.getDesc());
			comboBox_city.setSelectedItem(new City(enter.getId()));// 设置城市被选择
			
			
	
			textField_ename.setEditable(false);// 禁止修改企业名称
			textField_address.setEditable(false);
			editorPane_desc.setEditable(false);// 禁止修改企业描述
			comboBox_city.setEnabled(false);// 禁止修改城市 
		}catch(Exception e){
			log.error("查询城市根据id={}失败了",eid, e);
		 
		}
		
	}
	// 当前显示的证书信息
	private Certificate cer;
	
	// 显示证书信息
	public void showInfo(int cid) {
		reset();// 重置表单
		panel.removeAll();// 移除所有
		
		
		cer = service.findById(cid);
		
		textField_code.setText(cer.getCode());textField_code.setEditable(false);
		textField_vmode.setText(cer.getVerifyMode());textField_vmode.setEditable(false);
		textField_sTime.setText(cer.getSendTimeFormat());textField_sTime.setEditable(false);
		textField_eTime.setText(cer.getEffectiveTimeFormat()); textField_eTime.setEditable(false);
		textField_product.setText(cer.getProduct());textField_product.setEditable(false);
		Enterprise enter = cer.getEnterprise();
		
		textField_ename.setText(enter.getName());textField_ename.setEditable(false);
		textField_address.setText(enter.getAddress());textField_address.setEditable(false);
		editorPane_desc.setText(enter.getDesc());editorPane_desc.setEditable(false);
		comboBox_city.setSelectedItem(new City(enter.getCityId()));// 设置城市被选择
		comboBox_city.setEnabled(false);
		
		List<Unit> units = cer.getUnits();
		for(Unit unit:units){
			// 默认添加一个申证单元
			CertificateUnitPanel unitPanel = new CertificateUnitPanel(panel);
			unitPanel.setUnit(unit);
			panel.add(unitPanel);
		}
		
		
	}

 
}
