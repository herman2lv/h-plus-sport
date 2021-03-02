package com.epam.hplus.controller.commands.impl.authorized.manager;

import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.controller.commands.util.Paginator;
import com.epam.hplus.controller.commands.util.RequestProcessor;
import com.epam.hplus.util.resources.ConfigurationManger;
import com.epam.hplus.model.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;

import static com.epam.hplus.util.constants.Context.REQUEST_ORDER;

public class RejectOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        int orderId = RequestProcessor.getIntFromRequest(req, REQUEST_ORDER);
        OrderService.rejectOrder(orderId);
        Paginator.transferPageToSession(req);
        return ConfigurationManger.getProperty("page.orderManagementRedirect");
    }
}
