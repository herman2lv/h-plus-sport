package com.epam.hplus.controller.commands;

import com.epam.hplus.beans.Product;
import com.epam.hplus.resources.ConfigurationManger;
import com.epam.hplus.service.CartService;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.epam.hplus.constants.Context.REQUEST_CART_TOTAL_COST;
import static com.epam.hplus.constants.Context.REQUEST_GROUPED_PRODUCTS;
import static com.epam.hplus.constants.Context.SESSION_CART;

public class CartCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        List<Product> products = (ArrayList<Product>) req.getSession().getAttribute(SESSION_CART);
        if (products != null) {
            Map<Product, Long> productsGrouped = CartService.groupProducts(products);
            req.setAttribute(REQUEST_GROUPED_PRODUCTS, new ArrayList<>(productsGrouped.entrySet()));
            BigDecimal cost = CartService.countTotalCost(products);
            req.setAttribute(REQUEST_CART_TOTAL_COST, cost.toString());
        }
        return ConfigurationManger.getProperty("page.cart");
    }
}
