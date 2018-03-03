package org.marker.certificate.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.marker.certificate.util.DateTimeUtil;

/**
 * 证书
 * @author marker
 * @version 1.0
 */
public class Certificate {

	// 证书ID
	private int id;
	// 企业ID
	private int enterpriseId;
	// 证书编号
	private String code;
	// 产品名称
	private String product;
	// 有效时间
	private Date effectiveTime;
	// 发证时间
	private Date sendTime;
	// 检验方式
	private String verifyMode;
	// 多个申证单元 
	private List<Unit> units = new ArrayList<Unit>(4);
	// 企业
	private Enterprise enterprise;
	
	// 企业名称
	private String enterpriseName;
	// 省份
	private String province;
	// 城市
	private String city;
	// 队列ID
	private int qid;
	// 打印状态
	private int printer = 0;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public Date getEffectiveTime() { 
		return effectiveTime;
	}

	public String getEffectiveTimeFormat() { 
		return DateTimeUtil.getTimeyyyyMMdd(effectiveTime);
	}

	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public String getSendTimeFormat() {
		return DateTimeUtil.getTimeyyyyMMdd(sendTime);
	}
	
	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getVerifyMode() {
		return verifyMode;
	}

	public void setVerifyMode(String verifyMode) {
		this.verifyMode = verifyMode;
	}
 
	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
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

	public int getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	
	/**
	 * 添加申证书单元
	 * @param u
	 */
	public void addUnit(Unit u) {
		this.units.add(u);
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public int getPrinter() {
		return printer;
	}

	public void setPrinter(int printer) {
		this.printer = printer;
	}
	
	
	/**
	 * 获取打印状态
	 * @return
	 */
	public String getPrinterFormat(){
		switch (printer) {
		case 0: return "未打印";
		case 1: return "排队中...";
		case 2: return "已打印"; 
		}
		return "未打印";
	}
	
}
