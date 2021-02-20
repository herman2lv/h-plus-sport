package com.epam.hplus.listeners;

import java.sql.Connection;
import java.sql.SQLException;

import com.epam.hplus.dao.DbConnector;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {
    private static final String DB_CONNECTION = "dbConnection";
    private static final String DB_PASSWORD = "dbPassword";
    private static final String DB_USER = "dbUser";
    private static final String DB_URL = "dbUrl";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String url = context.getInitParameter(DB_URL);
        String user = context.getInitParameter(DB_USER);
        String password = context.getInitParameter(DB_PASSWORD);
        Connection connection = DbConnector.getConnectionToDatabase(url, user, password);
        context.setAttribute(DB_CONNECTION, connection);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ServletContext context = sce.getServletContext();
            Connection connection = (Connection) context.getAttribute(DB_CONNECTION);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
