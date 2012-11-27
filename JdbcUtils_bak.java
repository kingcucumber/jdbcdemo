package com.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
	private static String url = "jdbc:mysql://localhost:3306/buy";
	private static String username = "root";
	private static String password = "root";

	private JdbcUtils() {

	}

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError();
		}
	}
	
	public  static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,username,password);
	}
	
	public static void free(ResultSet rs, Statement st, Connection conn	){
		try {
			if (rs != null)
				rs.close();
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (st != null)
					st.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
			finally {
				try{
				if (conn != null)
					conn.close();
				}catch(Exception exp){
					exp.printStackTrace();
				}
			}
		}
	}

}
