package com.demo.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BatchTest {

	public static void main(String []args) {
		createBatch();
		
	}
	static void createBatch()  {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			// conn = JdbcUtilsSingle.getInstance().getConnection();
			String sql = "insert into user(UserId,Name,Password,Coin,Type,Consume) values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < 1000; i++) {
				ps.setInt(1, 20+i);
				ps.setString(2, "Ne"+i);
				ps.setString(3, "Ps"+i);
				ps.setInt(4, 3400);
				ps.setString(5, ""+i);
				ps.setInt(6, 232);
				ps.addBatch();
			}
			int[] is = ps.executeBatch();
		} catch(Exception e){
				throw new RuntimeException(e);
			}
		finally {
		}
			JdbcUtils.free(rs, ps, conn);
		}
	}

