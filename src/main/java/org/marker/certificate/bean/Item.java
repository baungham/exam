package org.marker.certificate.bean;


/**
 * 打印纸张选项
 * @author marker
 * @version 1.0
 */
public class Item {
	private String name;
	private String commond;
	
	
	
	
	public Item(String commond, String name) {
		super();
		this.name = name;
		this.commond = commond;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCommond() {
		return commond;
	}
	public void setCommond(String commond) {
		this.commond = commond;
	}
	
	
	
	/**
	 * 重写
	 */
	public boolean equals(Object obj) {  
		if(obj != null){
			Item it = (Item)obj;
			return this.commond.equals(it.commond);
		}
		return false;
	}
	
	
	@Override
	public String toString() { 
		return this.name;
	}
}
