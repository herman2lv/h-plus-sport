package com.epam.hplus.servlets;

import java.io.IOException;

import com.epam.hplus.beans.DbConnectionConfig;
import com.epam.hplus.dao.Dao;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/sql")
public class SqlServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String tableName = req.getParameter("table");
		if (tableName == null) {
			tableName = "";
		}
		String table = new Dao().getTable(Servlets.getDbConfig(getServletContext()), tableName);
		resp.getWriter().print(table);
	}
}
