package org.marker.certificate.bean;


/**
 * 业务处理消息
 * @author marker
 * @version 1.0
 */
public class ServiceMessage {

	/** 处理状态 */
	private boolean status;
	/** 处理结果 */
	private String message;
	
	
	
	
	
	
	/**
	 * 业务消息构造
	 * @param status 状态
	 * @param message 消息
	 */
	public ServiceMessage(boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
