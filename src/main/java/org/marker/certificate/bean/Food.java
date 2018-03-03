package org.marker.certificate.bean;

/**
 * 申证单元下边的食品明细
 * @author marker
 * @version 1.0
 */
public class Food {

	// ID
	private int id;
	// 申证单元id
	private int uid;
	// 食品明细
	private String name;
	// 使用标准
	private String used;
	
	
	 
	public Food() {
		super();
	}
	
	
	public Food(String name, String used) {
		super();
		this.name = name;
		this.used = used;
	}
 
 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsed() {
		return used;
	}
	public void setUsed(String used) {
		this.used = used;
	}
	
	
	
}
