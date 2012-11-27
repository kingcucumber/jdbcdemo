package com.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtilsSingle {
	private String url = "jdbc:mysql://localhost:3306/buy";
	private String username = "root";
	private String password = "root";

	private static JdbcUtilsSingle instance;

	private JdbcUtilsSingle() {
	}

	public static JdbcUtilsSingle getInstance() {
		if (instance == null) {
			synchronized (JdbcUtilsSingle.class) {
				if (instance == null) {
					instance = new JdbcUtilsSingle();
				}
			}
		}
		return instance;
	}

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError();
		}
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	public void free(ResultSet rs, Statement st, Connection conn) {
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
					if (conn != null)
						conn.close();
				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
		}
	}

}
