package com.epam.hplus.controller.commands.impl;

import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.controller.commands.util.RequestProcessor;
import com.epam.hplus.model.beans.Product;
import com.epam.hplus.model.service.CartService;
import com.epam.hplus.model.service.ProductService;
import com.epam.hplus.util.resources.ConfigurationManger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static com.epam.hplus.util.constants.Context.REQUEST_PRODUCT;
import static com.epam.hplus.util.constants.Context.REQUEST_PRODUCTS;
import static com.epam.hplus.util.constants.Context.SESSION_CART;
import static com.epam.hplus.util.constants.Context.SESSION_SEARCH_STRING;

public class AddProductCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        addProductToCart(req, session);
        addResultOfSearchToRequest(req, session);
        return ConfigurationManger.getProperty("page.searchRedirect");
    }

    private void addProductToCart(HttpServletRequest req, HttpSession session) {
        List<Product> cart = getCart(session);
        int productId = RequestProcessor.getIntFromRequest(req, REQUEST_PRODUCT);
        Product product = ProductService.getProduct(productId);
        CartService.addProduct(cart, product);
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
        List<Product> products = ProductService.searchProducts(searchString);
        req.setAttribute(REQUEST_PRODUCTS, products);
    }
}
