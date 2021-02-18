package com.epam.hplus.servlets;

import com.epam.hplus.beans.DbConnectionConfig;

import jakarta.servlet.ServletContext;

public class Servlets {
	public static DbConnectionConfig getDbConfig(ServletContext context) {
		String url = context.getInitParameter("dbUrl");
		String user = context.getInitParameter("dbUser");
		String password = context.getInitParameter("dbPassword");
		return new DbConnectionConfig(url, user, password);
	}
}
