package org.marker.certificate.config.impl;

import org.marker.certificate.config.ConfigEngine;


/**
 * 打印配置
 * @author marker
 * @version 1.0
 */
public class PrinterConfig extends ConfigEngine {

	// 配置文件地址
	public static final String CONFIG_FILE_PATH = "config/printer.properties";
	
	// 单例
	private static PrinterConfig instance ;
	
	
	
	// 需要单例支持
	private PrinterConfig() {
		super(CONFIG_FILE_PATH);
	}
	
	
	
	/**
	 * 获取数据库配置实例
	 * */
	public static PrinterConfig getInstance(){
		if(instance == null){
			instance = new PrinterConfig();   
		}
		return instance;
	}
	
	
	
}
