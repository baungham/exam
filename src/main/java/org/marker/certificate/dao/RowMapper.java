package org.marker.certificate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 高级货，不解释
 * @author marker
 * @version 1.0
 * @param <T>
 */
public interface RowMapper<T> {

	
	
	/**
	 * 抓取数据
	 * @param rs
	 * @param index
	 * @return
	 * @throws SQLException
	 */
	public T fetch(ResultSet rs, int index) throws SQLException;
}
