package org.marker.certificate.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

import org.marker.certificate.Main;
import org.marker.certificate.bean.Item;
import org.marker.certificate.component.ContentPanel;
import org.marker.certificate.component.printer.DateTimeMovePanel;
import org.marker.certificate.component.printer.EnterpriseMovePanel;
import org.marker.certificate.component.printer.UnitTableMovePanel;
import org.marker.certificate.component.printer.YearMonthDayMovePanel;
import org.marker.certificate.config.impl.PrinterConfig;
import org.marker.certificate.printer.PrinterLocation;
import org.marker.certificate.printer.PrinterTableLocation;


/**
 * 打印设置
 * @author marker
 * @version 1.0
 */
public class PrintSetupPanel extends ContentPanel {

	private static final long serialVersionUID = 4743297356197898100L;

	
	// 企业基本信息模块
	private EnterpriseMovePanel enterinfopanel;
	// 发证时间模块
	private DateTimeMovePanel datetimepanel;
	// 申证单元
	private UnitTableMovePanel unittablepanel;
	// 年月日面板
	private YearMonthDayMovePanel ymdpanel;
	
	JComboBox comboBox ;
	
	/**
	 * Create the panel.
	 */
	public PrintSetupPanel( Main frame) { 
		setLayout(null);
		
		
		
		/* 初始化可以拖拽Panel*/
		enterinfopanel = new EnterpriseMovePanel(frame );
		datetimepanel = new DateTimeMovePanel(frame);
		unittablepanel = new UnitTableMovePanel(frame);
		ymdpanel       = new YearMonthDayMovePanel(frame);
		
		
		
		
		
		
		  comboBox = new JComboBox();
		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(new Object[] {
				new Item("a3", "A3"),
				new Item("a4","A4"),
				new Item("other","其它")}));
		comboBox.addItemListener(new ItemListener() { 
			@Override
			public void itemStateChanged(ItemEvent e) {
				initData();
			}
		});
		comboBox.setBounds(50, 10, 125, 21);
		add(comboBox);
		
