package org.marker.certificate.view.panel;

import org.marker.certificate.bean.Exam;
import org.marker.certificate.bean.Page;
import org.marker.certificate.bean.ServiceMessage;
import org.marker.certificate.component.ContentPanel;
import org.marker.certificate.service.ExamService;
import org.marker.certificate.service.impl.ExamServiceImpl;
import org.marker.certificate.view.dialog.ExamDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 *
 * 班级管理
 * @author marker
 */
public class ExamPanel extends ContentPanel {

	private static final long serialVersionUID = -3845513977752773511L;


	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel pageNoLabel;

	// 企业业务对象
	private ExamService service = new ExamServiceImpl();


	// 当前页码
	private int currentPageNo = 1;
	// 每页条数
	private int pageSize = 20;
	// 总页数
	private int totalPages;



    // 添加企业
    private ExamDialog dialog = new ExamDialog(this, service);


    //
	private DefaultTableModel dtm = new DefaultTableModel( new Object[][] { }, new String[] { "ID", "考试名称", "年级", "学期", "考试人数", "排序", "创建时间"}) {
		private static final long serialVersionUID = -6985108346052830790L;
		java.lang.Class<?>[] columnTypes = new java.lang.Class[] {
		 	String.class,  Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
		};


		@Override
		public java.lang.Class<?> getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}
	};




	// 依赖数据初始化
	@Override
	public void initDependentData() {


	}



	/**
	 * Create the panel.
	 */
	public ExamPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 2));
		add(panel, BorderLayout.NORTH);
		
		initDependentData();// 初始化依赖数据
		


		
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
					
					ExamPanel.this.currentPageNo = pageNo;
					ExamPanel.this.initData();
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
		table.setToolTipText("考试管理");
		table.setBorder(null);
		table.setModel(dtm);

		// 加入鼠标点击事件
		table.addMouseListener(new MouseAdapter(){    //鼠标事件
			public void mouseClicked(MouseEvent e){
//				if (e.getClickCount() == 2) {
					Exam exam = new Exam();
					int selectedRow = table.getSelectedRow(); //获得选中行索引
					exam.setId(Integer.valueOf(dtm.getValueAt(selectedRow, 0).toString()));
					exam.setName(dtm.getValueAt(selectedRow, 1).toString());
					exam.setGradeName(dtm.getValueAt(selectedRow, 2).toString());
					exam.setSemesterId(Integer.valueOf(dtm.getValueAt(selectedRow, 3).toString()));
					exam.setCount(Integer.valueOf(dtm.getValueAt(selectedRow, 4).toString()));
					exam.setSortNum(Integer.valueOf(dtm.getValueAt(selectedRow, 5).toString()));
					dialog.setExam(exam);
					dialog.setVisible(true);
//				}

			}
		});


		/* 初始化鼠标右键菜单 */
        JPopupMenu popmenu = new JPopupMenu();


		JMenuItem delete = new JMenuItem("删除");

		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectId = getSelectObjectId(table, dtm);
				if(selectId != -1){
					int select = JOptionPane.showConfirmDialog(ExamPanel.this, "确定要删除?");
					if (select == 0) {
						ServiceMessage msg = service.deleteById(selectId);
						if(!msg.isStatus()){
							JOptionPane.showMessageDialog(null, "删除失败!\n");
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
		

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", textField.getText());

		
		Page<Exam> page = service.queryByPage(currentPageNo, pageSize, params);
		
		List<Exam> list =	page.getData();
		
		this.totalPages = page.getTotalPages();
		pageNoLabel.setText(page.getCurrentPageNo()+" / "+page.getTotalPages());
		
		
		Iterator<Exam> it = list.iterator();
		while(it.hasNext()){
			Exam e = it.next();
			dtm.addRow(new Object[]{
					e.getId(),
					e.getName(),
					e.getGradeName(),
					e.getSemesterId(),
					e.getCount(),
					e.getSortNum(),
					e.getCreateTime()
					}
			);
		}
		table.updateUI();
	}
}
