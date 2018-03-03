package org.marker.certificate.view.panel;

import org.marker.certificate.bean.Grade;
import org.marker.certificate.bean.Semester;
import org.marker.certificate.component.ContentPanel;
import org.marker.certificate.service.GradeService;
import org.marker.certificate.service.ToolsService;
import org.marker.certificate.service.impl.GradeServiceImpl;
import org.marker.certificate.service.impl.ToolsServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


/**
 * 汇总管理面板
 *
 * @author marker
 */
public class CollectPrintPanel extends ContentPanel {

	private static final long serialVersionUID = -3845513977752773511L;


    // 年级
    private JComboBox comboBox ;

	// 企业业务对象
	private GradeService service = new GradeServiceImpl();
	private ToolsService toolsService = new ToolsServiceImpl();

    // gradeService
    private GradeService gradeService = new GradeServiceImpl();

    private JLabel yl;
    private JLabel cl;
    private JTextField ctf;
    private JLabel nol;
    private JTextField notf;
    private JButton ypa;
    private JTextField notf10;



	// 依赖数据初始化
	@Override
	public void initDependentData() {
        // 年级信息
        List<Grade> list = gradeService.getAll();
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        for(Grade c : list){
            dcbm.addElement(c);
        }
        comboBox.setModel(dcbm);


//        DefaultComboBoxModel dcbm1 = new DefaultComboBoxModel();
//        dcbm1.addElement(new Semester("第一学期",1));
//        dcbm1.addElement(new Semester("第二学期",2));
//        dcbm1.addElement(new Semester("第三学期",3));
//        dcbm1.addElement(new Semester("第四学期",4));
//        dcbm1.addElement(new Semester("第五学期",5));
//        dcbm1.addElement(new Semester("第六学期",6));
//        dcbm1.addElement(new Semester("学考成绩",7));
//        cts.setModel(dcbm1);

	}



	/**
	 * Create the panel.
	 */
	public CollectPrintPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();

		add(panel, BorderLayout.NORTH);








        yl = new JLabel("年级：");

        comboBox = new JComboBox();
        comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        comboBox.setPreferredSize(new Dimension(100, 20));



        cl = new JLabel("班级：");
        ctf = new JTextField();
        ctf.setPreferredSize(new Dimension(100, 20));
        nol = new JLabel("学号：");
        notf = new JTextField();
        notf.setPreferredSize(new Dimension(100, 20));



        JPanel north = new JPanel();
        north.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 15));
        north.add(yl);
        north.add(comboBox);
        north.add(cl);
        north.add(ctf);
        north.add(nol);
        north.add(notf);
        this.add(north, BorderLayout.NORTH);



        JPanel north1 = new JPanel();
        north1.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 20));
        ypa = new JButton("打印汇总单");
        ypa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String gradeName = ((Grade)comboBox.getSelectedItem()).getName();
                String className = ctf.getText();
                String studentNo = notf.getText();

                toolsService.submitPrintCollectToQueue(studentNo, gradeName, className);
                JOptionPane.showMessageDialog(null, "已经提交到打印队列...");
            }
        });
        north1.add(ypa);


        this.add(north1, BorderLayout.SOUTH);



        initDependentData();// 初始化依赖数据

	}

    @Override
    public void initData() {

    }


}