		JLabel label = new JLabel("纸张：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBounds(6, 13, 54, 15);
		add(label);
		
		JScrollPane jsp = new JScrollPane();
		jsp.setEnabled(false);
		jsp.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	 
		jsp.setBounds(5, 40, 783, 440); 
		JPanel printcontentpanel = new JPanel();
		printcontentpanel.setBackground(new Color(102, 205, 170)); 
		printcontentpanel.setBounds(0, 0, 700, 2000);
		 
		jsp.setViewportView(printcontentpanel); 
		printcontentpanel.setLayout(null);
		printcontentpanel.setPreferredSize(new Dimension( 760, 2000));
		 
	 
		
		/* 添加移动打印组件 */
		enterinfopanel.setBounds(225, 22, 152, 219);
		printcontentpanel.add(enterinfopanel); 
		datetimepanel.setBounds(143, 282, 410, 85);
		printcontentpanel.add(datetimepanel);
		unittablepanel.setBounds(100, 282, 410, 85);
		printcontentpanel.add(unittablepanel);
		ymdpanel.setLocation(100, 282);
		printcontentpanel.add(ymdpanel);
		
		this.add(jsp);
		
		JButton button_1 = new JButton("应用");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				save();// 保存位置信息
			}
		});
		button_1.setBounds(690, 9, 80, 23);
		add(button_1);
		
		JButton button_test = new JButton("打印测试");
		button_test.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_test.setBounds(605, 9, 80, 23);
		//add(button_test); 打印测试
		
		
		// 加载依赖数据
		initDependentData();
	}

	
	/**
	 * 保存
	 */
	public void save(){
		Item item = (Item) comboBox.getSelectedItem(); 
		String commond = item.getCommond();// 得到纸张类型
		
		PrinterLocation location = enterinfopanel.getPrinterLocation();
		 
		
		// 记录证书企业信息
		saveLocation(commond,"enterprise",location);
		
		// 记录有效期 
		PrinterLocation location2 = datetimepanel.getPrinterLocation();
		saveLocation(commond,"effective",location2); 
		
		// 记录申证单元
		PrinterTableLocation location3 = unittablepanel.getPrinterTableLocation();
		saveTableLocation(commond,"unittable",location3); 
		
		// 发证日期
		PrinterLocation location4 = this.ymdpanel.getPrinterLocation();
		saveLocation(commond,"ymd",location4); 
		
		// 保存当前paper
		savePaper(commond);
		
		JOptionPane.showMessageDialog(this, "操作成功!\n已经应用打印设置！");
	}



	/**
	 * 保存paper
	 */
	public void savePaper(String paper){
		PrinterConfig config = PrinterConfig.getInstance();
		config.set("paper.current",paper );
		config.store();// 持久化 
	}
	/**
	 * 读取paper
	 */
	public String loadPaper(){
		PrinterConfig config = PrinterConfig.getInstance();
		return config.get("paper.current" );
	}

	@Override
	public void initData() {
		
		
		Item item = (Item) comboBox.getSelectedItem(); 
		String commond = item.getCommond();// 得到纸张类型
		 
		
		// 读取企业证书信息
		enterinfopanel.setPrinterLocation(loadLocation(commond, "enterprise"));
		
		// 读取有效期
		datetimepanel.setPrinterLocation(loadLocation(commond, "effective"));
		
	
		// 读取申证单元
		unittablepanel.setPrinterTableLocation(loadTableLocation(commond, "unittable"));
		
		
		//  读取发证日期
		this.ymdpanel.setPrinterLocation(loadLocation(commond, "ymd"));
		
		
	}




	@Override
	public void initDependentData() {
		String commond1 = loadPaper();
		comboBox.setSelectedItem(new Item(commond1, "")); 
	}


	
	
	
	
	
	
	
	/**
	 * 
	 * @param paper 纸张
	 * @param module 模块
	 * @param location 位置信息
	 */
	private void saveLocation(String paper,String module, PrinterLocation location){
		PrinterConfig config = PrinterConfig.getInstance();
		config.set(paper+"."+module+".x", location.getX() );
		config.set(paper+"."+module+".y", location.getY() );
		config.set(paper+"."+module+".lineheight", location.getLineheight());
		config.set(paper+"."+module+".font", location.getFont().getFamily());
		config.set(paper+"."+module+".size", location.getFont().getSize());
		config.set(paper+"."+module+".style", location.getFont().getStyle());
		config.set(paper+"."+module+".absolutex", location.getAbsoluteX());
		config.set(paper+"."+module+".absolutey", location.getAbsoluteY());
		config.store();// 持久化
	}
	
	
	/**
	 * 
	 * @param paper 纸张
	 * @param module 模块
	 * @param location 位置信息
	 */
	private void saveTableLocation(String paper, String module,
			PrinterTableLocation location) {
		PrinterConfig config = PrinterConfig.getInstance();
		config.set(paper+"."+module+".foldline", location.getFold_line() );
		config.set(paper+"."+module+".numberx", location.getNumberX() );
		config.set(paper+"."+module+".unitx", location.getUnitX() );
		config.set(paper+"."+module+".foodx", location.getFoodX() );
		config.set(paper+"."+module+".usedx", location.getUsedX() );
		config.set(paper+"."+module+".lineheight", location.getLineheight());
		config.set(paper+"."+module+".font", location.getFont().getFamily());
		config.set(paper+"."+module+".size", location.getFont().getSize());
		config.set(paper+"."+module+".style", location.getFont().getStyle());
		config.set(paper+"."+module+".absolutex", location.getAbsoluteX());
		config.set(paper+"."+module+".absolutey", location.getAbsoluteY());
		config.store();// 持久化
	}
	
	
	/**
	 * 读取位置信息
	 * @param paper
	 * @param module
	 * @return
	 */
	private PrinterLocation loadLocation(String paper, String module){
		PrinterConfig config = PrinterConfig.getInstance();
	
		String x = config.get(paper+"."+module+".x");
		String y = config.get(paper+"."+module+".y");
		String lineheight = config.get(paper+"."+module+".lineheight");
		String font = config.get(paper+"."+module+".font");
		String size = config.get(paper+"."+module+".size");
		String style = config.get(paper+"."+module+".style");
		String ax = config.get(paper+"."+module+".absolutex" );
		String ay = config.get(paper+"."+module+".absolutey" );
		 
		PrinterLocation location = new PrinterLocation();
		location.setFont(new Font(font,Integer.parseInt(style),Integer.parseInt(size)));
		location.setLineheight(Integer.parseInt(lineheight));
		location.setX(Integer.parseInt(x));
		location.setY(Integer.parseInt(y));
		location.setAbsoluteX(Integer.parseInt(ax));
		location.setAbsoluteY(Integer.parseInt(ay));
		return location;
		
	}
	
	/**
	 * 
	 * @return
	 */
	private PrinterTableLocation loadTableLocation(String paper, String module){
		PrinterConfig config = PrinterConfig.getInstance();
		String foldline = config.get(paper+"."+module+".foldline");
		String numberx = config.get(paper+"."+module+".numberx" );
		String unitx = config.get(paper+"."+module+".unitx");
		String foodx = config.get(paper+"."+module+".foodx");
		String usedx = config.get(paper+"."+module+".usedx");
		String lineheight = config.get(paper+"."+module+".lineheight");
		String font = config.get(paper+"."+module+".font");
		String size = config.get(paper+"."+module+".size");
		String style = config.get(paper+"."+module+".style");
		String ax = config.get(paper+"."+module+".absolutex");
		String ay = config.get(paper+"."+module+".absolutey");
		
		PrinterTableLocation location = new PrinterTableLocation();
		location.setFont(new Font(font,Integer.parseInt(style),Integer.parseInt(size)));
		location.setLineheight(Integer.parseInt(lineheight)); 
		location.setAbsoluteX(Integer.parseInt(ax));
		location.setAbsoluteY(Integer.parseInt(ay));

		location.setFold_line(Integer.parseInt(foldline));
		location.setNumberX(Integer.parseInt(numberx));
		location.setUnitX(Integer.parseInt(unitx));
		location.setFoodX(Integer.parseInt(foodx));
		location.setUsedX(Integer.parseInt(usedx));
		
		
		return location;
	}
}
