package com.epam.hplus.controller.commands;

import com.epam.hplus.beans.User;
import com.epam.hplus.resources.ConfigurationManger;
import com.epam.hplus.resources.MessageManager;
import com.epam.hplus.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.hplus.constants.Context.REQUEST_REGISTRATION_STATUS;

public class RegisterUserCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterUserCommand.class);


    @Override
    public String execute(HttpServletRequest req) {
        User user = UserService.createInstanceOfUser(req);
        StringBuilder validationStatus = new StringBuilder();
        String registrationStatus;
        if (UserService.isValidUserData(user, validationStatus)) {
            if (UserService.registerNewUser(user)) {
                registrationStatus = MessageManager.getMessage("msg.registeredSuccess");
                LOGGER.info(MessageManager.getMessage("log.registeredSuccess"));
            } else {
                registrationStatus = MessageManager.getMessage("msg.notRegistered");
                LOGGER.info(MessageManager.getMessage("log.notRegistered"));
            }
        } else {
            registrationStatus = validationStatus.toString();
        }
        req.setAttribute(REQUEST_REGISTRATION_STATUS, registrationStatus);
        return ConfigurationManger.getProperty("page.registerRedirect");
    }
}
