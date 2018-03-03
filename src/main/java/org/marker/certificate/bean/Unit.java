package org.marker.certificate.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 申证单元
 * @author marker
 * @version 1.0
 */
public class Unit {

	// 单元ID
	public int id;
	// 证书ID
	private int cid;
	// 单元描述
	private String desc;
	// 食品明细
	private List<Food> foods = new ArrayList<Food>(3);
	
	
	
	
	public Unit() {
		super();
	}
	public Unit(String desc, List<Food> foods) {
		super();
		this.desc = desc;
		this.foods = foods;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	 
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
 
	// 添加食品
	public void addFood(Food food){
		this.foods.add(food);
	}
	public List<Food> getFoods() {
		return foods;
	}
	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}
	
	
	
}
