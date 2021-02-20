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
    private static final String DB_CONNECTION = "dbConnection";
    private static final String USERNAME = "username";
    private static final String HISTORY_JSP = "history.jsp";
    private static final String ORDERS = "orders";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute(USERNAME);
        Connection connection = (Connection) getServletContext().getAttribute(DB_CONNECTION);
        List<Order> orders = new Dao().getOrdersOfUser(connection, username);
        req.setAttribute(ORDERS, orders);
        req.getRequestDispatcher(HISTORY_JSP).forward(req, resp);
    }
}
