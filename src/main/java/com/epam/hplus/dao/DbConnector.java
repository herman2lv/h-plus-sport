package com.epam.hplus.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";

    private DbConnector() {
    }

    public static Connection getConnectionToDatabase(String url, String user, String password) {
        Connection connection = null;
        try {
            Class.forName(MYSQL_DRIVER);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
