package org.marker.certificate.view.dialog;

import org.marker.certificate.bean.Grade;
import org.marker.certificate.bean.Semester;
import org.marker.certificate.service.GradeService;
import org.marker.certificate.service.impl.GradeServiceImpl;
import org.marker.certificate.view.LeadingPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * 导入考试
 *
 * @author marker
 * @version 1.0
 */
public class ImportExamDialog extends JDialog {

	private static final long serialVersionUID = 1089279433599822686L;

	private final JPanel contentPanel = new JPanel();



	// 窗口宽度
	private int D_WIDTH = 368;
	// 窗口高度
	private int D_HEIGHT = 200;
	private JTextField textField;
	private JTextField sortNumTextField;

    // 城市
    private JComboBox comboBox;
    private JComboBox comboBox1;


    // gradeService
    private GradeService gradeService = new GradeServiceImpl();

	// 初始化依赖数据
	public void initDependentData(){
        // 年级信息
        java.util.List<Grade> list = gradeService.getAll();
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        for(Grade c : list){
            dcbm.addElement(c);
        }
        comboBox.setModel(dcbm);


        DefaultComboBoxModel dcbm1 = new DefaultComboBoxModel();
        dcbm1.addElement(new Semester("第一学期",1));
        dcbm1.addElement(new Semester("第二学期",2));
        dcbm1.addElement(new Semester("第三学期",3));
        dcbm1.addElement(new Semester("第四学期",4));
        dcbm1.addElement(new Semester("第五学期",5));
        dcbm1.addElement(new Semester("第六学期",6));
        dcbm1.addElement(new Semester("学考成绩",7));
        comboBox1.setModel(dcbm1);

	}

	/**
	 * Create the dialog.
     */
	public ImportExamDialog(LeadingPanel panel) {
		setTitle("导入考试数据");
		this.setModal(true);
		this.setResizable(false);//设置不可改变大小
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
		setSize(D_WIDTH, D_HEIGHT);
		// 设置窗口居中
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension winSize = kit.getScreenSize();
		setLocation(winSize.width/2 - D_WIDTH/2, winSize.height/2 - D_HEIGHT/2);  
		
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);


		JLabel label = new JLabel("年级：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBounds(29, 34, 67, 15);
		contentPanel.add(label);

        comboBox = new JComboBox();
        comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        comboBox.setBounds(107, 34, 97, 21);
        contentPanel.add(comboBox);





		JLabel label_1 = new JLabel("考试名称：");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_1.setBounds(29, 62, 68, 15);
		contentPanel.add(label_1);

        textField = new JTextField();
        textField.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        textField.setBounds(104, 59, 148, 21);
        contentPanel.add(textField);
        textField.setColumns(10);


        JLabel label_2 = new JLabel("学期：");
        label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_2.setBounds(29, 92, 68, 15);
        contentPanel.add(label_2);


        comboBox1 = new JComboBox();
        comboBox1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        comboBox1.setBounds(107, 89, 140, 21);
        contentPanel.add(comboBox1);

		JLabel label_3 = new JLabel("排序号：");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_3.setBounds(29, 62, 68, 15);
		contentPanel.add(label_3);

		sortNumTextField = new JTextField();
		sortNumTextField.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		sortNumTextField.setBounds(104, 119, 148, 21);
		contentPanel.add(sortNumTextField);
		sortNumTextField.setColumns(10);


		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确 定"); 
				okButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
                        Grade grade = (Grade) comboBox.getSelectedItem();

                        String examName = textField.getText();
                        String sortNum = sortNumTextField.getText();

                        Semester semester =  (Semester)comboBox1.getSelectedItem();

                        panel.dealwith(grade, semester, examName, Integer.valueOf(sortNum));

						if(true){
							setVisible(false);
							reset();// 数据初始化
						} 
						
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("取 消");
				cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		} 
		initDependentData();
	}

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);

        initDependentData();

    }

    /**
	 * 重置数据
	 */
	public void reset() {
		textField.setText("");
	}
}
