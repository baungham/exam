package org.marker.certificate.bean;


/**
 * 地区统计
 * @author marker
 * @version 1.0
 */
public class AreaCount {

	
	// 城市名称
	private String cityName;
	// 企业数量
	private int enterpriseCount;
	// 证书数量
	private int certificateCount;
	
	
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public int getEnterpriseCount() {
		return enterpriseCount;
	}
	public void setEnterpriseCount(int enterpriseCount) {
		this.enterpriseCount = enterpriseCount;
	}
	public int getCertificateCount() {
		return certificateCount;
	}
	public void setCertificateCount(int certificateCount) {
		this.certificateCount = certificateCount;
	}
	
	
	
	
}
