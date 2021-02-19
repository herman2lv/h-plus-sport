package com.epam.hplus.servlets;

import java.io.IOException;
import java.sql.Connection;

import com.epam.hplus.beans.User;
import com.epam.hplus.dao.Dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String username = (String) req.getSession().getAttribute("username");
		Connection connection = (Connection) getServletContext().getAttribute("dbConnection");
		User user = new Dao().getUserProfile(connection, username);
		req.setAttribute("user", user);
		req.getRequestDispatcher("profile.jsp").forward(req, resp);
	}
}
