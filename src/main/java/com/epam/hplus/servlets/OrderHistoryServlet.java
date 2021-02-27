package com.epam.hplus.servlets;

import com.epam.hplus.beans.Order;
import com.epam.hplus.dao.Dao;
import com.epam.hplus.resources.ConfigurationManger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import static com.epam.hplus.constants.Context.APP_DB_CONNECTION;
import static com.epam.hplus.constants.Context.REQUEST_ORDERS;
import static com.epam.hplus.constants.Context.SESSION_USERNAME;
import static com.epam.hplus.constants.ServletsUrlPatterns.HISTORY_SERVLET;

@WebServlet(urlPatterns = HISTORY_SERVLET)
public class OrderHistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute(SESSION_USERNAME);
        Connection connection = (Connection) getServletContext().getAttribute(APP_DB_CONNECTION);
        List<Order> orders = new Dao().getOrdersOfUser(connection, username);
        req.setAttribute(REQUEST_ORDERS, orders);
        req.getRequestDispatcher(ConfigurationManger.getProperty("page.history")).forward(req, resp);
    }
}
