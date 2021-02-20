package com.epam.hplus.servlets;

import java.io.IOException;
import java.sql.Connection;

import com.epam.hplus.dao.Dao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Connection connection = (Connection) getServletContext().getAttribute("dbConnection");
		boolean isValidUser = new Dao().validateUser(connection, username, password);
		if (isValidUser) {
			req.getSession().setAttribute("username", username);
			req.getRequestDispatcher("/cart.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", "Invalid credentials. Please, login again");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}
}
