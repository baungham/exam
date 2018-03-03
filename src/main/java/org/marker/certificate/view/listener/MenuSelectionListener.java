package org.marker.certificate.view.listener;
 

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import org.marker.certificate.Main;
import org.marker.certificate.bean.Menu;


/**
 * 菜单选项监听器
 * 
 * 实现了面包屑导航、内容区域的控制
 * @author marker
 * @version 1.0
 */
public class MenuSelectionListener implements TreeSelectionListener{

	// 主界面
	private Main mainFrame;
	
	
	/**
	 * 监听器构造
	 * @param mainFrame 主界面
	 */
	public MenuSelectionListener(Main mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	
	@Override
	public void valueChanged(TreeSelectionEvent e)  {
		JTree t =(JTree)e.getSource();
		DefaultMutableTreeNode dmt = (DefaultMutableTreeNode)t.getLastSelectedPathComponent();
		DefaultMutableTreeNode pdmt = (DefaultMutableTreeNode) dmt.getParent();// 父级 
		Menu menu = (Menu)dmt.getUserObject(); 
		if(pdmt.getUserObject() instanceof String){// 代表点击二级菜单 
			mainFrame.setBreadcrumbNavigator(menu.getName(), null);  
		}else{// 三级菜单
			Menu pm = (Menu) pdmt.getUserObject();
			mainFrame.setBreadcrumbNavigator(pm.getName(), menu.getName()); 
			mainFrame.setContent(menu.getIdentify());// 改变内容区域
		} 
	}

}
