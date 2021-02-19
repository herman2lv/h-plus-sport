package com.epam.hplus.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.epam.hplus.beans.Order;
import com.epam.hplus.dao.Dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/orderHistory")
public class OrderHistoryServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String username = (String) req.getSession().getAttribute("username");
		Connection connection = (Connection) getServletContext().getAttribute("dbConnection");
		List<Order> orders = new Dao().getOrdersOfUser(connection, username);
		req.setAttribute("orders", orders);
		req.getRequestDispatcher("history.jsp").forward(req, resp);
	}
}
