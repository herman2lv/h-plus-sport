package com.epam.hplus.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.epam.hplus.beans.Product;
import com.epam.hplus.dao.Dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import static com.epam.hplus.constants.Context.APP_DB_CONNECTION;
import static com.epam.hplus.constants.Context.REQUEST_PRODUCTS;
import static com.epam.hplus.constants.Context.REQUEST_SEARCH;
import static com.epam.hplus.constants.Context.SESSION_SEARCH_STRING;
import static com.epam.hplus.constants.JspFiles.SEARCH_JSP;
import static com.epam.hplus.constants.ServletsUrlPatterns.SEARCH_SERVLET;

@WebServlet(urlPatterns = SEARCH_SERVLET)
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String searchString = req.getParameter(REQUEST_SEARCH);
        if (searchString == null) {
            searchString = "";
        }
        HttpSession session = req.getSession();
        session.setAttribute(SESSION_SEARCH_STRING, searchString);
        Connection connection = (Connection) getServletContext().getAttribute(APP_DB_CONNECTION);
        List<Product> products = new Dao().searchProducts(connection, searchString);
        req.setAttribute(REQUEST_PRODUCTS, products);
        req.getRequestDispatcher(SEARCH_JSP).forward(req, resp);
    }
}
