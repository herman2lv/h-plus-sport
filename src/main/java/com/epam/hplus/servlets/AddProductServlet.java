package com.epam.hplus.servlets;

import com.epam.hplus.beans.Product;
import com.epam.hplus.dao.Dao;
import com.epam.hplus.resources.ConfigurationManger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static com.epam.hplus.constants.Context.APP_DB_CONNECTION;
import static com.epam.hplus.constants.Context.REQUEST_PRODUCT;
import static com.epam.hplus.constants.Context.REQUEST_PRODUCTS;
import static com.epam.hplus.constants.Context.SESSION_CART;
import static com.epam.hplus.constants.Context.SESSION_SEARCH_STRING;
import static com.epam.hplus.constants.ServletsUrlPatterns.ADD_PRODUCT_SERVLET;

@WebServlet(urlPatterns = ADD_PRODUCT_SERVLET)
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Product> cart = (ArrayList<Product>) session.getAttribute(SESSION_CART);
        if (cart == null) {
            cart = new ArrayList<>();
        }
        Connection connection = (Connection) getServletContext().getAttribute(APP_DB_CONNECTION);
        Dao dao = new Dao();
        int productId = Integer.parseInt(req.getParameter(REQUEST_PRODUCT));
        Product product = dao.getProductById(connection, productId);
        cart.add(product);
        session.setAttribute(SESSION_CART, cart);
        String searchString = (String) session.getAttribute(SESSION_SEARCH_STRING);
        List<Product> products = dao.searchProducts(connection, searchString);
        req.setAttribute(REQUEST_PRODUCTS, products);
        req.getRequestDispatcher(ConfigurationManger.getProperty("page.search")).forward(req, resp);
    }
}
