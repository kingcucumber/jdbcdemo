package com.demo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLInject {

	public static void main(String[] args) throws Exception {
		
		read("cisco");
		
		
	
		read1("cisco");
	
		
	}

	static void read(String name) throws Exception {
		Connection conn = null;
		// Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			// st = conn.createStatement();

			//String sql = "select * from user where Name='" + name + "'";
			long start = System.currentTimeMillis();
			String sql = "select * from user where Name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			
			rs = ps.executeQuery();
			
			System.out.println("sql : " + sql);

			while (rs.next()) {
				System.out.println(rs.getObject(1) + "\t" + rs.getObject(2)
						+ "\t" + rs.getObject(3) + "\t" + rs.getObject(4)
						+ "\t" + rs.getObject(5) + "\t" + rs.getObject(6));
			}
			long end = System.currentTimeMillis();
			System.out.println("read:" + (end-start));
		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}
	
	
	static void read1(String name) throws Exception {
		Connection conn = null;
		Statement st = null;
		//PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			 st = conn.createStatement();

			 
			 long start = System.currentTimeMillis();
			String sql = "select * from user where Name='" + name + "'";
			//String sql = "select * from user where Name=?";
			st = conn.createStatement();
			
			rs = st.executeQuery(sql);
			
			System.out.println("sql : " + sql);

			while (rs.next()) {
				System.out.println(rs.getObject(1) + "\t" + rs.getObject(2)
						+ "\t" + rs.getObject(3) + "\t" + rs.getObject(4)
						+ "\t" + rs.getObject(5) + "\t" + rs.getObject(6));
			}
			long end = System.currentTimeMillis();
			System.out.println("read1:" + (end-start));
		} finally {
			JdbcUtils.free(rs,st, conn);
		}
	}
}
