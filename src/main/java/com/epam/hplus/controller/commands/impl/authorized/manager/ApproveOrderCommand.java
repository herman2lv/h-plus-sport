package com.epam.hplus.controller.commands.impl.authorized.manager;

import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.controller.commands.util.RequestProcessor;
import com.epam.hplus.util.resources.ConfigurationManger;
import com.epam.hplus.model.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;

import static com.epam.hplus.util.constants.Context.REQUEST_ORDER;

public class ApproveOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        int orderId = RequestProcessor.getIntFromRequest(req, REQUEST_ORDER);
        OrderService.approveOrder(orderId);
        return ConfigurationManger.getProperty("page.orderManagementRedirect");
    }
}