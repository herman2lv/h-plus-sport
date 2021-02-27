package com.epam.hplus.dao;

import com.epam.hplus.resources.ConfigurationManger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbConnector.class);
    protected static final String LOG_CONNECTION_TO_DB_CREATED = "Connection to DB created";
    private static Connection connection;

    private DbConnector() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String url = ConfigurationManger.getProperty("db.url");
                String user = ConfigurationManger.getProperty("db.user");
                String password = ConfigurationManger.getProperty("db.password");
                connection = DriverManager.getConnection(url, user, password);
                LOGGER.info(LOG_CONNECTION_TO_DB_CREATED);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return connection;
    }
}
