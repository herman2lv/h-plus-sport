package com.epam.hplus.model.pool;

import com.epam.hplus.util.resources.ConfigurationManger;
import com.epam.hplus.util.resources.MessageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public enum ConnectionPool {
    INSTANCE;

    private final BlockingDeque<ProxyConnection> freeConnections;
    private final Queue<ProxyConnection> givenConnections;
    private static final int POOL_SIZE = 8;
    private final Logger logger = LoggerFactory.getLogger(ConnectionPool.class);

    ConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(POOL_SIZE);
        givenConnections = new ArrayDeque<>();
        String driverName = ConfigurationManger.getProperty("db.driver");
        String url = ConfigurationManger.getProperty("db.url");
        String user = ConfigurationManger.getProperty("db.user");
        String password = ConfigurationManger.getProperty("db.password");
        try {
            Class.forName(driverName);
            logger.info(MessageManager.getMessage("log.driverLoaded"));
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                freeConnections.offer(new ProxyConnection(connection));
                logger.info(MessageManager.getMessage("log.connectionCreated"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection && givenConnections.remove(connection)) {
            freeConnections.offer((ProxyConnection) connection);
        } else {
            logger.warn(MessageManager.getMessage("msg.notProxyConnection"));
        }
    }

    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
                logger.info(MessageManager.getMessage("log.connectionClosed"));
            } catch (SQLException | InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
                logger.info(MessageManager.getMessage("log.driverDeregistered"));
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        });
    }
}
