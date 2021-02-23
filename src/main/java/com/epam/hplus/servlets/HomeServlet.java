package com.epam.hplus.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.epam.hplus.constants.JspFiles.INDEX_JSP;
import static com.epam.hplus.constants.ServletsUrlPatterns.HOME_SERVLET;
import static com.epam.hplus.constants.ServletsUrlPatterns.INDEX_SERVLET;

@WebServlet(urlPatterns = {HOME_SERVLET, INDEX_SERVLET})
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(INDEX_JSP).forward(req, resp);
    }
}
