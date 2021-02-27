package com.epam.hplus.controller.commands;

import com.epam.hplus.beans.Product;
import com.epam.hplus.resources.ConfigurationManger;
import com.epam.hplus.service.ProductService;
import com.epam.hplus.service.SearchService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static com.epam.hplus.constants.Context.REQUEST_PRODUCT;
import static com.epam.hplus.constants.Context.REQUEST_PRODUCTS;
import static com.epam.hplus.constants.Context.SESSION_CART;
import static com.epam.hplus.constants.Context.SESSION_SEARCH_STRING;

public class AddProductCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        addProductToCart(req, session);
        addResultOfSearchToRequest(req, session);
        return ConfigurationManger.getProperty("page.search");
    }

    private void addProductToCart(HttpServletRequest req, HttpSession session) {
        List<Product> cart = getCart(session);
        int productId = Integer.parseInt(req.getParameter(REQUEST_PRODUCT));
        Product product = ProductService.getProduct(productId);
        cart.add(product);
        session.setAttribute(SESSION_CART, cart);
    }

    private List<Product> getCart(HttpSession session) {
        List<Product> cart = (ArrayList<Product>) session.getAttribute(SESSION_CART);
        if (cart == null) {
            cart = new ArrayList<>();
        }
        return cart;
    }

    private void addResultOfSearchToRequest(HttpServletRequest req, HttpSession session) {
        String searchString = (String) session.getAttribute(SESSION_SEARCH_STRING);
        List<Product> products = SearchService.search(searchString);
        req.setAttribute(REQUEST_PRODUCTS, products);
    }
}
