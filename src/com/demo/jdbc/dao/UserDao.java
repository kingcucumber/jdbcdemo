package com.demo.jdbc.dao;

import com.demo.jdbc.domain.User;

public interface UserDao {
	
	public void addUser(User user);
	
	public User getUser(int userid);
	
	public User findUser(String loginName,String password);
	
	public void update(User user);
	
	public void delete(User user);
	
	
}
