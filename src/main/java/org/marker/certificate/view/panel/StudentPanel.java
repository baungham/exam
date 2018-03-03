package org.marker.certificate.view.panel;

import org.marker.certificate.bean.*;
import org.marker.certificate.component.ContentPanel;
import org.marker.certificate.service.StudentService;
import org.marker.certificate.service.impl.StudentServiceImpl;
import org.marker.certificate.view.dialog.StudentDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 学生管理
 */
public class StudentPanel extends ContentPanel {

	private static final long serialVersionUID = -3845513977752773511L;


	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel pageNoLabel;

	// 企业业务对象
	private StudentService service = new StudentServiceImpl();

	// 当前页码
	private int currentPageNo = 1;
	// 每页条数
	private int pageSize = 20;
	// 总页数
	private int totalPages;


	// 添加企业
	private StudentDialog dialog = new StudentDialog(this, service);


	// 企业
	private DefaultTableModel dtm = new DefaultTableModel( new Object[][] { }, new String[] { "学号", "姓名", "年级", "班级"}) {
		private static final long serialVersionUID = -6985108346052830790L;
		java.lang.Class<?>[] columnTypes = new java.lang.Class[] {
				String.class, String.class, Object.class, Object.class, Object.class, Object.class
		};


		@Override
		public java.lang.Class<?> getColumnClass(int columnIndex) {
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
	public StudentPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 2));
		add(panel, BorderLayout.NORTH);
		
		initDependentData();// 初始化依赖数据

		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(new Object[] {
				new Item("studentNo","学号"),
				new Item("name","姓名"),
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
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 20));
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
					
					StudentPanel.this.currentPageNo = pageNo;
					StudentPanel.this.initData();
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
		table.setToolTipText("学生管理");
		table.setBorder(null);
		table.setModel(dtm);
//		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		
		/* 初始化鼠标右键菜单 */ 
		JPopupMenu popmenu = new JPopupMenu();
//		JMenuItem add = new JMenuItem("添加");
//		add.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				dialog.setVisible(true);
//			}
//		});
//
//		popmenu.add(add);
//
//		JMenuItem update = new JMenuItem("修改");
//		update.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				int selectId = getSelectObjectId(table, dtm);
//				if(selectId != -1){
//					dialog.showInfo(selectId);
//					dialog.setVisible(true);
//				}
//			}
//
//		});
//		popmenu.add(update);


		JMenuItem delete = new JMenuItem("删除");

		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object selectId = getSelectObject(table, dtm);
				if(selectId != null){
					int select = JOptionPane.showConfirmDialog(StudentPanel.this, "确定要删除?");
					if (select == 0) {
						ServiceMessage msg = service.deleteByStudentNo(selectId);
						if(!msg.isStatus()){
							JOptionPane.showMessageDialog(null, "删除失败!");
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
		
		
		
		Page<Student> page = service.queryByPage(currentPageNo, pageSize, params);
		
		List<Student> list =	page.getData();
		
		this.totalPages = page.getTotalPages();
		pageNoLabel.setText(page.getCurrentPageNo()+" / "+page.getTotalPages());
		
		
		Iterator<Student> it = list.iterator();
		while(it.hasNext()){
			Student e = it.next();
			dtm.addRow(new Object[]{
					e.getStudentNo(),
					e.getName(),
					e.getGradeName(),
					e.getClassName()}
			);
		}
		table.updateUI();
	}
}
