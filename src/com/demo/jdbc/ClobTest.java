package com.demo.jdbc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClobTest {

	public static void main(String []args) throws Exception, IOException{
		//create();
		read();
	}
	static void create() throws SQLException, IOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			// conn = JdbcUtilsSingle.getInstance().getConnection();
			String sql = "insert into TextDemo(id,text) values(?,?)";
			ps = conn.prepareStatement(sql);
			
			File file = new File("src/com/demo/jdbc/JdbcUtils.java");
			Reader reader = new BufferedReader(new FileReader(file));
			ps.setInt(1, 1);
			ps.setCharacterStream(2, reader, (int) file.length());
			int i = ps.executeUpdate();
			reader.close();
			System.out.println("i= " + i);

		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}
	
	
	static void read() throws SQLException, IOException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			// conn = JdbcUtilsSingle.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select text from textdemo");
			while (rs.next()) {
				Clob clob = rs.getClob(1);
				Reader reader = clob.getCharacterStream();
				//reader = rs.getCharacterStream(1);
				File file = new File("JdbcUtils_bak.java");
				Writer writer = new BufferedWriter(new FileWriter(file));
				
				char[] buff = new char[1024];
				for(int i= 0;(i=reader.read(buff))> 0;){
					writer.write(buff,0,i);
				}
				writer.close();
				reader.close();
				
				
			}
		} finally {
			JdbcUtils.free(rs, st, conn);
		}

	}


}
