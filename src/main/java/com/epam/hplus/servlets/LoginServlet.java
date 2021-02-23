package com.epam.hplus.servlets;

import com.epam.hplus.dao.Dao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;

import static com.epam.hplus.constants.Context.APP_DB_CONNECTION;
import static com.epam.hplus.constants.Context.LOGIN_REQUEST_PASSWORD;
import static com.epam.hplus.constants.Context.LOGIN_REQUEST_USERNAME;
import static com.epam.hplus.constants.Context.REQUEST_ERROR;
import static com.epam.hplus.constants.Context.SESSION_USERNAME;
import static com.epam.hplus.constants.JspFiles.LOGIN_JSP;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);
    private static final String ERROR_MESSAGE = "Invalid credentials. Please, login again";
    protected static final String LOG_INVALID_CREDENTIALS =
            "User tried to login using invalid credentials";
    private static final String CART_SERVLET = "/cart";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(LOGIN_JSP);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter(LOGIN_REQUEST_USERNAME);
        String password = req.getParameter(LOGIN_REQUEST_PASSWORD);
        Connection connection = (Connection) getServletContext().getAttribute(APP_DB_CONNECTION);
        boolean isValidUser = new Dao().validateUser(connection, username, password);
        if (isValidUser) {
            req.getSession().setAttribute(SESSION_USERNAME, username);
            req.getRequestDispatcher(CART_SERVLET).forward(req, resp);
        } else {
            LOGGER.info(LOG_INVALID_CREDENTIALS);
            req.setAttribute(REQUEST_ERROR, ERROR_MESSAGE);
            req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);
        }
    }
}
