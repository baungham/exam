package org.marker.certificate.bean;


/**
 * 城市
 * @author marker
 * @version 1.0
 */
public class City {


	// ID
	private int id;
	// 身份ID
	private int pid;
	// 名称
	private String name;
	
	
	
	
	public City() {
		super();
	}
	
	
	
	public City(int id) {
		super();
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() { 
		return this.name;
	}
	
	@Override
	public int hashCode() { 
		return this.id;
	}
	
	@Override
	public boolean equals(Object obj) {
		City c = (City)obj;
		int a = c.getId();
		return a == this.id;
	}
	
}
