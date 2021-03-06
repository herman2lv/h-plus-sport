package com.epam.hplus.controller.commands.impl.authorized;

import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.model.beans.User;
import com.epam.hplus.model.service.UserService;
import com.epam.hplus.util.resources.ConfigurationManger;
import jakarta.servlet.http.HttpServletRequest;

import static com.epam.hplus.util.constants.Context.REQUEST_USER;
import static com.epam.hplus.util.constants.Context.SESSION_USERNAME;

public class ProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute(SESSION_USERNAME);
        User user = UserService.getUser(username);
        req.setAttribute(REQUEST_USER, user);
        return ConfigurationManger.getProperty("page.profile");
    }
}
