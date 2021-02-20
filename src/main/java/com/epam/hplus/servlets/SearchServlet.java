package com.epam.hplus.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.epam.hplus.beans.Product;
import com.epam.hplus.dao.Dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    private static final String DB_CONNECTION = "dbConnection";
    private static final String CART = "cart";
    private static final String SEARCH_JSP = "search.jsp";
    private static final String PRODUCTS = "products";
    private static final String CART_SIZE = "cartSize";
    private static final String SEARCH_STRING = "searchString";
    private static final String SEARCH = "search";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String searchString = req.getParameter(SEARCH);
        if (searchString == null) {
            searchString = "";
        }
        HttpSession session = req.getSession();
        session.setAttribute(SEARCH_STRING, searchString);
        Connection connection = (Connection) getServletContext().getAttribute(DB_CONNECTION);
        List<Product> products = new Dao().searchProducts(connection, searchString);
        List<String> cart = (ArrayList<String>) session.getAttribute(CART);
        int cartSize = 0;
        if (cart != null) {
            cartSize = cart.size();
        }
        req.setAttribute(CART_SIZE, cartSize);
        req.setAttribute(PRODUCTS, products);
        req.getRequestDispatcher(SEARCH_JSP).forward(req, resp);
    }
}
