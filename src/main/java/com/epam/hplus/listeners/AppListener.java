package com.epam.hplus.listeners;

import java.sql.Connection;
import java.sql.SQLException;

import com.epam.hplus.dao.DbConnector;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import static com.epam.hplus.constants.Context.APP_DB_CONNECTION;
import static com.epam.hplus.constants.Context.APP_DB_PASSWORD;
import static com.epam.hplus.constants.Context.APP_DB_URL;
import static com.epam.hplus.constants.Context.APP_DB_USER;

@WebListener
public class AppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String url = context.getInitParameter(APP_DB_URL);
        String user = context.getInitParameter(APP_DB_USER);
        String password = context.getInitParameter(APP_DB_PASSWORD);
        Connection connection = DbConnector.getConnectionToDatabase(url, user, password);
        context.setAttribute(APP_DB_CONNECTION, connection);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ServletContext context = sce.getServletContext();
            Connection connection = (Connection) context.getAttribute(APP_DB_CONNECTION);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
