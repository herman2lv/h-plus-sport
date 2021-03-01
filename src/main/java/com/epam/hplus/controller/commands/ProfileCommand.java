package com.epam.hplus.controller.commands;

import com.epam.hplus.model.beans.User;
import com.epam.hplus.resources.ConfigurationManger;
import com.epam.hplus.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import static com.epam.hplus.constants.Context.REQUEST_USER;
import static com.epam.hplus.constants.Context.SESSION_USERNAME;

public class ProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute(SESSION_USERNAME);
        User user = UserService.getUser(username);
        req.setAttribute(REQUEST_USER, user);
        return ConfigurationManger.getProperty("page.profile");
    }
}
