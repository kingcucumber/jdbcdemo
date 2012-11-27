package com.demo.jdbc.dao;

import com.demo.jdbc.dao.impl.UserDaoJdbcImpl;
import com.demo.jdbc.domain.User;

public class UserDaoTest {
	public static void main(String[] args) {
		//UserDao userDao = new UserDaoJdbcImpl();
		UserDao userDao = DaoFactory.getInstance().getUserDao();
		User user = new User();

		user.setUserId(13);
		user.setName("xunlei");
		user.setPassword("xunlei");
		user.setCoin(490);
		user.setType("net");
		user.setConsume(20);

		userDao.addUser(user);
		System.out.println("userid = " + user.getUserId());

		User u = userDao.findUser(user.getName(), null);
		System.out.println(u.getUserId());
//
//		 User u = userDao.getUser(10);
//		 System.out.println(u.getCoin());
//		 u.setCoin(500);
//		 userDao.update(u);
//		 System.out.println(u.getCoin());
//		User u = userDao.findUser("tencent", null);
//		userDao.delete(u);
	}
}
