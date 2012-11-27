package com.demo.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.demo.jdbc.domain.User;

public class ORMTest {

	public static void main(String[] args) throws Exception {
		User user = (User) getObject("select * from user where UserId =1", User.class);
		System.out.println(user);
	}

	static Object getObject(String sql,Class clazz) throws SQLException, Exception, Exception,
			Exception {

		Connection conn = null;
		// Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			// ParameterMetaData pmd = ps.getParameterMetaData();
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			String[] colNames = new String[count];
			for (int i = 1; i <= count; i++) {
				colNames[i - 1] = rsmd.getColumnLabel(i);
			}
			Object object = null;
			if (rs.next()) {
				object = clazz.newInstance();
				for (int i = 0; i < colNames.length; i++) {
					String colName = colNames[i];
					Method[] ms = object.getClass().getMethods();
					String methodName = "set" + colName;
					for (Method m : ms) {
					//	System.out.println(m.getName());
						if (methodName.equals(m.getName())) {
							m.invoke(object, rs.getObject(colName));
						}
					}
				}
			}
			return object;
		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}

}
