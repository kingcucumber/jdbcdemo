package com.demo.jdbc.dao.refactor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.demo.jdbc.JdbcUtils;


public class AbstractDao {

	public void update(String sql,Object[] args){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			for(int i = 0;i<args.length;i++){
				ps.setObject(i+1, args[i]);
			}
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtils.free(rs, ps, conn);
		}
	}
	
	public Object find(String sql,Object[] args){
		return null;
	}
}
