package com.epam.hplus.listeners;

import java.sql.Connection;
import java.sql.SQLException;

import com.epam.hplus.dao.DbConnection;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		String url = context.getInitParameter("dbUrl");
		String user = context.getInitParameter("dbUser");
		String password = context.getInitParameter("dbPassword");
		Connection connection = DbConnection.getConnectionToDatabase(url, user, password);
		context.setAttribute("dbConnection", connection);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			ServletContext context = sce.getServletContext();
			Connection connection = (Connection) context.getAttribute("dbConnection");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
