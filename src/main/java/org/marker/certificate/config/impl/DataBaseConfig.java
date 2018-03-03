package org.marker.certificate.config.impl;

import org.marker.certificate.config.ConfigEngine;


/**
 * 数据库配置
 * @author marker
 * @version 1.0
 */
public class DataBaseConfig extends ConfigEngine {

	// 配置文件地址
	public static final String CONFIG_FILE_PATH = "config/database.properties";
	
	// 单例
	private static DataBaseConfig instance ;
	
	
	
	// 需要单例支持
	private DataBaseConfig() {
		super(CONFIG_FILE_PATH);
	}
	
	
	
	/**
	 * 获取数据库配置实例
	 * */
	public static DataBaseConfig getInstance(){
		if(instance == null){
			instance = new DataBaseConfig();   
		}
		return instance;
	}
	
	
	
}
