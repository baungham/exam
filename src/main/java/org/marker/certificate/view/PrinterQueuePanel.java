package org.marker.certificate.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.marker.certificate.Main;
import org.marker.certificate.bean.Certificate;
import org.marker.certificate.bean.PrinterQueue;
import org.marker.certificate.component.ContentPanel;
import org.marker.certificate.monitor.MonitorThread;
import org.marker.certificate.service.ICertificateService;
import org.marker.certificate.service.ToolsService;
import org.marker.certificate.service.impl.CertificateServiceImpl;
import org.marker.certificate.service.impl.ToolsServiceImpl;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



/**
 * 证书打印队列
 * @author marker
 * @version 1.0
 */
public class PrinterQueuePanel extends ContentPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4661298953876194520L;
	private JTable table;

	
	// 队列状态
	public static JLabel label_status;
	// 操作队列按钮
	public static JButton btnNewButton;
	
	
	private DefaultTableModel dtm = new DefaultTableModel(
			new Object[][] { },
				new String[] {
					"队列ID","学号", "姓名" ,"年级", "班级","学期" , "类型", "状态"
				}
			) { 
				private static final long serialVersionUID = 6529285203789200566L;
				Class<?>[] columnTypes = new Class[] {
					Integer.class, String.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
				};
				
				
				@Override
				public Class<?> getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
	
	
	
	
	
	
	
	/**
	 * Create the panel.
	 */
	public PrinterQueuePanel() {
		setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 单选
		table.setFillsViewportHeight(true);
		table.setSurrendersFocusOnKeystroke(true);
		table.setModel(dtm);
		JScrollPane jsp = new JScrollPane(); 
		jsp.setViewportView(table);
		
		table.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		table.setModel(dtm);
		table.setForeground(Color.DARK_GRAY);
		table.setFillsViewportHeight(true);  
		table.getColumnModel().getColumn(0).setWidth(30);
		table.getColumnModel().getColumn(0).setMinWidth(20);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setMinWidth(25);
		table.setToolTipText("证书管理");
		table.setBorder(null);
		//  获取表头
		JTableHeader tc = table.getTableHeader();
		tc.setFont(new Font("微软雅黑", Font.PLAIN, 12)); 
		table.setRowHeight(25);
	
		table.setSelectionBackground(new Color(100, 149, 237));
		
		add(jsp, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel label = new JLabel("状态：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel.add(label);
		
		  label_status = new JLabel("正常");
		label_status.setForeground(Color.green);
		label_status.setFont(new Font("微软雅黑", Font.BOLD, 12));
		panel.add(label_status);
		
		JLabel label_1 = new JLabel("                                                         ");
		panel.add(label_1);
		
		  btnNewButton = new JButton("停止");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if("正常".equals(label_status.getText())){
					MonitorThread thread = Main.getMonitorThread();
					thread.setSuspend(true);// 暂停
					label_status.setText("停止");
					btnNewButton.setText("启动");
					label_status.setForeground(Color.red);
					
				}else{
					MonitorThread thread = Main.getMonitorThread(); 
					label_status.setText("正常");
					btnNewButton.setText("停止");
					thread.setSuspend(false);// 启动
					label_status.setForeground(Color.green); 
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel.add(btnNewButton);
		
		JButton button = new JButton("刷新");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initData();
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel.add(button);

		
		
		initDependentData();
	}

	private ToolsService service = new ToolsServiceImpl();
	
	
	// 初始化数据
	@Override
	public void initData() {
		reset(dtm);
		
		List<PrinterQueue> list = service.queryCurrentQueue();
		Iterator<PrinterQueue> it = list.iterator();
		while(it.hasNext()){
			PrinterQueue e = it.next();
			dtm.addRow(new Object[]{ 
				e.getId(),
				e.getStudentNo(),
				e.getStudentName(),// 产品
				e.getGradeName(),
				e.getClassName(),
				e.getSemesterId(),
				e.getTypeStr(),
				e.getStatusStr()
			});
		}
		table.updateUI();
	}

	@Override
	public void initDependentData() {
		// TODO Auto-generated method stub
		 
		
		
		
		
	}

}
