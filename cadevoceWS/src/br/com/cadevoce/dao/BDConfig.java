package br.com.cadevoce.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;

public class BDConfig {
	// Acesso Azure
  static String host = "127.0.0.1:57215";
  static String database = "cadevocedb";
  static String user = "azure";
  static String password = "6#vWHD_$";

	// Acesso Local
//	static String host = "localhost:3306";
//	static String database = "cadevocedb";
//	static String user = "root";
//	static String password = "";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("MySQL JDBC driver NOT detected in library path.", e);
		}
		System.out.println("MySQL JDBC driver detected in library path.");

		Connection connection = null;

		// Initialize connection object
		try {
			String url = String.format("jdbc:mysql://%s/%s", host, database);

			// Set connection properties.
			Properties properties = new Properties();
			properties.setProperty("user", user);
			properties.setProperty("password", password);
			properties.setProperty("useSSL", "true");
			properties.setProperty("verifyServerCertificate", "true");
			properties.setProperty("requireSSL", "false");

			// get connection
			return connection = (Connection) DriverManager.getConnection(url, properties);
		} catch (SQLException e) {
			throw new SQLException("Failed to create connection to database.", e);
		}
	}
}
