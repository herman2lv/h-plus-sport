package com.epam.hplus.controller.commands;

import com.epam.hplus.beans.Order;
import com.epam.hplus.resources.ConfigurationManger;
import com.epam.hplus.service.OrdersService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static com.epam.hplus.constants.Context.REQUEST_ORDERS;
import static com.epam.hplus.constants.Context.SESSION_USERNAME;

public class OrdersCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute(SESSION_USERNAME);
        List<Order> orders = OrdersService.getOrdersOfUser(username);
        req.setAttribute(REQUEST_ORDERS, orders);
        return ConfigurationManger.getProperty("page.history");
    }
}
