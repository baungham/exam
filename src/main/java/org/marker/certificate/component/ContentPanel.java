package org.marker.certificate.component;

import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 * 抽象的目的就是为了初始化数据
 * @author marker
 * @version 1.0
 */
public abstract class ContentPanel extends JPanel {
 
	private static final long serialVersionUID = -8816142076968030571L;



	/**
	 * Create the panel.
	 */
	public ContentPanel() {
		
	}

	
	/**
	 * 初始化数据接口
	 */
	public abstract void initData();
	
	/**
	 * 初始化依赖数据
	 */
	public abstract void initDependentData();
	
	
	/**
	 * 重置表格数据模型
	 */
	public void reset(DefaultTableModel dtm){
		if(dtm.getRowCount() != 0){
			for(int i=-1; i<dtm.getRowCount(); i++){
				Vector<?> v =  dtm.getDataVector();
				v.removeAll(v);
			}
		} 
	}
	
	
	
	
	
	/**
	 * 获取当前被选择的ID
	 * @return
	 */
	public int getSelectObjectId(JTable table,DefaultTableModel dtm){
		int row = table.getSelectedRow();// 获取选择的行
		if(dtm.getRowCount() > 0){// 如果有数据
			if(row != -1){
				Integer id = -1;
				try{
					id = (Integer)table.getValueAt(row, 0);// 获取ID
				}catch(Exception e){ }
				return id;
			}
		}
		return -1;
	}




	/**
	 * 获取当前被选择的ID
	 * @return
	 */
	public Object getSelectObject(JTable table,DefaultTableModel dtm){
		int srow = table.getSelectedRow();// 获取选择的行
		if(dtm.getRowCount() > 0){// 如果有数据
			if(srow != -1){
				Integer id = -1;
				try{
					return table.getValueAt(srow, 0);// 获取ID
				}catch(Exception e){ }
			}
		}
		return null;
	}
}
