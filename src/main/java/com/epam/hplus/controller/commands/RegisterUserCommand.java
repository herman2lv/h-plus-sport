package com.epam.hplus.controller.commands;

import com.epam.hplus.beans.User;
import com.epam.hplus.resources.ConfigurationManger;
import com.epam.hplus.resources.MessageManager;
import com.epam.hplus.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.epam.hplus.constants.Context.REQUEST_ACTIVITY;
import static com.epam.hplus.constants.Context.REQUEST_DOB;
import static com.epam.hplus.constants.Context.REQUEST_FIRST_NAME;
import static com.epam.hplus.constants.Context.REQUEST_LAST_NAME;
import static com.epam.hplus.constants.Context.REQUEST_PASSWORD;
import static com.epam.hplus.constants.Context.REQUEST_REGISTRATION_STATUS;
import static com.epam.hplus.constants.Context.REQUEST_USERNAME;

public class RegisterUserCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterUserCommand.class);
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    @Override
    public String execute(HttpServletRequest req) {
        User user = createInstanceOfUser(req);
        String registrationStatus;
        if (UserService.registerNewUser(user)) {
            registrationStatus = MessageManager.getMessage("msg.registeredSuccess");
            LOGGER.info(MessageManager.getMessage("log.registeredSuccess"));
        } else {
            registrationStatus = MessageManager.getMessage("msg.notRegistered");
            LOGGER.info(MessageManager.getMessage("log.notRegistered"));
        }
        req.setAttribute(REQUEST_REGISTRATION_STATUS, registrationStatus);
        return ConfigurationManger.getProperty("page.register");
    }

    private User createInstanceOfUser(HttpServletRequest req) {
        String username = req.getParameter(REQUEST_USERNAME);
        String password = req.getParameter(REQUEST_PASSWORD);
        String firstName = req.getParameter(REQUEST_FIRST_NAME);
        String lastName = req.getParameter(REQUEST_LAST_NAME);
        String activity = req.getParameter(REQUEST_ACTIVITY);
        Date date = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
            date = format.parse(req.getParameter(REQUEST_DOB));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new User(username, password, firstName, lastName, activity, date);
    }
}
