package org.marker.certificate.bean;

import java.util.Date;

import org.marker.certificate.util.DateTimeUtil;


/**
 * 打印日志
 * @author marker
 * @version 1.0
 */
public class PrinterLog {

	// ID
	private int id;
	// 打印时间
	private Date time;
	// 打印状态
	private String status;
	// 证书编号
	private String code;	
	// 产品名称
	private String product;
	// 检验方式
	private String verifyMode;
	// 企业名称
	private String enterpriseName;
	// 省份
	private String province;
	// 城市
	private String city;
	// 生产地址
	private String address;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	
	public String getTimeFormat() { 
		return DateTimeUtil.getTime(time);
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getVerifyMode() {
		return verifyMode;
	}
	public void setVerifyMode(String verifyMode) {
		this.verifyMode = verifyMode;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	 
	
	
	
	
	
}
