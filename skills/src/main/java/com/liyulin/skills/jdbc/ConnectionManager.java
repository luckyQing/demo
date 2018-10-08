package com.liyulin.skills.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionManager {
	
	private static String DRIVER;
	private static String URL;
	private static String USERNAME;
	private static String PASSWORD;
	static {
		Properties pro = new Properties();
		InputStream in = ConnectionManager.class.getClassLoader().getResourceAsStream("config/dbconfig.properties");
		try {
			pro.load(in);
			DRIVER = pro.getProperty("driver");
			URL = pro.getProperty("url");
			USERNAME = pro.getProperty("username");
			PASSWORD = pro.getProperty("password");
		} catch (IOException e) {
			log.error("load dbconfig error", e);
		}
	}

	// 创建连接
	public Connection openConnection() {
		Connection con = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			log.error("类没找到", e);
		} catch (SQLException e) {
			log.error("sql exception", e);
		}
		return con;
	}

	// 关闭数据库
	public void closeDatabase(Connection con, PreparedStatement preparedStatement, ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (preparedStatement != null && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			log.error("sql exception", e);
		}
	}

}
