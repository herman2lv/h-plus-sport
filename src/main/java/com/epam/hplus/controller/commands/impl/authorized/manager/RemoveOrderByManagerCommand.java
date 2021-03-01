package com.epam.hplus.controller.commands.impl.authorized.manager;

import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.controller.commands.util.RequestProcessor;
import com.epam.hplus.util.resources.ConfigurationManger;
import com.epam.hplus.model.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.hplus.util.constants.Context.REQUEST_ORDER;

public class RemoveOrderByManagerCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(RemoveOrderByManagerCommand.class);
    @Override
    public String execute(HttpServletRequest req) {
        int orderId = RequestProcessor.getIntFromRequest(req, REQUEST_ORDER);
        OrderService.removeOrder(orderId);
        return ConfigurationManger.getProperty("page.orderManagementRedirect");
    }
}