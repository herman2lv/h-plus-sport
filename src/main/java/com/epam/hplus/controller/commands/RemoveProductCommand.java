package com.epam.hplus.controller.commands;

import com.epam.hplus.beans.Product;
import com.epam.hplus.controller.commands.util.RequestProcessor;
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
import static com.epam.hplus.constants.Context.SESSION_CART;

public class RemoveProductCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(RemoveProductCommand.class);

    @Override
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
