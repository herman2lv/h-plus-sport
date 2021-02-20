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
    private static final String PROFILE_JSP = "profile.jsp";
    private static final String USER = "user";
    private static final String DB_CONNECTION = "dbConnection";
    private static final String USERNAME = "username";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute(USERNAME);
        Connection connection = (Connection) getServletContext().getAttribute(DB_CONNECTION);
        User user = new Dao().getUserProfile(connection, username);
        req.setAttribute(USER, user);
        req.getRequestDispatcher(PROFILE_JSP).forward(req, resp);
    }
}
