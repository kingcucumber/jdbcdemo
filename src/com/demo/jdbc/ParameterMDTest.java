package com.demo.jdbc;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ParameterMDTest {

	public static void main(String[] args) throws Exception {
		Object[] params = new Object[] { "baidu", 500, 200 };

		read("select * from user where Name=? and Coin < ? and Consume > ?",
				params);
	}

	static void read(String sql, Object[] params) throws Exception {
		Connection conn = null;
		// Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ParameterMetaData pmd = ps.getParameterMetaData();

			int count = pmd.getParameterCount();
			for (int i = 1; i <= count; i++) {
				// System.out.print(pmd.getParameterClassName(i)+ "\t");
				// System.out.print(pmd.getParameterType(i)+ "\t");
				// System.out.println(pmd.getParameterTypeName(i)+ "\t");

				ps.setObject(i, params[i - 1]);
			}

			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getObject(1) + "\t" + rs.getObject(2)
						+ "\t" + rs.getObject(3) + "\t" + rs.getObject(4)
						+ "\t" + rs.getObject(5) + "\t" + rs.getObject(6));
			}

		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}
}
