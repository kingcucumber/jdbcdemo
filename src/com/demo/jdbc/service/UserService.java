package com.demo.jdbc.service;

import com.demo.jdbc.dao.UserDao;
import com.demo.jdbc.domain.User;

public class UserService {
	
	private UserDao userDao;
	
	public void register(User user){
		this.userDao.addUser(user);
		
		//sendMail.send(User user);
		
	}
}
