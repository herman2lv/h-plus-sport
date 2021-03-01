package com.epam.hplus.controller.commands;

import com.epam.hplus.model.beans.User;
import com.epam.hplus.resources.ConfigurationManger;
import com.epam.hplus.resources.MessageManager;
import com.epam.hplus.model.service.LoginService;
import com.epam.hplus.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.hplus.constants.Context.LOGIN_REQUEST_PASSWORD;
import static com.epam.hplus.constants.Context.LOGIN_REQUEST_USERNAME;
import static com.epam.hplus.constants.Context.REQUEST_ERROR;
import static com.epam.hplus.constants.Context.SESSION_USERNAME;
import static com.epam.hplus.constants.Context.SESSION_USER_ROLE;

public class LoginCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest req) {
        String username = req.getParameter(LOGIN_REQUEST_USERNAME);
        String password = req.getParameter(LOGIN_REQUEST_PASSWORD);
        if (LoginService.isValidUser(username, password)) {
            HttpSession session = req.getSession();
            User user = UserService.getUserProfile(username);
            session.setAttribute(SESSION_USERNAME, user.getUsername());
            session.setAttribute(SESSION_USER_ROLE, user.getRole());
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
