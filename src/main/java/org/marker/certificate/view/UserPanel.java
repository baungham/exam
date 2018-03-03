package org.marker.certificate.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.marker.certificate.bean.User;
import org.marker.certificate.component.ContentPanel;
import org.marker.certificate.service.IUserService;
import org.marker.certificate.service.impl.UserServiceImpl;
import org.marker.certificate.view.dialog.UserAddDialog;
import org.marker.certificate.view.dialog.UserUpdateDialog;



/**
 * 用户管理
 * @author marker
 * @version 1.0
 */
public class UserPanel extends ContentPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1562892316577976529L;
	
	
	// 业务对象
	private IUserService service = new UserServiceImpl();
	
	// 添加
	private UserAddDialog addDialog = new UserAddDialog(this,service);
	
	// 修改
	private UserUpdateDialog updateDialog =  new UserUpdateDialog(this,service);
	
	
	
	
	
	private JTable table = new JTable();
	
	private DefaultTableModel dtm = new DefaultTableModel(
			new Object[][] { },
				new String[] {
					"ID", "用户名", "手机", "描述", "登录时间","状态"
				}
			) { 
				private static final long serialVersionUID = 7641912221570777229L;
				Class<?>[] columnTypes = new Class[] {
					Integer.class, String.class, Object.class, Object.class, Object.class, Object.class
				};
				
				
				@Override
				public Class<?> getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
	
	
	/**
	 * Create the panel.
	 */
	public UserPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		table.setForeground(Color.DARK_GRAY);
		table.setFillsViewportHeight(true);
		
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setToolTipText("用户管理");
		table.setBorder(null);
		//  获取表头
		JTableHeader tc = table.getTableHeader();
		tc.setFont(new Font("微软雅黑", Font.PLAIN, 12)); 
		table.setRowHeight(25);
	
		JScrollPane jsp = new JScrollPane(); 
		jsp.setViewportView(table);
		
		table.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		table.setModel(dtm);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(0).setMinWidth(25);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setMinWidth(25);
		
		table.setSelectionBackground(new Color(100, 149, 237));
		this.add(jsp, BorderLayout.CENTER);
		
		/* 初始化鼠标右键菜单 */ 
		JPopupMenu popmenu = new JPopupMenu();
		JMenuItem add = new JMenuItem("添加"); 
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addDialog.setVisible(true);// 显示添加对话框
			}
		});
		
		popmenu.add(add);
		
		JMenuItem update = new JMenuItem("修改");
		update.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) { 
				int id = getSelectObjectId(table,dtm);// 获取选择的行 
				if(id != -1){
					updateDialog.showInfo(id);
					updateDialog.setVisible(true);
				} 
			}
		});
		popmenu.add(update);
		
		
		JMenuItem delete = new JMenuItem("删除");
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = getSelectObjectId(table,dtm);// 获取选择的行 
				if(id != -1){
					int select = JOptionPane.showConfirmDialog(UserPanel.this, "确定要删除?");
					if (select == 0) {
						if (id == 1) {// 系统管理员不能禁用
							JOptionPane.showMessageDialog(null, "系统内置管理员不能删除!");
							return;
						}
						service.deleteById(id);
						initData();// 删除完毕，再刷新数据
					}
				} 
			}
		});
		popmenu.add(delete); 
		popmenu.addSeparator();
  
		table.setComponentPopupMenu(popmenu);
		
		 
		
//		JPanel panel_1 = new JPanel();
//		add(panel_1, BorderLayout.SOUTH);
//		
//		JButton button_1 = new JButton("上一页");
//		panel_1.add(button_1);
//		
//		JButton button = new JButton("下一页");
//		panel_1.add(button);

	}

	
 
	
	/**
	 * 
	 * 初始化数据
	 * (显示此界面时候调用此方法)
	 */
	@Override
	public void initData(){
		reset(dtm);
		List<User> list = service.queryList(null);
		
		Iterator<User> it = list.iterator();
		while(it.hasNext()){
			User user = it.next();
			dtm.addRow(new Object[]{
					user.getId(),
					user.getName(),
					user.getPhone(),
					user.getDesc(),
					user.getTimeFormat(),
					user.getStatusString()}
			);
		} 
		table.updateUI();// 必须调用，不然有问题
	}




	@Override
	public void initDependentData() {
		// TODO Auto-generated method stub
		
	}
	
	

	
}
