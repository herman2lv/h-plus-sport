package com.epam.hplus.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.epam.hplus.constants.Database.MYSQL_DRIVER;

public class DbConnector {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbConnector.class);
    protected static final String LOG_CONNECTION_TO_DB_CREATED = "Connection to DB created";

    private DbConnector() {
    }

    public static Connection getConnectionToDatabase(String url, String user, String password) {
        Connection connection = null;
        try {
            Class.forName(MYSQL_DRIVER);
            connection = DriverManager.getConnection(url, user, password);
            LOGGER.info(LOG_CONNECTION_TO_DB_CREATED);
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return connection;
    }
}
