package com.epam.hplus.controller.commands;

import com.epam.hplus.resources.ConfigurationManger;
import com.epam.hplus.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import static com.epam.hplus.constants.Context.REQUEST_USER;

public class DeleteUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String username = req.getParameter(REQUEST_USER);
        UserService.deleteUser(username);
        return ConfigurationManger.getProperty("page.userManagementRedirect");
    }
}
