package com.demo.jdbc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BlobTest {
	public static void main(String []args) throws SQLException, IOException{
		create();
		
	}
	
	static void create() throws SQLException, IOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			// conn = JdbcUtilsSingle.getInstance().getConnection();
			String sql = "insert into blobdemo(id,blob)values(?,?)";
			ps = conn.prepareStatement(sql);
			
			File file = new File("Girl.jpg");
			InputStream in = new BufferedInputStream(new FileInputStream(file));
			ps.setInt(1, 1);
			ps.setBinaryStream(2, in,(int)file.length());
			int i = ps.executeUpdate();
			in.close();
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
				Blob blob = rs.getBlob(1);
				InputStream in = blob.getBinaryStream();
				//reader = rs.getCharacterStream(1);
				File file = new File("JdbcUtils_bak.java");
				OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
				
				byte[] buff = new byte[1024];
				for(int i= 0;(i=in.read(buff))> 0;){
					out.write(buff,0,i);
				}
				out.close();
				in.close();		
			}
		} finally {
			JdbcUtils.free(rs, st, conn);
		}

	}
}
