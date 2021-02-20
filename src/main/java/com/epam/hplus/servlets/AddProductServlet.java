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

import static com.epam.hplus.constants.Context.APP_DB_CONNECTION;
import static com.epam.hplus.constants.Context.SESSION_CART;
import static com.epam.hplus.constants.Context.REQUEST_CART_SIZE;
import static com.epam.hplus.constants.Context.REQUEST_PRODUCT;
import static com.epam.hplus.constants.Context.REQUEST_PRODUCTS;
import static com.epam.hplus.constants.Context.SESSION_SEARCH_STRING;
import static com.epam.hplus.constants.JspFiles.SEARCH_JSP;

@WebServlet(urlPatterns = "/addProduct")
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<String> cart = (ArrayList<String>) session.getAttribute(SESSION_CART);
        if (cart == null) {
            cart = new ArrayList<>();
        }
        cart.add(req.getParameter(REQUEST_PRODUCT));
        session.setAttribute(SESSION_CART, cart);
        String searchString = (String) session.getAttribute(SESSION_SEARCH_STRING);
        Connection connection = (Connection) getServletContext().getAttribute(APP_DB_CONNECTION);
        List<Product> products = new Dao().searchProducts(connection, searchString);
        req.setAttribute(REQUEST_PRODUCTS, products);
        req.setAttribute(REQUEST_CART_SIZE, cart.size());
        req.getRequestDispatcher(SEARCH_JSP).forward(req, resp);
    }
}
