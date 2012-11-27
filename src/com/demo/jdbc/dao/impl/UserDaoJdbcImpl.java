package com.demo.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.demo.jdbc.JdbcUtils;
import com.demo.jdbc.dao.DaoException;
import com.demo.jdbc.dao.UserDao;
import com.demo.jdbc.domain.User;

public class UserDaoJdbcImpl implements UserDao {

	@Override
	public void addUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			// conn = JdbcUtilsSingle.getInstance().getConnection();

			String sql = "insert into user(UserId, Name,Password ,Coin ,Type,Consume) values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, user.getUserId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());
			ps.setInt(4, user.getCoin());
			ps.setString(5, user.getType());
			ps.setInt(6, user.getConsume());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			
			if(rs.next()){
				user.setUserId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			// conn = JdbcUtilsSingle.getInstance().getConnection();

			String sql = "delete from  user where Userid=" + user.getUserId();
			ps = conn.prepareStatement(sql);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}

	@Override
	public User getUser(int userid) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = JdbcUtils.getConnection();
			// conn = JdbcUtilsSingle.getInstance().getConnection();

			String sql = "select UserId, Name,Password ,Coin ,Type,Consume from user where UserId=?";
			ps = conn.prepareStatement(sql);

			ps.setInt(1, userid);

			rs = ps.executeQuery();

			while (rs.next()) {
				user = mappingUser(rs);

			}

		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
		return user;
	}

	@Override
	public User findUser(String loginName, String password) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = JdbcUtils.getConnection();
			// conn = JdbcUtilsSingle.getInstance().getConnection();

			String sql = "select UserId, Name,Password ,Coin ,Type,Consume from user where Name=?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, loginName);

			rs = ps.executeQuery();

			while (rs.next()) {
				user = mappingUser(rs);

			}

		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
		return user;
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			// conn = JdbcUtilsSingle.getInstance().getConnection();
			String sql = "update user set Coin=?,Consume=? where UserId = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getCoin());
			ps.setInt(2, user.getConsume());
			ps.setInt(3, user.getUserId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}

	private User mappingUser(ResultSet rs) throws SQLException {
		User user;
		user = new User();
		user.setUserId(rs.getInt(1));
		user.setName(rs.getString(2));
		user.setPassword(rs.getString(3));
		user.setCoin(rs.getInt(4));
		user.setType(rs.getString(5));
		user.setConsume(rs.getInt(6));
		return user;
	}

}
