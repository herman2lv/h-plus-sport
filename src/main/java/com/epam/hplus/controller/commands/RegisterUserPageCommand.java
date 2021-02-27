package com.epam.hplus.controller.commands;

import com.epam.hplus.resources.ConfigurationManger;
import jakarta.servlet.http.HttpServletRequest;

import static com.epam.hplus.constants.Context.REQUEST_REGISTRATION_STATUS;
import static com.epam.hplus.constants.Context.SESSION_REGISTRATION_STATUS;

public class RegisterUserPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        refreshRegistrationStatus(req);
        return ConfigurationManger.getProperty("page.register");
    }

    private void refreshRegistrationStatus(HttpServletRequest req) {
        String registerStatus = req.getParameter(REQUEST_REGISTRATION_STATUS);
        if (registerStatus == null) {
            registerStatus = "";
        }
        req.getSession().setAttribute(SESSION_REGISTRATION_STATUS, registerStatus);
    }
}
