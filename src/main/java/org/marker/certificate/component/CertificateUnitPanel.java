package org.marker.certificate.component;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.marker.certificate.bean.Food;
import org.marker.certificate.bean.Unit;



/**
 * 申证单元面板
 * @author marker
 * @version 1.0
 */
public class CertificateUnitPanel extends JPanel {

	private static final long serialVersionUID = -4430455281429190247L;
	private JTextField textField_f1;
	private JTextField textField_f2;
	private JTextField textField_f3;
	private JTextField textField_n1;
	private JTextField textField_n2;
	private JTextField textField_n3;
	private JEditorPane editorPane_desc ;
	private JLabel lblNewLabel;
	private JLabel label;

	// 索引
	private int index;
	
	/**
	 * Create the panel.
	 * @param currentUnitCount 
	 * @param panel 
	 */
	public CertificateUnitPanel(final JPanel panel) {
		this.index = panel.getComponentCount() + 1;
		setLayout(null);
		this.setPreferredSize(new Dimension(550, 105));// 必须这样设置，在flowlayout才能看到
		editorPane_desc = new JEditorPane();
		
		editorPane_desc.setBounds(28, 10, 171, 83);
		add(editorPane_desc);
		
		textField_f1 = new JTextField();
		textField_f1.setBounds(206, 10, 153, 21);
		add(textField_f1);
		textField_f1.setColumns(10);
		
		textField_f2 = new JTextField();
		textField_f2.setBounds(206, 41, 153, 21);
		add(textField_f2);
		textField_f2.setColumns(10);
		
		textField_f3 = new JTextField();
		textField_f3.setBounds(206, 72, 153, 21);
		add(textField_f3);
		textField_f3.setColumns(10);
		
		textField_n1 = new JTextField();
		textField_n1.setBounds(369, 10, 142, 21);
		add(textField_n1);
		textField_n1.setColumns(10);
		
		textField_n2 = new JTextField();
		textField_n2.setBounds(369, 41, 142, 21);
		add(textField_n2);
		textField_n2.setColumns(10);
		
		textField_n3 = new JTextField();
		textField_n3.setBounds(369, 72, 142, 21);
		add(textField_n3);
		textField_n3.setColumns(10);
		
		label = new JLabel(""+index);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBounds(10, 44, 16, 15);
		add(label);
		
		lblNewLabel = new JLabel( );
		lblNewLabel.setIcon(new ImageIcon("resource\\images\\delete.gif"));
		lblNewLabel.setBounds(521, 10, 21, 21);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int tempIndex = index-1;
				System.out.println(index);
				
				Component[] cs = panel.getComponents();
				for(int i=0; i<cs.length;i++){
					CertificateUnitPanel cer =	(CertificateUnitPanel)cs[i];
					if(cer.getIndex() > index){// 之后的组件
						// 向前移动操作，这里只是操纵index
						cer.setIndex(cer.getIndex()-1); 
					} 
				}
				panel.remove(tempIndex); 
				panel.updateUI();
				
			} 
		});
		add(lblNewLabel);

		 
		
		// 更新
		panel.add(this); 
		panel.updateUI();
	}
	
	
	public int getIndex(){
		return index;
	}
	
	
	/**
	 * 设置索引
	 * @param index
	 */
	public void setIndex(int indexe){
		label.setText(""+indexe);
		this.index = indexe; 
	}
	
	
	/**
	 * 获取申证单元对象
	 * @return
	 */
	public Unit getUnit(){
		Unit unit = new Unit();
		unit.setDesc(editorPane_desc.getText());
		unit.getFoods().add(new Food(textField_f1.getText(), textField_n1.getText()));
		unit.getFoods().add(new Food(textField_f2.getText(), textField_n2.getText()));
		unit.getFoods().add(new Food(textField_f3.getText(), textField_n3.getText()));
		return unit;
	}
	
	
	
	/**
	 * 设置申证单元
	 * @param unit
	 */
	public void setUnit(Unit unit){
		editorPane_desc.setText(unit.getDesc()); editorPane_desc.setEditable(false);
		List<Food> foods = unit.getFoods(); 
		for(int i =0; i<foods.size();i++){
			Food food = foods.get(i);
			if(i == 0){
				textField_f1.setText(food.getName());textField_f1.setEditable(false);
				textField_n1.setText(food.getUsed());textField_n1.setEditable(false);
			}else if(i ==1){
				textField_f2.setText(food.getName());textField_f2.setEditable(false);
				textField_n2.setText(food.getUsed()); textField_n2.setEditable(false);
			}else if(i == 2){
				textField_f3.setText(food.getName());textField_f3.setEditable(false);
				textField_n3.setText(food.getUsed());textField_n3.setEditable(false);
			}
		}
		
		this.remove(lblNewLabel);// 移除
	}
	
	
	
	/**
	 * 设置申证单元
	 * @param unit
	 */
	public void setUnitForUpdate(Unit unit){
		editorPane_desc.setText(unit.getDesc()); editorPane_desc.setEditable(false);
		List<Food> foods = unit.getFoods(); 
		for(int i =0; i<foods.size();i++){
			Food food = foods.get(i);
			if(i == 0){
				textField_f1.setText(food.getName()); 
				textField_n1.setText(food.getUsed()); 
			}else if(i ==1){
				textField_f2.setText(food.getName());
				textField_n2.setText(food.getUsed());
			}else if(i == 2){
				textField_f3.setText(food.getName()); 
				textField_n3.setText(food.getUsed()); 
			}
		}
	}

	
}
