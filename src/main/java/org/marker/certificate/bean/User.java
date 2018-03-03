package org.marker.certificate.bean;

import java.util.Date;

import org.marker.certificate.util.DateTimeUtil;


/**
 * 用户
 * @author marker
 * @version 1.0
 */
public class User {

	
	/* 用户ID */
	private int id;
	// 用户名
	private String name;
	// 用户密码
	private String pass;
	// 用户联系方式
	private String phone;
	// 用户秒速
	private String desc;
	// 登录时间
	private Date time;
	// 用户状态
	private boolean status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() { 
		return this.name;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	} 
	
	
	/**
	 * 获取格式化时间
	 * @return
	 */
	public String getTimeFormat(){
		return DateTimeUtil.getTime(this.time);
	}
	
	/**
	 * 获取状态字符串
	 * @return
	 */
	public String getStatusString(){
		if(this.status){
			return "正常";
		}else{
			return "禁用";
		}
	}
}
