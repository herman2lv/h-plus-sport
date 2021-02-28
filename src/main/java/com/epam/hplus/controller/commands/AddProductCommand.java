package com.epam.hplus.controller.commands;

import com.epam.hplus.beans.Product;
import com.epam.hplus.resources.ConfigurationManger;
import com.epam.hplus.service.CartService;
import com.epam.hplus.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.epam.hplus.constants.Context.REQUEST_PRODUCT;
import static com.epam.hplus.constants.Context.REQUEST_PRODUCTS;
import static com.epam.hplus.constants.Context.SESSION_CART;
import static com.epam.hplus.constants.Context.SESSION_SEARCH_STRING;

public class AddProductCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddProductCommand.class);
    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        addProductToCart(req, session);
        addResultOfSearchToRequest(req, session);
        return ConfigurationManger.getProperty("page.searchRedirect");
    }

    private void addProductToCart(HttpServletRequest req, HttpSession session) {
        List<Product> cart = getCart(session);
        int productId = 0;
        try {
            productId = Integer.parseInt(req.getParameter(REQUEST_PRODUCT));
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e);
        }
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
