package org.marker.certificate.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.marker.certificate.bean.ExamSience;
import org.marker.certificate.bean.Page;
import org.marker.certificate.component.ContentPanel;
import org.marker.certificate.service.ExamSienceService;
import org.marker.certificate.service.impl.ExamSienceServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 


/**
 * 证书管理
 * @author marker
 * @version 1.0
 */
public class CertificatePanel extends ContentPanel {
	// 日志记录器
	protected static final Logger log = LoggerFactory.getLogger(CertificatePanel.class);
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1678334184709622949L;
	
	private JTextField textFieldPageNO;
	private JTable table;
	private	JLabel pageNoLabel;
	
	private JComboBox comboBox;
	
	
	// 当前页码
	private int currentPageNo = 1; 
	// 每页条数
	private int pageSize = 20;
	// 总页数
	private int totalPages;
	
	// 业务
	private ExamSienceService examSienceService = new ExamSienceServiceImpl();
	
	
	

	private DefaultTableModel dtm = new DefaultTableModel(
			new Object[][] { },
				new String[] {
                        "考号",
                        "姓名",
                        "年级",
                        "班级",
                        "学期",
                        "语文",
                        "语名",
                        "数学",
                        "数名",
                        "英语",
                        "英名",
                        "物理",
                        "物名",
                        "化学",
                        "化名",
                        "生物",
                        "生名",
                        "6科(理)",
                        "6科(理)名",
                        "6科(理)班名",
                        "政治",
                        "政名",
                        "历史",
                        "历名",
                        "地理",
                        "地名",
                        "6科(文)",
                        "6科(文)名",
                        "6科(文)班名",
                        "美术",
                        "美名",
                        "音乐",
                        "音名",
                        "体育",
                        "体名",
                        "信息技术",
                        "信名",
                        "心理健康",
                        "心名",
                        "通用技术",
                        "通名"

				}
			) { 
				private static final long serialVersionUID = 6529285203789200566L;
				Class<?>[] columnTypes = new Class[] {
						Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
						,Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
						,Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
						,Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
						,Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
				};
				
				
				@Override
				public Class<?> getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
	private JTextField textField;
	

	private JFileChooser fileChooser;
	
	
	
	
	/**
	 * Create the panel.
	 */
	public CertificatePanel() {
		setLayout(new BorderLayout(0, 0));
		
		
		
		fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"xls 2003", "xls");
		fileChooser.setFileFilter(filter);
		
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 20));
		add(panel, BorderLayout.SOUTH);
		
		JLabel label = new JLabel("页码：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel.add(label);
		
	    pageNoLabel = new JLabel("1 / 1");
		pageNoLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel.add(pageNoLabel);
		

		
		JButton button_prev = new JButton("上一页");
		button_prev.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentPageNo > 1){
					currentPageNo--;
					initData();
				} 
			}
		});
		button_prev.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel.add(button_prev);
		
		JButton button_next = new JButton("下一页");
		button_next.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentPageNo < totalPages){
					currentPageNo++;
					initData();
				}
			}
		});
		button_next.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel.add(button_next);
		
		textFieldPageNO = new JTextField();
		panel.add(textFieldPageNO);
		textFieldPageNO.setColumns(10);
		
		JButton button_find = new JButton("跳转");
		button_find.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int pageNo = 0;
				try{
					pageNo = Integer.parseInt(textFieldPageNO.getText());
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "页码输入有误，请输入数字!");
				} 
				if( 1 <= pageNo && pageNo <= totalPages){ 
					CertificatePanel.this.currentPageNo = pageNo;
					CertificatePanel.this.initData();
				}
			}
		});
		button_find.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel.add(button_find);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 2));

		add(panel_1, BorderLayout.NORTH);
		 
//		 JButton button = new JButton("全部导出");
//		 button.addActionListener(new ActionListener() {
//		 	public void actionPerformed(ActionEvent e) {
//		 		int returnVal = fileChooser.showSaveDialog(CertificatePanel.this);
//				if (returnVal == JFileChooser.APPROVE_OPTION) {
//					String path = fileChooser.getSelectedFile()+".xls";
//					try {
//						ExportDataUtil.process(path);
//					} catch (Exception e1) {
//						log.error("导出处理是失败...", e1);
//						e1.printStackTrace();
//						JOptionPane.showMessageDialog(null, "导出异常，请查看日志信息!");
//					}
//					JOptionPane.showMessageDialog(null, "导出成功!");
//				}
//		 	}
//		 });
//		 panel_1.add(button);
		 
//		 JButton btnNewButton = new JButton("批量打印");
//		 btnNewButton.addActionListener(new ActionListener() {
//		 	public void actionPerformed(ActionEvent e) {
//		 		// 查询未打印证书数量
////		 		int count = service.getNotPrinter();
////		 		if(count <= 0){
////		 			JOptionPane.showMessageDialog(null, "现在没有未打印记录!");
////		 		}else{
////		 			int a = JOptionPane.showConfirmDialog(null, "点击确定将会打印从未打印过的证书!\n"
////			 				+ "当前未打印证书数量：" + count + " 条");
////			 		if(a == JOptionPane.OK_OPTION){
////			 			// 提交打印任务
//////			 			service.submitNotPrintToQueue();
////			 			JOptionPane.showMessageDialog(null, "提交打印任务完毕...");
////			 		}
////		 		}
//
//		 	}
//		 });
//		 panel_1.add(btnNewButton);
		 

		
//		 comboBox = new JComboBox();
//		comboBox.setModel(new DefaultComboBoxModel(new Object[] {
//				new Item("all", "全部信息"),
//				new Item("ccode", "证书编号"),
//				new Item("ename", "企业名称")
//			}));
//		panel_1.add(comboBox);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(20);
		
		JButton button_2 = new JButton("查询");
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button_2.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				initData();// 重新载入数据
			}
		});
		panel_1.add(button_2);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 单选
		table.setFillsViewportHeight(true);
		table.setSurrendersFocusOnKeystroke(true);
		table.setModel(dtm);
		JScrollPane jsp = new JScrollPane(); 
		jsp.setViewportView(table);
		
		table.setFont(new Font("微软雅黑", Font.PLAIN, 12)); 
		table.setForeground(Color.DARK_GRAY);
		table.setFillsViewportHeight(true); 

		table.setToolTipText("证书管理");
		table.setBorder(null);
		//  获取表头
		JTableHeader tc = table.getTableHeader();
		tc.setFont(new Font("微软雅黑", Font.PLAIN, 12)); 
		table.setRowHeight(25);
	
		table.setSelectionBackground(new Color(100, 149, 237));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//设置自动大小
		
		/* 初始化鼠标右键菜单 */ 
