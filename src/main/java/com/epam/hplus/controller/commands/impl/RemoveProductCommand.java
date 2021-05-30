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
import static com.epam.hplus.util.constants.Context.SESSION_CART;

public class RemoveProductCommand implements Command {
    @Override
    @SuppressWarnings("unchecked")
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        int productId = RequestProcessor.getIntFromRequest(req, REQUEST_PRODUCT);
        Product product = ProductService.getProduct(productId);
        List<Product> cart = (ArrayList<Product>) session.getAttribute(SESSION_CART);
        CartService.removeProduct(cart, product);
        session.setAttribute(SESSION_CART, cart);
        return ConfigurationManger.getProperty("page.cartRedirect");
    }
}
