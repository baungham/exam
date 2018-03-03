package org.marker.certificate.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.marker.certificate.bean.Exam;
import org.marker.certificate.bean.Grade;
import org.marker.certificate.bean.Page;
import org.marker.certificate.bean.Semester;
import org.marker.certificate.component.ContentPanel;
import org.marker.certificate.service.ExamService;
import org.marker.certificate.service.impl.ExamServiceImpl;
import org.marker.certificate.util.AnalysisDataUtil;
import org.marker.certificate.util.ExcelUtils;
import org.marker.certificate.util.IRowMapper;
import org.marker.certificate.util.XlsUtil;
import org.marker.certificate.view.dialog.ImportExamDialog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 


/**
 * 导入数据面板
 * 
 * @author marker
 * @version 1.0
 */
public class LeadingPanel extends ContentPanel {

	// 日志记录器
	protected Logger log = LoggerFactory.getLogger(LeadingPanel.class);
	
	private JFileChooser fileChooser;
	private DefaultTableModel dtm;
    private JLabel label_number;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3297054812726228563L;
	private JTable table;
	private JTextField textField;

    private JLabel lblNewLabel_status ;


    // 导入数据
    private ImportExamDialog importExamDialog = new ImportExamDialog(this);
	/**
	 * Create the panel.
	 */
	public LeadingPanel() {
		setLayout(new BorderLayout(0, 0));
		
		
		fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"xls 2003", "xls","xlsx");
		fileChooser.setFileFilter(filter);
		
		
		JScrollPane dataScrollPane = new JScrollPane();
		dataScrollPane.setVerticalScrollBar(new JScrollBar());
		dataScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		table = new JTable();
		dtm = new DefaultTableModel(new String[] {
				"考号",
                "姓名",
                "班级",
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
				"通名",
				"9总",
				"9总名",

		}, 0);
		table.setModel(dtm);
		table.setRowHeight(25);
		table.setSelectionBackground(new Color(100, 149, 237));
		table.setForeground(Color.DARK_GRAY);
		table.setFillsViewportHeight(true);	 
		table.setFont(new Font("微软雅黑", Font.PLAIN, 12)); 
		table.setEnabled(false);//设置表格不能编辑
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//设置自动大小
		
