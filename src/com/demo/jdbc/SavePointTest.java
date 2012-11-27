package com.demo.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class SavePointTest {

	static void test() throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		Savepoint sp = null;
		try {
			conn = JdbcUtils.getConnection();
			conn.setAutoCommit(false);
			// conn = JdbcUtilsSingle.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "update user set Coin = Coin - 10 where UserId = 3";
			st.executeUpdate(sql);
			sp = conn.setSavepoint();
			sql = "update user set Coin = Coin - 10 where UserId = 1";
			st.executeUpdate(sql);

			sql = "select Coin from user where UserId = 2";
			rs = st.executeQuery(sql);

			// rs =
			// st.executeQuery("select UserId, Name,Password ,Coin ,Type,Consume from user");
			int coin = 0;
			if (rs.next()) {
				coin = rs.getInt("Coin");
			}
			if (coin > 300) {
				throw new RuntimeException("超过最大值了");
			}
			sql = "update user set Coin = Coin + 10 where UserId = 2";
			st.executeUpdate(sql);
			conn.commit();

		}catch(RuntimeException e){
			if(conn!=null && sp!= null){
				conn.rollback(sp);
				conn.commit();
		      }
		}finally {
			JdbcUtils.free(rs, st, conn);
		}
	}
}