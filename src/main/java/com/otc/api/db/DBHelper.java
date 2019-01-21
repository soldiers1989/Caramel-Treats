package com.otc.api.db;

import java.sql.*;

public class DBHelper {
	public static final String url = "jdbc:mysql://47.93.120.116:3306/otc";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "#FJS87vzx$i#";

	public Connection conn = null;
	public PreparedStatement pst = null;

	public DBHelper(String sql) {
		try {
			Class.forName(name);//指定连接类型
			conn = DriverManager.getConnection(url, user, password);//获取连接
			pst = conn.prepareStatement(sql);//准备执行语句
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn(){
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//获取连接
		return null;
	}
	
	public static void close(Connection conn ,PreparedStatement pst) {
		try {
			conn.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close2(Connection conn ,PreparedStatement pst,ResultSet rs,Statement stmt) {
		try {
			conn.close();
			pst.close();
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