		//  获取表头
		JTableHeader tc = table.getTableHeader();
		tc.setFont(new Font("微软雅黑", Font.PLAIN, 12));  
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		dataScrollPane.setViewportView(table);
		add(dataScrollPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_1 = new JLabel("共");
		panel_1.add(lblNewLabel_1);
		
		  label_number = new JLabel("0 条");
		panel_1.add(label_number);
		

		
		JLabel label_1 = new JLabel("状态：");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel_1.add(label_1);
		
		  lblNewLabel_status = new JLabel("");
		lblNewLabel_status.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel_1.add(lblNewLabel_status);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("文件：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel.add(textField);
		textField.setColumns(50);
		
		JButton button = new JButton("浏览");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileChooser.showOpenDialog(LeadingPanel.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					textField.setText(fileChooser.getSelectedFile()
							.getAbsolutePath());
					load();// 加载数据 
				}
			}
		});
		panel.add(button);
		
		JButton button_1 = new JButton("导入");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				if(dtm.getDataVector().size() > 0){

				    // 弹出输入框
                    importExamDialog.setVisible(true);

				}else{
					JOptionPane.showMessageDialog(null, "请加载数据，再点击导入！");
				}
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel.add(button_1);

		
		
		
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initDependentData() {
		// TODO Auto-generated method stub
		
	}
	
	private int count = 0;




	
	private void load(){
		reset(dtm);// 清除表格中的数据
		String xlsPath = textField.getText();
		if (xlsPath == null) {
			JOptionPane.showMessageDialog(null, "请选择Excel文件!");
			return;// 如果没有选择路径
		}
		count = 0;// 条数
		try {
			XlsUtil.load(xlsPath, new IRowMapper() { 
				@Override
				public void execute(Row row) {

					dtm.addRow(new Object[] {
                            ExcelUtils.getValue(row.getCell(0)),
                            ExcelUtils.getValue(row.getCell(1)),
                            ExcelUtils.getValue(row.getCell(2)),
                            ExcelUtils.getValue(row.getCell(3)),
                            ExcelUtils.getValue(row.getCell(4)),
                            ExcelUtils.getValue(row.getCell(5)),//检验方式
                            ExcelUtils.getValue(row.getCell(6)),//证书编号
                            ExcelUtils.getValue(row.getCell(7)),//
                            ExcelUtils.getValue(row.getCell(8)),//有效期
							ExcelUtils.getValue(row.getCell(9)),
							ExcelUtils.getValue(row.getCell(10)),
							ExcelUtils.getValue(row.getCell(11)),
							ExcelUtils.getValue(row.getCell(12)),
							ExcelUtils.getValue(row.getCell(13)),
							ExcelUtils.getValue(row.getCell(14)),
							ExcelUtils.getValue(row.getCell(15)),
							ExcelUtils.getValue(row.getCell(16)),
							ExcelUtils.getValue(row.getCell(17)),
							ExcelUtils.getValue(row.getCell(18)),
							ExcelUtils.getValue(row.getCell(19)),
							ExcelUtils.getValue(row.getCell(20)),
							ExcelUtils.getValue(row.getCell(21)),
							ExcelUtils.getValue(row.getCell(22)),
							ExcelUtils.getValue(row.getCell(23)),
							ExcelUtils.getValue(row.getCell(24)),
							ExcelUtils.getValue(row.getCell(25)),
							ExcelUtils.getValue(row.getCell(26)),
							ExcelUtils.getValue(row.getCell(27)),
							ExcelUtils.getValue(row.getCell(28)),
							ExcelUtils.getValue(row.getCell(29)),
							ExcelUtils.getValue(row.getCell(30)),
							ExcelUtils.getValue(row.getCell(31)),
							ExcelUtils.getValue(row.getCell(32)),
							ExcelUtils.getValue(row.getCell(33)),
							ExcelUtils.getValue(row.getCell(34)),
							ExcelUtils.getValue(row.getCell(35)),
							ExcelUtils.getValue(row.getCell(36)),
							ExcelUtils.getValue(row.getCell(37)),
							ExcelUtils.getValue(row.getCell(38)),
							ExcelUtils.getValue(row.getCell(39)),
							ExcelUtils.getValue(row.getCell(40)),
					});
					count++;
				} 
			});
		} catch (IOException e1) {
			reset(dtm);// 清除表格中的数据
			JOptionPane.showMessageDialog(null, "Excel文件格式错误!");
		}
		label_number.setText(count+" 条"); 

		lblNewLabel_status.setText("准备就绪");
		table.updateUI();// 更新
	}




    static ExamService examService = new ExamServiceImpl();

	/**
	 * 处理数据
     * @param grade
     * @param semester
     * @param examName
     */
	public void dealwith(Grade grade, Semester semester, String examName){

		lblNewLabel_status.setText("正在处理...");
		lblNewLabel_status.updateUI();// 显示
		Vector<?> data = dtm.getDataVector();
		int successCount = 0;
		int totalCount = data.size();
		long startTime = System.currentTimeMillis();
		// 如果有数据
		if(data.size() > 0){

            // 写入考试记录， 防止重复导入
			Map<String, Object> params = new HashMap<>();
			params.put("name", examName);
			params.put("gradeName", grade.getName());
			params.put("semesterId", semester.getId());
            Exam exam = null;
            Page<Exam> page = examService.queryByPage(1, 10, params);
            if (page.getData().size() == 0) {
                exam = new Exam();
                exam.setName(examName);
                exam.setGradeName(grade.getName());
                exam.setSemesterId(semester.getId());
                exam.setCount(totalCount);// 参与人数
                examService.save(exam);
            } else {
                exam = page.getData().get(0);
            }


			Iterator<?> it = data.iterator();
			while(it.hasNext()){
				Object obj = it.next();
				boolean a = AnalysisDataUtil.process(grade, semester, exam, (Vector<?>)obj);
				if(a){
					++successCount;
					it.remove();
				}
			}
		}
		long endTime = System.currentTimeMillis();

		String msg = "总共导入："+totalCount+" 条\n"
				+ "成功："+successCount+" 条\n"
				+ "失败："+(totalCount-successCount)+" 条\n";
		log.info(msg);
		if(totalCount != successCount){
			msg+= "失败原因：请查看安装目录下/logs/*.log日志文件。\n";
			lblNewLabel_status.setText("剩余未解析数据");
		}else{
			lblNewLabel_status.setText("导入完成");
		}

		msg += "消耗时间："+(endTime- startTime)+" 毫秒\n";
		JOptionPane.showMessageDialog(null,msg);



		label_number.setText((totalCount-successCount) + " 条");
		this.updateUI();// 更新表
	}

}
