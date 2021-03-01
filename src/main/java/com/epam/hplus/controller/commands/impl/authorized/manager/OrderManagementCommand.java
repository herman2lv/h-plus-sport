package com.epam.hplus.controller.commands.impl.authorized.manager;

import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.model.beans.Order;
import com.epam.hplus.util.resources.ConfigurationManger;
import com.epam.hplus.model.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static com.epam.hplus.util.constants.Context.REQUEST_ORDERS;

public class OrderManagementCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        List<Order> orders = OrderService.getOrders();
        req.setAttribute(REQUEST_ORDERS, orders);
        return ConfigurationManger.getProperty("page.orderManagement");
    }
}