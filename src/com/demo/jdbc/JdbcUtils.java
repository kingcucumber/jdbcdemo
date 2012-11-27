package com.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.demo.jdbc.datasource.MyDataSource;

public class JdbcUtils {
	private static String url = "jdbc:mysql://localhost:3306/buy";
	private static String username = "root";
	private static String password = "root";

	private static MyDataSource myDataSource = null;

	private JdbcUtils() {

	}

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			myDataSource =  new MyDataSource();
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError();
		}
	}

	public static Connection getConnection() throws Exception {
		return myDataSource.getConnection();
	}

	public static void free(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
//					if (conn != null)
//						conn.close();
					myDataSource.free(conn);
				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
		}
	}

}
