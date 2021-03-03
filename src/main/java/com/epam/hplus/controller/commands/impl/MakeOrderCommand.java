package com.epam.hplus.controller.commands.impl;

import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.model.beans.Order;
import com.epam.hplus.model.beans.Product;
import com.epam.hplus.model.service.CartService;
import com.epam.hplus.model.service.OrderService;
import com.epam.hplus.util.resources.ConfigurationManger;
import com.epam.hplus.util.resources.MessageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.epam.hplus.util.constants.Context.REQUEST_ORDER_STATUS;
import static com.epam.hplus.util.constants.Context.SESSION_CART;
import static com.epam.hplus.util.constants.Context.SESSION_USERNAME;

public class MakeOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Order order = createInstanceOfOrder(session);
        String orderStatus;
        if (OrderService.createOrder(order)) {
            cleanCart(session);
            return ConfigurationManger.getProperty("page.historyRedirect");
        } else {
            orderStatus = MessageManager.getMessage("msg.orderNotCreated");
            req.setAttribute(REQUEST_ORDER_STATUS, orderStatus);
            return ConfigurationManger.getProperty("page.cartRedirect");
        }
    }

    private Order createInstanceOfOrder(HttpSession session) {
        List<Product> cart = (List<Product>) session.getAttribute(SESSION_CART);
        String username = (String) session.getAttribute(SESSION_USERNAME);
        Date orderDate = new Date();
        Map<Product, Long> listOfProducts = CartService.groupProducts(cart);
        BigDecimal orderCost = CartService.countTotalCost(cart);
        return new Order(username, orderDate, listOfProducts, orderCost, false);
    }

    private void cleanCart(HttpSession session) {
        session.setAttribute(SESSION_CART, new ArrayList<Product>());
    }
}
