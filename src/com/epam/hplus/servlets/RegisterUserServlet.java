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

@WebServlet(urlPatterns = "/register")
public class RegisterUserServlet extends HttpServlet {
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String FIRST_NAME = "fname";
	private static final String LAST_NAME = "lname";
	private static final String ACTIVITY = "activity";
	private static final String AGE = "age";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.getRequestDispatcher("register.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		User user = createInstanceOfUser(req);
		Connection connection = (Connection) getServletContext().getAttribute("dbConnection");
		int rowsAffected = new Dao().createUser(connection, user);
		String registraionStatus;
		if (rowsAffected == 0) {
			registraionStatus = "User was't registered, an errow occured";
		} else {
			registraionStatus = "User registered successfully";
		}
		req.setAttribute("registraionStatus", registraionStatus);
		req.getRequestDispatcher("register.jsp").forward(req, resp);
	}
	
	private User createInstanceOfUser(HttpServletRequest req) {
		String username = req.getParameter(USERNAME);
		String password = req.getParameter(PASSWORD);
		String firstName = req.getParameter(FIRST_NAME);
		String lastName = req.getParameter(LAST_NAME);
		String activity = req.getParameter(ACTIVITY);
		int age = Integer.valueOf(req.getParameter(AGE));
		return new User(username, password, firstName, lastName, activity, age);
	}
}
