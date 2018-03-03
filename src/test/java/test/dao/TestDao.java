package test.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.marker.certificate.dao.DBConnection;
 

/**
 * Junit单元测试 
 * @author marker
 * @version 1.0
 */
public class TestDao {

	@org.junit.Test
	public void testConnection() throws PropertyVetoException{
		
		try {
			DBConnection db = DBConnection.getInstance();
			Connection conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from cer_user");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt("id"));
			}
			ps.close();
			conn.close();// 放回去
			
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		
		
	}
}
