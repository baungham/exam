package org.marker.certificate.bean;

import java.util.Date;

import org.marker.certificate.util.DateTimeUtil;


/**
 * 有效期统计
 * @author marker
 * @version 1.0
 */
public class TimeCount {

	// 发证时间
	private Date stime;
	// 有效期
	private Date etime;
	
	// 证书个数
	private int count;
	
	// 有效时间年份
	private int year;

	
	
 

	public Date getStime() {
		return stime;
	}
	public String getStimeFormat() { 
		return DateTimeUtil.getTimeyyyyMM(stime);
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

	public Date getEtime() {
		return etime;
	}
	
	public String getEtimeFormat() {
		return DateTimeUtil.getTimeyyyyMM(etime);
	}

	public void setEtime(Date etime) {
		this.etime = etime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
	
	
}
