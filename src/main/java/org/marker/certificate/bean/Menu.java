package org.marker.certificate.bean;


/**
 * 菜单
 * @author marker
 * @version 1.0
 */
public class Menu {

	// 菜单标识
	private String identify;
	// 菜单名称
	private String name;
	// 图标
	private int icon = 0;
	
	/**
	 * 
	 * @param identify 标识
	 * @param name 名称
	 */
	public Menu(String identify, String name,int icon) {
		this.identify = identify;
		this.name = name;
		this.icon = icon;
	}
	/**
	 * 
	 * @param identify 标识
	 * @param name 名称
	 */
	public Menu(String identify, String name) {
		this.identify = identify;
		this.name = name;
	}
	
	
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * 为JTree提供获取菜单名称
	 */
	@Override
	public String toString() { 
		return this.name;
	}
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
}
