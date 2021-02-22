package com.epam.hplus.listeners;

import java.sql.Connection;
import java.sql.SQLException;

import com.epam.hplus.dao.DbConnector;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.hplus.constants.Context.APP_DB_CONNECTION;
import static com.epam.hplus.constants.Context.APP_DB_PASSWORD;
import static com.epam.hplus.constants.Context.APP_DB_URL;
import static com.epam.hplus.constants.Context.APP_DB_USER;

@WebListener
public class AppListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppListener.class);
    protected static final String LOG_DB_CONNECTION_SET_UP =
            "DB connection has been set up as ServletContextAttribute";
    protected static final String LOG_DB_CONNECTION_WAS_CLOSED = "DB connection was closed";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String url = context.getInitParameter(APP_DB_URL);
        String user = context.getInitParameter(APP_DB_USER);
        String password = context.getInitParameter(APP_DB_PASSWORD);
        Connection connection = DbConnector.getConnectionToDatabase(url, user, password);
        context.setAttribute(APP_DB_CONNECTION, connection);
        LOGGER.info(LOG_DB_CONNECTION_SET_UP);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ServletContext context = sce.getServletContext();
            Connection connection = (Connection) context.getAttribute(APP_DB_CONNECTION);
            connection.close();
            LOGGER.info(LOG_DB_CONNECTION_WAS_CLOSED);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
