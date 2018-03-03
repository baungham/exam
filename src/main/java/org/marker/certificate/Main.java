package org.marker.certificate;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.util.Timer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.alibaba.druid.pool.DruidDataSource;
import org.marker.certificate.bean.Menu;
import org.marker.certificate.component.BreadcrumLabel;
import org.marker.certificate.component.ContentPanel;
import org.marker.certificate.component.RCardLayout;
import org.marker.certificate.component.renderer.MyTreeCellRenderer;
import org.marker.certificate.dao.DBConnection;
import org.marker.certificate.monitor.MonitorThread;
import org.marker.certificate.task.TimeTimerTask;
import org.marker.certificate.view.*;
import org.marker.certificate.view.listener.MenuSelectionListener;
import org.marker.certificate.view.panel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 程序入口
 * @author marker
 * @version 1.0
 */
public class Main {
	
	// 日志记录器
	protected static final Logger log = LoggerFactory.getLogger(Main.class);
	

	private static JFrame frame;
	/** 菜单JTree */
	private JTree tree = new JTree();
	/** 内容区  */
	private JPanel content = new JPanel();

	// 时间
	private JLabel label_time;
	// 用户名
	private JLabel lblAdmin;
	
	/**卡片布局 ,不能获取当前显示组件，因此采用增强版的卡片布局 */
	private RCardLayout card = new RCardLayout();
	
	
	// 监控线程
	private static MonitorThread monitorThread;
	
	
	// 是否登录
	private static boolean islogin = false;
	
	

	private int WIDTH  = 1000;
	private int HEIGHT = 640;
	
	
	/* ================================================
	 *                  面包屑导航
	 * ================================================
	 */
	private JLabel labelTwo = new BreadcrumLabel("");
	private JLabel labelThree = new BreadcrumLabel("");
	private JLabel label_1 = new JLabel(" > ");
	private JLabel label_2 = new JLabel(" > ");
	
	static{
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName(); 
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch ( Exception e) { 
			log.error("加载系统UI失败...", e);
		} 
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			@Override
//			public void run() {
				try {
					Main window = new Main();
					if(!islogin){
						new LoginDialog(window); 
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
//			}
//		});
	}
	
 
	
	/**
	 * Create the application.
	 */
	public Main() {
		initializeMonitorThread();// 初始化监控线程
		initialize();
		initSystemTray();
		startTime();// 启动动态时间显示
	}

	
	/**
	 * 初始化监控线程
	 */
	private void initializeMonitorThread() {
		newMonitorThread();
	}

	
	
	/**
	 * 获取当前监控线程
	 * @return
	 */
	public static MonitorThread getMonitorThread(){
		return monitorThread;
	}

	/**
	 * 启动监控
	 */
	public static void newMonitorThread() {
		monitorThread = new MonitorThread();
		monitorThread.start();// 启动监控线程
	}

	/**
	 * 初始化托盘菜单
	 */
	private void initSystemTray() {
		/* 托盘图标设置 */
		SystemTray st = SystemTray.getSystemTray(); 
		Image icon = null;
		try {
			icon = new ImageIcon("resource/images/icon_tray.png").getImage();
 			// 判断操作系统
			String osName = System.getProperty("os.name");
			if(osName.contains("Mac")){
				java.lang.Class<?> clzz = java.lang.Class.forName("com.apple.eawt.Application");
				Object app = clzz.getMethod("getApplication").invoke(null);

				app.getClass().getMethod("setDockIconImage", Image.class).invoke(app, icon);
//				com.apple.eawt.Application.Application application = com.apple.eawt.Application.Application.getApplication();
//				application.setDockIconImage(icon);

            }

            TrayIcon ti = new TrayIcon( icon);
            PopupMenu p = new PopupMenu();
            MenuItem about = new MenuItem(new String("关于系统(About)".getBytes("UTF-8")));
            MenuItem exit = new MenuItem(new String("退出(Exit)".getBytes("UTF-8")));
            exit.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    Main.closeFrame();// 关闭窗口
                }
            });
            p.addSeparator();
            p.add(about);
            p.add(exit);
            p.addSeparator();
            ti.setPopupMenu(p);
            ti.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            st.add(ti);







		} catch (Exception e) {
			log.error("初始化托盘菜单异常!", e);
		}
	}



	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("成绩打印管理系统");


		ImageIcon icon = new ImageIcon("resource/images/icon.png");
		frame.setIconImage( icon.getImage());
		// frame.setCursor(new Cursor(Cursor.MOVE_CURSOR));//设置鼠标为十字架
		frame.setLocationRelativeTo(null);
		frame.setSize(WIDTH, HEIGHT); 
		// 设置窗口居中
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension winSize = kit.getScreenSize();
		frame.setLocation(winSize.width/2 - WIDTH/2, winSize.height/2 - HEIGHT/2); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		content.setBorder(new LineBorder(new Color(128, 128, 128)));
		
	
		content.setBackground(Color.WHITE);
		content.setBounds(190, 130, 795, 482);
		content.setLayout(card);// 设置为卡片布局

        content.add("main", new MainPanel());
		content.add("studentManager", new StudentPanel());// 学生管理
		content.add("gradeManager", new GradePanel());// 年级
		content.add("classManager", new ClassPanel());// 班级
		content.add("examManager", new ExamPanel());// 考试

		content.add("noticeManager", new NoticePrintPanel());// 通知打印

		content.add("printsetup", new PrintSetupPanel(this ));
		content.add("certificateManager", new CertificatePanel());// 证书管理
		content.add("printerTask", new PrinterQueuePanel());// 证书打印队列
		content.add("leadingManager", new LeadingPanel());// 导入记录
		content.add("printerRecord",new PrinterLogPanel());// 打印记录

		content.add("CollectManager",new CollectPrintPanel());// 汇总打印
		frame.getContentPane().add(content);
		
		
		
		// 导航
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("成绩打印管理系统");
		
		DefaultMutableTreeNode menu_1 =new DefaultMutableTreeNode(new Menu(null, "基本信息",1));
		DefaultMutableTreeNode menu_2 =new DefaultMutableTreeNode(new Menu(null, "成绩管理",2));
		DefaultMutableTreeNode menu_3 =new DefaultMutableTreeNode(new Menu(null, "打印管理",2));
