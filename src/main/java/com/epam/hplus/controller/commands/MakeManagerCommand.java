package com.epam.hplus.controller.commands;

import com.epam.hplus.beans.User;
import com.epam.hplus.resources.ConfigurationManger;
import com.epam.hplus.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import static com.epam.hplus.constants.Context.REQUEST_USER;

public class MakeManagerCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String username = req.getParameter(REQUEST_USER);
        User user = UserService.getUserProfile(username);
        UserService.setUserManagerRole(user);
        return ConfigurationManger.getProperty("page.userManagementRedirect");
    }
}
