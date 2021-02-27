package com.epam.hplus.servlets;

import com.epam.hplus.beans.Product;
import com.epam.hplus.resources.ConfigurationManger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.epam.hplus.constants.Context.REQUEST_CART_TOTAL_COST;
import static com.epam.hplus.constants.Context.REQUEST_GROUPED_PRODUCTS;
import static com.epam.hplus.constants.Context.SESSION_CART;
import static com.epam.hplus.constants.ServletsUrlPatterns.CART_SERVLET;

@WebServlet(urlPatterns = CART_SERVLET)
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> productsList =
                (ArrayList<Product>) req.getSession().getAttribute(SESSION_CART);
        if (productsList != null) {
            BigDecimal cost = countTotalCost(productsList);
            Map<Product, Long> productsGrouped = groupProducts(productsList);
            req.setAttribute(REQUEST_GROUPED_PRODUCTS, new ArrayList<>(productsGrouped.entrySet()));
            req.setAttribute(REQUEST_CART_TOTAL_COST, cost.toString());
        }
        req.getRequestDispatcher(ConfigurationManger.getProperty("cart.search")).forward(req, resp);
    }

    private Map<Product, Long> groupProducts(List<Product> productsList) {
        return productsList.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
    }

    private BigDecimal countTotalCost(List<Product> productsList) {
        return productsList.stream()
                .map(Product::getCost)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
