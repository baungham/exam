package org.marker.certificate.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List; 

import org.marker.certificate.config.ConfigEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class DaoEngine {

	// 日志记录器
	protected Logger log = LoggerFactory.getLogger(ConfigEngine.class);
	
	// 数据库链接工具
	private DBConnection db = null;
	

	public DaoEngine(){
		try {
			db = DBConnection.getInstance();
		} catch (PropertyVetoException e) {
			log.error("create DBConnection faild!", e);
		}
	}
	
	
	/**
	 * 插入参数
	 * @param params
	 * @throws SQLException
	 */
	public void insertParams(PreparedStatement ps, Object... params) throws SQLException{
		if(params != null){
			int i = params.length;
			for(@SuppressWarnings("unused") Object o : params){
				ps.setObject(i--, params[i]);
			}
		}
	}
	
	/**
	 * 直接获取数据库链接
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException{
		return db.getConnection();
	}
	
	
	/**
	 * 更新数据
	 * @param sql
	 * @param mapper
	 */
	public boolean update(String sql, Object... params){
		Connection conn = null;
		try {
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			insertParams(ps, params);// 插入参数 
			int status =  ps.executeUpdate(); 
			ps.close();
			return status > 0?true : false; 
		} catch (SQLException e) { 
			log.error("update error!", e);
		}finally{
			db.close(conn); 
		}
		return false; 
	}
	
	
	/**
	 * 更新数据
	 * @param sql
	 * @param mapper
	 * @return int 返回更新数据的ID
	 */
	public int updateReturnId(String sql, Object... params){
		Connection conn = null;
		try {
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			insertParams(ps, params);// 插入参数
			
			int status =  ps.executeUpdate();
			if (status > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs != null && rs.next()) {
					return rs.getInt(1);
				}
				if (rs != null) {
					rs.close();
				}
			}
			ps.close(); 
		} catch (SQLException e) { 
			log.error("update error!", e);
		}finally{
			db.close(conn); 
		}
		return -1; 
	}
	
	
	
	public <T> T queryForObject(String sql, RowMapper<T> mapper, Object... params) {
		Connection conn = null;
		try {
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			insertParams(ps, params);// 插入参数
			ResultSet rs = ps.executeQuery();
			int index = 0;
			while(rs.next()){
				return mapper.fetch(rs, ++index);
			} 
			rs.close();
			ps.close();
			conn.close(); 
		} catch (SQLException e) {
			log.error("queryForObject error!", e);
		} finally{
			db.close(conn);
		}
		return null;
	}
	
	
	
	/**
	 * 批量查询
	 * @param sql
	 * @param mapper
	 * @param objects
	 * @return
	 */
	public <T> List<T> queryForList(String sql, RowMapper<T> mapper, Object... params) {
		
		Connection conn = null;
		final List<T> list = new ArrayList<T>(1);
		try {
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			insertParams(ps, params);// 插入参数
			ResultSet rs = ps.executeQuery();
			int index = 0;
			while(rs.next()){
				list.add(mapper.fetch(rs, ++index));
			} 
			rs.close();
			ps.close();
			conn.close(); 
		} catch (SQLException e) {
			log.error("queryForList error!", e);
		} finally{
			db.close(conn);
		}
		return list;
	}
	
	
	

	
	
	
	
	/**
	 * 查询条数
	 * @param string
	 * @param array
	 */
	public int queryForInt(String sql, Object... params) {
		Connection conn = null;
		try {
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			insertParams(ps, params);// 插入参数
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				return rs.getInt(1);
			} 
			rs.close();
			ps.close();
			conn.close(); 
		} catch (SQLException e) {
			log.error("queryForInt error!", e);
		}finally{
			db.close(conn);
		}
		return 0;
	}
}
