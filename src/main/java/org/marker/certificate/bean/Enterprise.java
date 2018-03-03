package org.marker.certificate.bean;

import java.util.Date;

import org.marker.certificate.util.DateTimeUtil;


/**
 * 企业
 * @author marker
 * @version 1.0
 */
public class Enterprise {

	// 企业ID
	private int id;
	// 企业名称
	private String name;
	// 城市ID
	private int cityId;
	// 省份ID
	private int provinceId = 1; //1：代表成都
	// 描述
	private String desc; 
	// 录入时间
	private Date time;
	
	// 生产地址
	private String address;
	
	// 城市名称
	private String cityName;
	
	// 身份名称
	private String provinceName;
	
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
 

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



	public int getCityId() {
		return cityId;
	}



	public void setCityId(int cityId) {
		this.cityId = cityId;
	}



	public int getProvinceId() {
		return provinceId;
	}



	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}



	public String getCityName() {
		return cityName;
	}



	public void setCityName(String cityName) {
		this.cityName = cityName;
	}



	public String getProvinceName() {
		return provinceName;
	}



	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	
	
	
	/**
	 * 获取格式化时间
	 * @return
	 */
	public String getTimeFormat(){
		return DateTimeUtil.getTime(this.time);
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name+" ("+this.cityName+")";
	}
}
