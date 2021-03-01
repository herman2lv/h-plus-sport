package com.epam.hplus.controller.commands;

import com.epam.hplus.model.beans.Order;
import com.epam.hplus.util.resources.ConfigurationManger;
import com.epam.hplus.model.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static com.epam.hplus.util.constants.Context.REQUEST_ORDERS;
import static com.epam.hplus.util.constants.Context.SESSION_USERNAME;

public class OrdersCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute(SESSION_USERNAME);
        List<Order> orders = OrderService.getOrdersOfUser(username);
        req.setAttribute(REQUEST_ORDERS, orders);
        return ConfigurationManger.getProperty("page.history");
    }
}
