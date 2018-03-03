package org.marker.certificate.config.impl;

import org.marker.certificate.config.ConfigEngine;


/**
 * 系统配置
 *
 * @author marker
 * @version 1.0
 */
public class SystemConfig extends ConfigEngine {

	// 配置文件地址
	public static final String CONFIG_FILE_PATH = "config/system.properties";

	// 单例
	private static SystemConfig instance ;



	// 需要单例支持
	private SystemConfig() {
		super(CONFIG_FILE_PATH);
	}
	
	
	
	/**
	 * 获取数据库配置实例
	 * */
	public static SystemConfig getInstance(){
		if(instance == null){
			instance = new SystemConfig();
		}
		return instance;
	}


	public String getExcelNoticePath(){
		return super.get("excel.notice");
	}
	public String getExcelCollectPath(){
		return super.get("excel.collect");
	}
	public String getExcelOutPath(){
		return super.get("excel.out");
	}
	
	
	
}
