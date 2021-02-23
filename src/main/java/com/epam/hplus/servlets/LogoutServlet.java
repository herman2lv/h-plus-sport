package com.epam.hplus.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.epam.hplus.constants.ServletsUrlPatterns.HOME_SERVLET;
import static com.epam.hplus.constants.ServletsUrlPatterns.LOGOUT_SERVLET;

@WebServlet(urlPatterns = LOGOUT_SERVLET)
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getSession().invalidate();
        req.getRequestDispatcher(HOME_SERVLET).forward(req, resp);
    }
}
