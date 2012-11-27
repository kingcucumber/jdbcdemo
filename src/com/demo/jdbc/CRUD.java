package com.demo.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUD {
	public static void main(String[] args) throws Exception {
		delete();
		read();
		create();
		update();
		read();
	}

	static void read() throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			// conn = JdbcUtilsSingle.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select UserId, Name,Password ,Coin ,Type,Consume from user");
			while (rs.next()) {
				System.out.println(rs.getObject("UserId") + "\t"
						+ rs.getObject("Name") + "\t"
						+ rs.getObject("Password") + "\t"
						+ rs.getObject("Coin") + "\t" + rs.getObject("Consume")
						+ "\t" + rs.getObject("Type"));
			}
		} finally {
			JdbcUtils.free(rs, st, conn);
		}

	}

	static void create() throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			// conn = JdbcUtilsSingle.getInstance().getConnection();
			st = conn.createStatement();

			String sql = "insert into user(UserId, Name,Password ,Coin ,Type,Consume) values(8,'baidu','baidu',345,'net',230)";
			int i = st.executeUpdate(sql);
			System.out.println("i= " + i);

		} finally {
			JdbcUtils.free(rs, st, conn);
		}

	}

	static void update() throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			// conn = JdbcUtilsSingle.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "update user set Coin=Coin + 100";
			int j = st.executeUpdate(sql);
			System.out.println("j= " + j);
		} finally {
			JdbcUtils.free(rs, st, conn);
		}
	}
	
	
	static void delete() throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			// conn = JdbcUtilsSingle.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "delete  from user where UserId= 8";
			int z = st.executeUpdate(sql);
			System.out.println("z= " + z);
		} finally {
			JdbcUtils.free(rs, st, conn);
		}
	}
	
	

}
