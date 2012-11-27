package com.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Base {

	static void test() throws SQLException {
		// 1.注册驱动
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		// System.setProperty("jdbc.drivers","com.mysql.jdbc.Driver");
		// Class.forName("com.mysql.jdbc.Driver"); 推荐使用

		// 2.建立连接
		String url = "jdbc:mysql://localhost:3306/buy";
		String username = "root";
		String password = "root";
		Connection conn1 = (Connection) DriverManager.getConnection(url,
				username, password);

		Connection conn = (Connection) DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/buy", "root", "root");

		// 3.创建语句
		Statement st = conn.createStatement();

		// 4.执行语句
		ResultSet rs = (ResultSet) st.executeQuery("select * from user");

		// 5.处理结果
		while (rs.next()) {
			System.out.println(rs.getObject(1) + "\t" + rs.getObject(2) + "\t"
					+ rs.getObject(3) + "\t" + rs.getObject(4) + "\t"
					+ rs.getObject(5) + "\t" + rs.getObject(6));

		}
		// 6.释放资源
		rs.close();
		st.close();
		conn.close();
	}

	static void template() throws Exception {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select * from user");
			while (rs.next()) {
				System.out.println(rs.getObject(1) + "\t" + rs.getObject(2)
						+ "\t" + rs.getObject(3) + "\t" + rs.getObject(4)
						+ "\t" + rs.getObject(5) + "\t" + rs.getObject(6));
			}
		} finally{
			JdbcUtils.free(rs, st, conn);
		}	
	}

	public static void main(String[] args) throws Exception {
//		Base base  = new Base();
//		base.template();
		
		for(int i = 0;i<12;i++){
			Connection conn = JdbcUtils.getConnection();
			System.out.println(conn);
			//JdbcUtils.free(null, null, conn);
		}
	}

}
