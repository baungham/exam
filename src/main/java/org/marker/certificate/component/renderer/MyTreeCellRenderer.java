package org.marker.certificate.component.renderer;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.marker.certificate.bean.Menu;

/**
 * MyTreeCellRenderer
 * @author marker
 * @version 1.0
 */
public class MyTreeCellRenderer extends DefaultTreeCellRenderer{
 
	private static final long serialVersionUID = -1777329797960624469L;

	

	private ImageIcon li1 = new ImageIcon("resource/images/icon_info.png");
	private ImageIcon li2 = new ImageIcon("resource/images/icon_certificate.png");
	private ImageIcon li3 = new ImageIcon("resource/images/icon_system.png");
	private ImageIcon li5 = new ImageIcon("resource/images/icon_count.png");

	@Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        
        DefaultMutableTreeNode dmt = (DefaultMutableTreeNode)value;
        Object obj = dmt.getUserObject();
		if(obj instanceof Menu){
			Menu menu = (Menu)obj;
	        int icon = menu.getIcon();// 获取图标
	        
	        switch (icon) {
			case 1: this.setIcon(li1); break;
			case 2: this.setIcon(li2); break;
			case 3: this.setIcon(li3); break;
			case 5: this.setIcon(li5); break;

			default:this.setIcon(li1); break;
			} 
		}
        
        if (leaf) {
            setLeafIconByValue(value);
        }
        return this;
    }
	
	
	private ImageIcon li = new ImageIcon("resource/images/1.png");

    public void setLeafIconByValue(Object value) {  
        this.setIcon(li);
    }
}