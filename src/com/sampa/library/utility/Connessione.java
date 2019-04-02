package com.sampa.library.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connessione {


	private static Connection conn = null;

	private static Connection connessione() {
		try {
			if (conn == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/libreria?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root","1234");
			}
			return conn;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Connection getConnection() {
		return connessione();
	}
	
//	public static Connection getConnection() throws NamingException, SQLException {
//		
//		InitialContext initContext = new InitialContext();
//		DataSource ds = (DataSource)initContext.lookup("java:/hash");
//		conn = ds.getConnection();
//		return conn;
//	}
}