//		DefaultMutableTreeNode menu_4 =new DefaultMutableTreeNode(new Menu(null, "系统管理",3));

		 
		
		menu_1.add(new DefaultMutableTreeNode(new Menu("gradeManager", "年级管理")));
		menu_1.add(new DefaultMutableTreeNode(new Menu("classManager", "班级管理")));
		menu_1.add(new DefaultMutableTreeNode(new Menu("studentManager", "学生管理")));
		root.add(menu_1);
		
		
		
		menu_2.add(new DefaultMutableTreeNode(new Menu("examManager", "考试管理")));
		menu_2.add(new DefaultMutableTreeNode(new Menu("certificateManager", "成绩管理")));
		menu_2.add(new DefaultMutableTreeNode(new Menu("leadingManager", "导入数据")));
		root.add(menu_2);



		menu_3.add(new DefaultMutableTreeNode(new Menu("noticeManager", "通知打印")));
		menu_3.add(new DefaultMutableTreeNode(new Menu("CollectManager", "汇总打印")));
		menu_3.add(new DefaultMutableTreeNode(new Menu("printerTask", "打印队列")));
//		menu_3.add(new DefaultMutableTreeNode(new Menu("printerRecord", "打印记录")));
		root.add(menu_3);

		
//		menu_4.add(new DefaultMutableTreeNode(new Menu("printsetup", "打印设置")));
//		root.add(menu_4);

		
		
		
		DefaultTreeModel d = new DefaultTreeModel(root);
		tree.setCellRenderer(new MyTreeCellRenderer());
		tree.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		tree.setRowHeight(28);
		tree.setRootVisible(false);// 不显示根节点
		tree.setModel(d);
		// 添加树监听器
		tree.addTreeSelectionListener(new MenuSelectionListener(this));
		
		// 展开节点
		tree.expandPath(new TreePath(menu_1.getPath()));
		tree.expandPath(new TreePath(menu_2.getPath()));
		tree.expandPath(new TreePath(menu_3.getPath()));

		
		
		tree.setBounds(20, 100, 160, 512);
		frame.getContentPane().add(tree);
		
		JPanel panel = new JPanel();
		
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 994, 90);
		frame.setResizable(false);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label_3 = new JLabel("当前用户：");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("微软雅黑", Font.BOLD, 12));
		label_3.setBounds(828, 17, 69, 15);
		panel.add(label_3);
		
		lblAdmin = new JLabel("admin");
		lblAdmin.setForeground(Color.WHITE);
		lblAdmin.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblAdmin.setBounds(900, 17, 54, 15);
		panel.add(lblAdmin);
		
		label_time = new JLabel("2014年12月14日 22:33:33");
		label_time.setForeground(Color.WHITE);
		label_time.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_time.setBounds(820, 54, 160, 15);
		panel.add(label_time);
	 
		
		JLabel label = new JLabel(" ");
		label.setIcon(new ImageIcon("resource/images/bar.png"));
		label.setBounds(0, 0, 994, 90);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 100, 25, 512);
		frame.getContentPane().add(panel_1);
		
		
		/* 面包屑导航 开始  */
		JPanel nav = new JPanel();
		nav.setBorder(UIManager.getBorder("ComboBox.border"));
		nav.setBounds(190, 100, 795, 30);
		nav.setLayout(new BoxLayout(nav, BoxLayout.X_AXIS));
		 
		frame.getContentPane().add(nav);
		
		JLabel labelHome = new BreadcrumLabel("主页");
		nav.add(new JLabel("  "));
		nav.add(labelHome);
		nav.add(label_1); label_1.setVisible(false);
		nav.add(labelTwo);
		nav.add(label_2); label_2.setVisible(false);
		nav.add(labelThree);
		/* 面包屑导航结束  */
		
		frame.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent e) {
				closeFrame();
			} 
		});
		
		
		/* 显示主界面 */ 
		setContent("main");
	}
	
	
	
	
	
	/**
	 * 设置面包屑导航
	 */
	public void setBreadcrumbNavigator(String two, String three){
		if(two == null && three == null){// 回到主页
			this.label_1.setVisible(false);
			this.label_2.setVisible(false);
			this.labelTwo.setText("");
			this.labelThree.setText("");
		}else if(three == null){ // 二级
			this.label_1.setVisible(true);
			this.labelTwo.setText(two);
			this.labelThree.setText(""); 
			this.label_2.setVisible(false); 
		}else{// 三级
			this.label_1.setVisible(true);
			this.label_2.setVisible(true);
			this.labelTwo.setText(two);
			this.labelThree.setText(three); 
		}
	}

	
	
	
	/**
	 * 设置内容区域
	 * @param identify 标识
	 */
	public void setContent(String identify) { 
		card.show(content, identify);
		ContentPanel currentPanel = (ContentPanel) card.getCurrentCard();
		if(currentPanel != null){
			currentPanel.initData(); 
		}
	}
	
	
	/**
	 * 设置鼠标样式
	 * @param cursor
	 */
	public void setCursor(Cursor cursor){
		frame.setCursor(cursor);
	}
	
	
	
	/**
	 * 设置是否显示
	 * @param b
	 */
	public void setVisible(boolean b){
		frame.setVisible(b);
	}
	
	
	/**
	 * 设置用户名
	 * @param text
	 */
	public void setUserName(String text){
		lblAdmin.setText(text); 
	}
	
	
	/**
	 * 启动时间
	 */
	public void startTime(){
		Timer timer = new Timer();
		timer.schedule(new TimeTimerTask(timer, label_time), 1000);// 在1秒后执行此任务
	}
	
	
	/**
	 * 退出程序
	 */
	public static void closeFrame(){
		Main.getMonitorThread().interrupt();
		DruidDataSource cpds = null;
		try {
			cpds = (DruidDataSource)DBConnection.getInstance().getDataSource();
		} catch (PropertyVetoException e) { 
			e.printStackTrace();
		}
		cpds.close();
		frame.dispose();
		System.exit(0);
	}
}



