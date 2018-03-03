package org.marker.certificate.view.dialog;

import org.marker.certificate.bean.Class;
import org.marker.certificate.bean.Exam;
import org.marker.certificate.bean.Grade;
import org.marker.certificate.bean.ServiceMessage;
import org.marker.certificate.service.ClassService;
import org.marker.certificate.service.ExamService;
import org.marker.certificate.service.GradeService;
import org.marker.certificate.view.panel.ClassPanel;
import org.marker.certificate.view.panel.ExamPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * 添加班级
 * @author marker
 * @version 1.0
 */
public class ExamDialog extends JDialog {

	private static final long serialVersionUID = 1089279433599822686L;

	private final JPanel contentPanel = new JPanel();



	// 窗口宽度
	private int D_WIDTH = 368;

	// 窗口高度
	private int D_HEIGHT = 337;
	private JTextField textField;
	private JEditorPane editorPane;
    // 城市
    private JComboBox comboBox;





	/**
	 * Create the dialog.
	 * @param service
	 * @param enterprisePanel
	 */
	public ExamDialog(final ExamPanel enterprisePanel, final ExamService service) {
		setTitle("添加班级");
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


		JLabel label = new JLabel("班级名称：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBounds(29, 34, 67, 15);
		contentPanel.add(label);


		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField.setBounds(100, 31, 148, 21);
		contentPanel.add(textField);
		textField.setColumns(10);


        JLabel labelGrade = new JLabel("年级：");
        labelGrade.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        labelGrade.setBounds(29, 59, 67, 15);
        contentPanel.add(labelGrade);



        comboBox = new JComboBox();
        comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        comboBox.setBounds(100, 59, 97, 21);
        contentPanel.add(comboBox);




        JLabel label_1 = new JLabel("描述：");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_1.setBounds(29, 92, 68, 15);
		contentPanel.add(label_1);
		
		editorPane = new JEditorPane();
		editorPane.setBounds(104, 89, 191, 70);
		contentPanel.add(editorPane);

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
						Exam ee = new Exam();
						ee.setName(textField.getText());


                        Grade grade = (Grade) comboBox.getSelectedItem();


                        ee.setGradeName(grade.getName());

						
						ServiceMessage msg = service.save(ee);
						JOptionPane.showMessageDialog(null, msg.getMessage()); 
						if(msg.isStatus()){
							enterprisePanel.initData();
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


	}


	/**
	 * 重置数据
	 */
	public void reset() {
		textField.setText("");
		editorPane.setText("");
		
	}
}
