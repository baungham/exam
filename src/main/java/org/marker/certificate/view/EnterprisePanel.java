package org.marker.certificate.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.marker.certificate.bean.Enterprise;
import org.marker.certificate.bean.Item;
import org.marker.certificate.bean.Page;
import org.marker.certificate.bean.ServiceMessage;
import org.marker.certificate.component.ContentPanel;
import org.marker.certificate.service.impl.EnterpriseServiceImpl;
import org.marker.certificate.view.dialog.EnterpriseAddDialog;
import org.marker.certificate.view.dialog.EnterpriseUpdateDialog;

public class EnterprisePanel extends ContentPanel {
 
	private static final long serialVersionUID = -3845513977752773511L;
	
	
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel pageNoLabel; 

	// 企业业务对象
	private EnterpriseServiceImpl service = new EnterpriseServiceImpl(); 
	
	// 当前页码
	private int currentPageNo = 1; 
	// 每页条数
	private int pageSize = 30;
	// 总页数
	private int totalPages;
	
	
	// 添加企业
	private EnterpriseAddDialog addDialog =new EnterpriseAddDialog(this, service);
	 
	// 修改企业
	private EnterpriseUpdateDialog updateDialog = new EnterpriseUpdateDialog(this, service);
	
	
	
	// 企业 
	private DefaultTableModel dtm = new DefaultTableModel( new Object[][] { }, new String[] { "ID", "企业名称", "所在地", "生产地址", "录入时间", "描述"}) { 
		private static final long serialVersionUID = -6985108346052830790L;
		Class<?>[] columnTypes = new Class[] {
			Integer.class, String.class, Object.class, Object.class, Object.class, Object.class
		};
		
		
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}
	};
	
	
	private JComboBox comboBox;
	
	
	// 依赖数据初始化
	@Override
	public void initDependentData() {
		
		
	}
	
	
	
	/**
	 * Create the panel.
	 */
	public EnterprisePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel(); 
		add(panel, BorderLayout.NORTH);
		
		initDependentData();// 初始化依赖数据
		
		JLabel label_1 = new JLabel("                                                                               ");
		panel.add(label_1);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(new Object[] {
				new Item("all","全部信息"),
				new Item("ename","企业名称"),
				}));
		panel.add(comboBox);
		
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				initData();// 重新载入数据
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel.add(button);
		
		JPanel panel_1 = new JPanel(); 
		add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton_1 = new JButton("上一页");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentPageNo > 1){
					currentPageNo--;
					initData();
				} 
			}
		});
		
		JLabel label = new JLabel("页码：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel_1.add(label);
		
		pageNoLabel = new JLabel("1/1");
		pageNoLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel_1.add(pageNoLabel);
		
		JLabel label_2 = new JLabel("                                                                  ");
		panel_1.add(label_2);
		btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("下一页");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentPageNo < totalPages){
					currentPageNo++;
					initData();
				}
			}
		});
		
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel_1.add(btnNewButton);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel_1.add(textField_1);
		textField_1.setColumns(5);
		
		JButton button_1 = new JButton("跳转");
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int pageNo = 0;
				try{
					pageNo = Integer.parseInt(textField_1.getText());
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "页码输入有误，请输入数字!");
				} 
				if( 1 <= pageNo && pageNo <= totalPages){
					
					EnterprisePanel.this.currentPageNo = pageNo;
					EnterprisePanel.this.initData();
				}
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel_1.add(button_1);
		
		table = new JTable();  
		JScrollPane jsp = new JScrollPane(); 
		jsp.setViewportView(table);
		table.setRowHeight(25);
		
		
		table.setFont(new Font("微软雅黑", Font.PLAIN, 12)); 
		table.setSelectionBackground(new Color(100, 149, 237));
		table.setForeground(Color.DARK_GRAY);
		table.setFillsViewportHeight(true);	 
		
		
		//  获取表头
		JTableHeader tc = table.getTableHeader();
		tc.setFont(new Font("微软雅黑", Font.PLAIN, 12)); 
		
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setToolTipText("企业管理");
		table.setBorder(null);
		table.setModel(dtm);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		
		/* 初始化鼠标右键菜单 */ 
		JPopupMenu popmenu = new JPopupMenu();
		JMenuItem add = new JMenuItem("添加"); 
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addDialog.setVisible(true);
			}
		});
		
		popmenu.add(add);
		
		JMenuItem update = new JMenuItem("修改");
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectId = getSelectObjectId(table, dtm);
				if(selectId != -1){
					updateDialog.showInfo(selectId);
					updateDialog.setVisible(true);
				} 
			} 
		 
		});
		popmenu.add(update);
		
		
		JMenuItem delete = new JMenuItem("删除");
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectId = getSelectObjectId(table, dtm);
				if(selectId != -1){
					int select = JOptionPane.showConfirmDialog(EnterprisePanel.this, "确定要删除?");
					if (select == 0) {
						ServiceMessage msg = service.deleteById(selectId); 
						if(!msg.isStatus()){
							JOptionPane.showMessageDialog(null, "删除失败!\n企业还有证书信息。");
						}
						initData();// 删除完毕，再刷新数据
					}
				}
			}
		});
		popmenu.add(delete); 
		popmenu.addSeparator();
  
		table.setComponentPopupMenu(popmenu);
		
		
		this.add(jsp, BorderLayout.CENTER); 

	}

	






	@Override
	public synchronized void initData() {
		reset(dtm);
		
		Item item = (Item)comboBox.getSelectedItem();
		Map<String, Object> params = new HashMap<String, Object>();
		 
		params.put(item.getCommond(), textField.getText());
		
		
		
		Page<Enterprise> page = service.queryByPage(currentPageNo, pageSize, params);
		
		List<Enterprise> list =	page.getData();
		
		this.totalPages = page.getTotalPages();
		pageNoLabel.setText(page.getCurrentPageNo()+" / "+page.getTotalPages());
		
		
		Iterator<Enterprise> it = list.iterator();
		while(it.hasNext()){
			Enterprise e = it.next();
			dtm.addRow(new Object[]{
					e.getId(),
					e.getName(),
					e.getProvinceName()+"省 "+e.getCityName()+"",
					e.getAddress(),
					e.getTimeFormat(),
					e.getDesc()}
			);
		}
		table.updateUI();
	}
}
