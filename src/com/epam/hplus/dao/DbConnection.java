package com.epam.hplus.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.epam.hplus.beans.DbConnectionConfig;

public class DbConnection {
	public static Connection getConnectionToDatabase(DbConnectionConfig dbConfig) {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(dbConfig.getUrl(), 
					dbConfig.getUser(), dbConfig.getPassword());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
