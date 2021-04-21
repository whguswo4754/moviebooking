package com.freshworks.freshcinemas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/freshcinemas?autoReconnect=true&useSSL=false";
	private static String user = "root";
	private static String password = "abhishek@74";

	public static Connection getConnection() throws DBConnectionException {
		Connection conn = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DBConnectionException();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBConnectionException();
		}
		return conn;
	}

	public static void closeConn(Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//check NULL
	public static void closeStatement(Statement st, ResultSet rs) {
		try {
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeStatement(Statement st) {
		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeResources(Connection conn,Statement st, ResultSet rs) {
		try {
			if (conn != null)
				conn.close();
			
			if (st != null)
				st.close();
			
			if (rs != null)
				rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
