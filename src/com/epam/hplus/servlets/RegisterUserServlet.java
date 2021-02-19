package com.epam.hplus.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.text.MessageFormat;

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
		String pagePath = req.getServletContext().getRealPath("register.html");
		String page = getFileAsString(pagePath);
		page = replacePlaceholders(page, "");
		resp.getWriter().append(page);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		User user = createInstanceOfUser(req);
		Connection connection = (Connection) getServletContext().getAttribute("dbConnection");
		int rowsAffected = new Dao().createUser(connection, user);
		String resultOfQuery;
		if (rowsAffected == 0) {
			resultOfQuery = "User was't registered, an errow occured";
		} else {
			resultOfQuery = "User registered successfully";
		}
		String pagePath = req.getServletContext().getRealPath("register.html");
		String resultPage = getFileAsString(pagePath);
		resultPage = replacePlaceholders(resultPage, resultOfQuery);
		resp.getWriter().append(resultPage);
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
	
	private String getFileAsString(String filePath) {
		StringBuilder fileString = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				fileString.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileString.toString();
	}
	
	private String replacePlaceholders(String fileString, String resultOfQuery) {
		fileString = MessageFormat.format(fileString, resultOfQuery);
		return fileString;
	}
}
