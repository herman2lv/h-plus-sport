package com.epam.hplus.controller.commands.impl.authorized.admin;

import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.model.beans.User;
import com.epam.hplus.util.resources.ConfigurationManger;
import com.epam.hplus.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import static com.epam.hplus.util.constants.Context.REQUEST_USER;

public class MakeCustomerCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String username = req.getParameter(REQUEST_USER);
        User user = UserService.getUser(username);
        UserService.setUserCustomerRole(user);
        return ConfigurationManger.getProperty("page.userManagementRedirect");
    }
}
