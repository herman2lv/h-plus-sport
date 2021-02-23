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

import static com.epam.hplus.constants.Context.APP_DB_CONNECTION;
import static com.epam.hplus.constants.Context.REQUEST_USER;
import static com.epam.hplus.constants.Context.SESSION_USERNAME;
import static com.epam.hplus.constants.JspFiles.PROFILE_JSP;
import static com.epam.hplus.constants.ServletsUrlPatterns.PROFILE_SERVLET;

@WebServlet(urlPatterns = PROFILE_SERVLET)
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute(SESSION_USERNAME);
        Connection connection = (Connection) getServletContext().getAttribute(APP_DB_CONNECTION);
        User user = new Dao().getUserProfile(connection, username);
        req.setAttribute(REQUEST_USER, user);
        req.getRequestDispatcher(PROFILE_JSP).forward(req, resp);
    }
}
