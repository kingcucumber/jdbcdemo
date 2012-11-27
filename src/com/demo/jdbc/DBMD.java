package com.demo.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class DBMD {

	public static void main(String  []args) throws SQLException{
		Connection conn = JdbcUtils.getConnection();
		
		DatabaseMetaData dbmd = conn.getMetaData();
		
		System.out.println("db name:" + dbmd.getDatabaseProductName())	;
		
		System.out.println("tx: " + dbmd.supportsTransactions());
		
		conn.close();
	}
}
