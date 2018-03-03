package org.marker.certificate.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.marker.certificate.config.impl.DataBaseConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 数据库链接工具
 * @author marker
 * @version 1.0
 */
public class DBConnection {

	/** 日志 */
	private Logger logger = LoggerFactory.getLogger(DBConnection.class);



	/** 数据源对象 */
	private DataSource dataSource;
	
	/* 单例 */
	private volatile static DBConnection db = null;
	 
	
	// 数据库配置对象
	 private static final DataBaseConfig config = DataBaseConfig.getInstance();
	
	// 单例模式
	private DBConnection(DataSource dataSource){
		this.dataSource = dataSource;
	}
	 
	
	/**
	 * 获取实例
	 * @return
	 * @throws PropertyVetoException 
	 */
	public static DBConnection getInstance() throws PropertyVetoException{ 
		if(db == null){
			// 使用C3P0
            DruidDataSource cmpd =  new DruidDataSource();
            cmpd.setDbType(config.get("db.dbType"));

			cmpd.setDriverClassName(config.get("db.driverClass"));
			cmpd.setUrl(config.get("db.jdbcUrl"));
            cmpd.setTestWhileIdle(false);
            cmpd.setTestOnBorrow(false);


            db = new DBConnection(cmpd);
		}
		
	
		return db;
	}
	
	
	
	
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
	
	
	/**
	 * 代理关闭
	 * @param conn
	 * @throws SQLException
	 */
	public void close(Connection conn) {
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) { 
				e.printStackTrace();
			}
		} 
	}


	public DataSource getDataSource() {
		return dataSource;
	}


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	
}
