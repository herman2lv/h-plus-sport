package com.epam.hplus.controller.commands.impl;

import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.controller.commands.util.PasswordEncryptor;
import com.epam.hplus.model.beans.User;
import com.epam.hplus.model.service.LoginService;
import com.epam.hplus.model.service.UserService;
import com.epam.hplus.util.resources.ConfigurationManger;
import com.epam.hplus.util.resources.LogManager;
import com.epam.hplus.util.resources.MessageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.hplus.util.constants.Context.LOGIN_REQUEST_PASSWORD;
import static com.epam.hplus.util.constants.Context.LOGIN_REQUEST_USERNAME;
import static com.epam.hplus.util.constants.Context.REQUEST_ERROR;
import static com.epam.hplus.util.constants.Context.SESSION_USERNAME;
import static com.epam.hplus.util.constants.Context.SESSION_USER_ROLE;

public class LoginCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest req) {
        String username = req.getParameter(LOGIN_REQUEST_USERNAME);
        String password = req.getParameter(LOGIN_REQUEST_PASSWORD);
        String encryptedPassword = PasswordEncryptor.encryptPassword(password);
        if (LoginService.isValidUser(username, encryptedPassword)) {
            HttpSession session = req.getSession();
            User user = UserService.getUser(username);
            session.setAttribute(SESSION_USERNAME, user.getUsername());
            session.setAttribute(SESSION_USER_ROLE, user.getRole());
            return ConfigurationManger.getProperty("page.index");
        } else {
            if (username != null) {
                LOGGER.info(LogManager.getMessage("log.invalidCredentials"));
                req.setAttribute(REQUEST_ERROR,
                        MessageManager.getMessage("msg.invalidCredentials"));
            }
            return ConfigurationManger.getProperty("page.login");
        }
    }
}
