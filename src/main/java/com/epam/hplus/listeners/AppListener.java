package com.epam.hplus.listeners;

import com.epam.hplus.dao.DbConnector;
import com.epam.hplus.resources.MessageManager;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

import static com.epam.hplus.constants.Database.MYSQL_DRIVER;

@WebListener
public class AppListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName(MYSQL_DRIVER);
            LOGGER.info(MessageManager.getMessage("log.driverLoaded"));
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            DbConnector.getConnection().close();
            LOGGER.info(MessageManager.getMessage("log.connectionClosed"));
        } catch (
                SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
