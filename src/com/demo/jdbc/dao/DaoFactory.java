package com.demo.jdbc.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {

	private static UserDao userDao = null;
	private static DaoFactory instance = new DaoFactory();
	

	private DaoFactory() {

		try {
			Properties prop = new Properties();
			InputStream inStream = new FileInputStream(new File("daoConfig.properties"));
			prop.load(inStream);
			String userDaoClass = prop.getProperty("userDaoClass");
		
			Class clazz = Class.forName(userDaoClass);
			userDao = (UserDao)clazz.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ExceptionInInitializerError(e);
		}
	}

	public static DaoFactory getInstance() {
		return instance;
	}

	public UserDao getUserDao() {
		return userDao;
	}
}
