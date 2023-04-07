package com.bhavika.ContactDirectory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	Connection conn;
	
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	private String className = "com.mysql.cj.jdbc.Driver";
	private String connString = "jdbc:mysql://localhost:3306/contactDirectory?useSSL=false&serverTimezone=UTC";
	private String user = "CD";
	private String password = "Password@12";
	
	public DbConnection() {
		getConnection();
	}
	
	public void getConnection() {
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(connString, user, password);
			System.out.println(conn);
			System.out.println("Connected");
		}catch (Exception e) {
			System.out.println("------------------------");
			System.out.println(e.getMessage());
		}
	}
	
}
