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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.marker.certificate.bean.Item;
import org.marker.certificate.bean.Page;
import org.marker.certificate.bean.PrinterLog;
import org.marker.certificate.component.ContentPanel;
import org.marker.certificate.service.IPrinterLogService;
import org.marker.certificate.service.impl.PrinterLogServiceImpl;


/**
 * 打印记录
 * @author marker
 * @version 1.0
 */
public class PrinterLogPanel extends ContentPanel {
 
	private static final long serialVersionUID = 1827560196906618670L;
	
	
	// 打印日志
	private IPrinterLogService service = new PrinterLogServiceImpl();
	private JTable table;
	private JTextField textField;
	
	//
	private JComboBox comboBox;
	
	
	private DefaultTableModel dtm = new DefaultTableModel(
			new Object[][] { },
				new String[] {
					"打印时间", "证书编号", "产品名称" ,"企业名称", "所在地","检验方式"
				}
			) { 
				private static final long serialVersionUID = 6529285203789200566L;
				Class<?>[] columnTypes = new Class[] {
					Integer.class, String.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
				};
				
				
				@Override
				public Class<?> getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
	
	
	/**
	 * Create the panel.
	 */
	public PrinterLogPanel() {
		setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 单选
		table.setFillsViewportHeight(true);
		table.setSurrendersFocusOnKeystroke(true);
		table.setModel(dtm);
		table.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		table.setModel(dtm);
		table.setForeground(Color.DARK_GRAY);
		table.setFillsViewportHeight(true); 
		table.setBorder(null);
		//  获取表头
		JTableHeader tc = table.getTableHeader();
		tc.setFont(new Font("微软雅黑", Font.PLAIN, 12)); 
		table.setRowHeight(25);
	
		table.setSelectionBackground(new Color(100, 149, 237));
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(0).setMinWidth(25);
		
		JScrollPane jsp = new JScrollPane(); 
		jsp.setViewportView(table);
		add(jsp, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new Object[] {
				new Item("all", "全部信息"),
				new Item("ccode", "证书编号"),
				new Item("ename", "企业名称")
			}));
		panel.add(comboBox);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(20);
		
		JButton button = new JButton("查询");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel.add(button);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		
		JLabel label_1 = new JLabel("页码：");
		panel_1.add(label_1);
		
		label_page = new JLabel("");
		label_page.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel_1.add(label_page);
		
		JLabel label = new JLabel("                           ");
		panel_1.add(label);
		
		JButton button_1 = new JButton("上一页");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPageNo > 1){
					currentPageNo--;
					initData();
				}
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel_1.add(button_1);
		
		JButton button_2 = new JButton("下一页");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPageNo < totalPages){
					currentPageNo++;
					initData();
				}
			}
		});
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel_1.add(button_2);
		
		textFieldPageNO = new JTextField();
		textFieldPageNO.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel_1.add(textFieldPageNO);
		textFieldPageNO.setColumns(10);
		
		JButton button_3 = new JButton("跳转");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pageNo = 0;
				try{
					pageNo = Integer.parseInt(textFieldPageNO.getText());
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "页码输入有误，请输入数字!");
				} 
				if( 1 <= pageNo && pageNo <= totalPages){ 
					PrinterLogPanel.this.currentPageNo = pageNo;
					PrinterLogPanel.this.initData();
				}
			}
		});
		button_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel_1.add(button_3);

	}

	JLabel label_page;

	// 当前页码
	private int currentPageNo = 1; 
	// 每页条数
	private int pageSize = 50;
	// 总页数
	private int totalPages;
	private JTextField textFieldPageNO;
	
	
	@Override
	public void initData() {
		reset(dtm);
		
		Item item = (Item)comboBox.getSelectedItem();
		Map<String, Object> params = new HashMap<String, Object>();
		 
		params.put(item.getCommond(), textField.getText());
		
		Page<PrinterLog> page = service.queryByPage(currentPageNo, pageSize, params);
		
		List<PrinterLog> list = page.getData();
		
		this.totalPages = page.getTotalPages();
		label_page.setText(page.getCurrentPageNo()+" / "+page.getTotalPages());
		
		// "证书ID", "证书编号", "企业名称", "所在地","检验方式" , "发证时间", "有效期至"
		Iterator<PrinterLog> it = list.iterator();
		while(it.hasNext()){
			PrinterLog e = it.next();
			dtm.addRow(new Object[]{
				e.getTimeFormat(),
				e.getCode(),
				e.getProduct(),// 产品
				e.getEnterpriseName(),
				e.getProvince()+"省 "+e.getCity(),
				e.getVerifyMode(), 
			});
		}
		table.updateUI(); 
	}

	@Override
	public void initDependentData() {
		// TODO Auto-generated method stub
		
	}

}
