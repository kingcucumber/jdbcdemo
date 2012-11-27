package com.demo.jdbc;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultSetMetaTest {
	public static void main(String[] args) throws Exception {
		List<Map<String,Object>> datas = read("select Name,Password from user where Userid<10");
		
		System.out.println(datas);
	}

	static List<Map<String, Object>> read(String sql) throws Exception {
		Connection conn = null;
		// Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ParameterMetaData pmd = ps.getParameterMetaData();
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();

			String[] colNames = new String[count];

			for (int i = 1; i <= count; i++) {
				 System.out.print(rsmd.getColumnClassName(i) + "\t");
				 System.out.print(rsmd.getColumnName(i) + "\t");
				 System.out.println(rsmd.getColumnLabel(i));
				colNames[i-1] = rsmd.getColumnName(i);
			}
			List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
			
			while (rs.next()) {
				Map<String,Object> data = new HashMap<String, Object>();
				for (int i = 0; i < colNames.length; i++) {
					data.put(colNames[i], rs.getObject(colNames[i]));
				}
				datas.add(data);
			}
			return datas;
		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}
}