//		JPopupMenu popmenu = new JPopupMenu();
//		JMenuItem add = new JMenuItem("添加");
//		add.setFont(new Font("微软雅黑", Font.PLAIN, 12));
//		add.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
////				addDialog.setVisible(true);
//			}
//		});
//
//		popmenu.add(add);
//
//		JMenuItem update = new JMenuItem("修改");
//		update.setFont(new Font("微软雅黑", Font.PLAIN, 12));
//		update.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				int selectId = getSelectObjectId(table, dtm);
//				if(selectId != -1){
////					updateDialog.setCertificate(selectId);
////					updateDialog.setVisible(true);
//				}
//			}
//
//		});
//		popmenu.add(update);
//
//
//		JMenuItem delete = new JMenuItem("删除");
//		delete.setFont(new Font("微软雅黑", Font.PLAIN, 12));
//		delete.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				int selectId = getSelectObjectId(table, dtm);
//				if(selectId != -1){
//					int select = JOptionPane.showConfirmDialog(CertificatePanel.this, "确定要删除?");
//					if (select == 0) {
////						service.deleteById(selectId);
//						initData();// 删除完毕，再刷新数据
//					}
//				}
//			}
//		});
//		popmenu.add(delete);
//		JMenuItem find = new JMenuItem("查看");
//		find.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				int selectId = getSelectObjectId(table, dtm);
//				if(selectId != -1){
////					lookDialog.showInfo(selectId);
////					lookDialog.setVisible(true);
//				}else{
//					JOptionPane.showMessageDialog(null, "请选择要查看的证书!");
//				}
//			}
//		});
//		delete.setFont(new Font("微软雅黑", Font.PLAIN, 12));
//		popmenu.add(find);
//
//		JMenuItem printer = new JMenuItem("打印证书");
//		printer.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int selectId = getSelectObjectId(table, dtm);
//				if(selectId != -1){
////					service.submitPrintQueue(selectId);
//					initData();
//				}else{
//					JOptionPane.showMessageDialog(null, "请选择需要打印的证书!");
//				}
//
//			}
//		});
//		printer.setFont(new Font("微软雅黑", Font.PLAIN, 12));
//		popmenu.add(printer);
//
//		popmenu.addSeparator();
//
//		table.setComponentPopupMenu(popmenu);
		
		
		
		
		
		add(jsp, BorderLayout.CENTER);

	}







	@Override
	public void initData() {
		reset(dtm);
		log.debug("查询{}成绩", textField.getText());
//		Item item = (Item)comboBox.getSelectedItem();
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", textField.getText());

		 
//		params.put(item.getCommond(), textField.getText());
		
		Page<ExamSience> page = examSienceService.queryByPage(currentPageNo, pageSize, params);
		
		List<ExamSience> list = page.getData();
		
		this.totalPages = page.getTotalPages();
		pageNoLabel.setText(page.getCurrentPageNo()+" / "+page.getTotalPages());
		
		// "证书ID", "证书编号", "企业名称", "所在地","检验方式" , "发证时间", "有效期至"
		Iterator<ExamSience> it = list.iterator();
		while(it.hasNext()){
            ExamSience entity = it.next();
			dtm.addRow(new Object[]{
                    entity.getStudentNo(),
                    entity.getStudentName(),
                    entity.getGradeName(),
                    entity.getClassName(),
                    entity.getSemesterId(),
                    entity.getLanguage(),
                    entity.getLanguageOrder(),
                    entity.getMathematics(),
                    entity.getMathematicsOrder(),
                    entity.getEnglish(),
                    entity.getEnglishOrder(),
                    entity.getPhysics(),
                    entity.getPhysicsOrder(),
                    entity.getChemistry(),
                    entity.getChemistryOrder(),
                    entity.getBiology(),
                    entity.getBiologyOrder(),
                    entity.getScienceTotalAchievement(),
                    entity.getScienceTotalAchievementOrder(),
                    entity.getScienceClassOrder(),
                    entity.getPolitics(),
                    entity.getPoliticsOrder(),
                    entity.getHistory(),
                    entity.getHistoryOrder(),
                    entity.getGeography(),
                    entity.getGeographyOrder(),
                    entity.getLiteralTotalAchievement(),
                    entity.getLiteralTotalAchievementOrder(),
                    entity.getLiteralClassOrder(),
                    entity.getInfomation(),
                    entity.getInfomationOrder(),
                    entity.getGeneral(),
                    entity.getGeneralOrder(),
                    entity.getSports(),
                    entity.getSportsOrder(),
                    entity.getMusic(),
                    entity.getMusicOrder(),
                    entity.getArt(),
                    entity.getArtOrder(),
                    entity.getPsychology(),
                    entity.getPsychologyOrder()

			});
		}
		table.updateUI();
	}







	@Override
	public void initDependentData() {
	}

}
