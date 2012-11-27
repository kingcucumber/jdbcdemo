package com.demo.jdbc.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class MyDataSource {

	private static String url = "jdbc:mysql://localhost:3306/buy";
	private static String user = "root";
	private static String password = "root";

	private static int initCount = 5;
	private static int maxCount = 10;
	private int currentCount = 0;

	private static MyDataSource myDataSource = new MyDataSource();

	private LinkedList<Connection> connectionsPool = new LinkedList<Connection>();

	public MyDataSource() {
		try {
			for (int i = 0; i < initCount; i++) {
				this.connectionsPool.addLast(this.createConnection());
				this.currentCount++;
			}
		} catch (SQLException e) {
			throw new ExceptionInInitializerError();
		}
	}

	public Connection getConnection() throws Exception {
		synchronized (connectionsPool) {
			if (this.connectionsPool.size() > 0)
				return this.connectionsPool.removeFirst();
			if (this.currentCount < maxCount) {
				this.currentCount++;
				return this.createConnection();
			}
			throw new SQLException("too many connections!");
		}
	}

	public void free(Connection conn) {
		this.connectionsPool.addLast(conn);
	}

	private Connection createConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
}
