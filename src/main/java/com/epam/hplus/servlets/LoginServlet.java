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
    private static final String ERROR_MESSAGE = "Invalid credentials. Please, login again";
    private static final String ERROR = "error";
    private static final String CART_JSP = "/cart.jsp";
    private static final String DB_CONNECTION = "dbConnection";
    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";
    private static final String LOGIN_JSP = "login.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(LOGIN_JSP);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);
        Connection connection = (Connection) getServletContext().getAttribute(DB_CONNECTION);
        boolean isValidUser = new Dao().validateUser(connection, username, password);
        if (isValidUser) {
            req.getSession().setAttribute(USERNAME, username);
            req.getRequestDispatcher(CART_JSP).forward(req, resp);
        } else {
            req.setAttribute(ERROR, ERROR_MESSAGE);
            req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);
        }
    }
}
