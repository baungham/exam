package org.marker.certificate.view.dialog;

import org.apache.commons.lang3.StringUtils;
import org.marker.certificate.bean.City;
import org.marker.certificate.bean.Enterprise;
import org.marker.certificate.bean.Grade;
import org.marker.certificate.bean.ServiceMessage;
import org.marker.certificate.service.GradeService;
import org.marker.certificate.service.ICityService;
import org.marker.certificate.service.IEnterpriseService;
import org.marker.certificate.service.impl.CityServiceImpl;
import org.marker.certificate.view.EnterprisePanel;
import org.marker.certificate.view.panel.GradePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


/**
 * 添加企业
 * @author marker
 * @version 1.0
 */
public class GradeDialog extends JDialog {

	private static final long serialVersionUID = 1089279433599822686L;

	private final JPanel contentPanel = new JPanel();



	// 窗口宽度
	private int D_WIDTH = 368;
	// 窗口高度
	private int D_HEIGHT = 337;
	private JTextField textField;
	private JEditorPane editorPane;

	// ID
	private int id;


	// 初始化依赖数据
	public void initDependentData(){


	}

	/**
	 * Create the dialog.
	 * @param service
	 * @param enterprisePanel
	 */
	public GradeDialog(final GradePanel enterprisePanel, final GradeService service) {
		setTitle("添加年级");
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


		JLabel label = new JLabel("年级名称：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBounds(29, 34, 67, 15);
		contentPanel.add(label);


		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField.setBounds(100, 31, 148, 21);
		contentPanel.add(textField);
		textField.setColumns(10);




		JLabel label_1 = new JLabel("描述：");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_1.setBounds(29, 62, 68, 15);
		contentPanel.add(label_1);
		
		editorPane = new JEditorPane();
		editorPane.setBounds(104, 59, 191, 70);
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
                        String gradeName = textField.getText();
                        if(StringUtils.isBlank(gradeName)){
                            JOptionPane.showMessageDialog(null, "请输入年级！");
                            return;
                        }

						Grade ee = new Grade();
						
						ee.setName(gradeName);
						ee.setDesc(editorPane.getText());


                        Grade grade = service.findByName(gradeName);
                        ServiceMessage msg = null;
                        if(grade == null){
                            msg = service.save(ee);
                        }else{
                            msg = service.update(ee);
                        }


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
		initDependentData();
	}
	
	

	/**
	 * 重置数据
	 */
	public void reset() {
		textField.setText("");
		editorPane.setText("");
        setTitle("添加年级");
		
	}

    public void showInfo(Grade grade) {
        textField.setText(grade.getName());
        editorPane.setText(grade.getDesc());
        setTitle("编辑年级");
    }
}
