package com.epam.hplus.controller.commands;

import com.epam.hplus.resources.ConfigurationManger;
import com.epam.hplus.resources.MessageManager;
import com.epam.hplus.service.Login;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.hplus.constants.Context.LOGIN_REQUEST_PASSWORD;
import static com.epam.hplus.constants.Context.LOGIN_REQUEST_USERNAME;
import static com.epam.hplus.constants.Context.REQUEST_ERROR;
import static com.epam.hplus.constants.Context.SESSION_USERNAME;

public class LoginCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest req) {
        String username = req.getParameter(LOGIN_REQUEST_USERNAME);
        String password = req.getParameter(LOGIN_REQUEST_PASSWORD);
        if (Login.isValidUser(username, password)) {
            req.getSession().setAttribute(SESSION_USERNAME, username);
            return ConfigurationManger.getProperty("page.index");
        } else {
            if (username != null) {
                LOGGER.info(MessageManager.getMessage("log.invalidCredentials"));
                req.setAttribute(REQUEST_ERROR,
                        MessageManager.getMessage("msg.invalidCredentials"));
            }
            return ConfigurationManger.getProperty("page.login");
        }
    }
}
