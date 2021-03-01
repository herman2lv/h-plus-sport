package com.epam.hplus.controller.commands;

import com.epam.hplus.model.beans.User;
import com.epam.hplus.model.service.PasswordEncryptor;
import com.epam.hplus.model.service.UserService;
import com.epam.hplus.model.validators.UserValidator;
import com.epam.hplus.resources.ConfigurationManger;
import com.epam.hplus.resources.MessageManager;
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
        String username = req.getParameter(REQUEST_USERNAME);
        String password = req.getParameter(REQUEST_PASSWORD);
        String firstName = req.getParameter(REQUEST_FIRST_NAME);
        String lastName = req.getParameter(REQUEST_LAST_NAME);
        Date date = extractDate(req);
        String activity = req.getParameter(REQUEST_ACTIVITY);
        int role = User.ROLE_CUSTOMER;
        StringBuilder validationStatus = new StringBuilder();
        String registrationStatus;
        if (isValidUserData(username, password, firstName, lastName, date, validationStatus)) {
            String encryptedPassword = PasswordEncryptor.encryptPassword(password);
            User user = new User(
                    username, encryptedPassword, firstName, lastName, activity, date, role);
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

    private static Date extractDate(HttpServletRequest req) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
            return format.parse(req.getParameter(REQUEST_DOB));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean isValidUserData(String username, String password, String firstName,
                                           String lastName, Date date, StringBuilder status) {
        UserValidator validator = new UserValidator();
        if (validator.isNotValidUsername(username)) {
            status.append(MessageManager.getMessage("msg.notValidUsername"));
            return false;
        }
        if (validator.isNotValidPassword(password)) {
            status.append(MessageManager.getMessage("msg.notValidPassword"));
            return false;
        }
        if (validator.isNotValidName(firstName)
                || validator.isNotValidName(lastName)) {
            status.append(MessageManager.getMessage("msg.notValidName"));
            return false;
        }
        if (validator.isNotValidDate(date)) {
            status.append(MessageManager.getMessage("msg.notValidDate"));
            return false;
        }
        if (UserService.isUsernameNotFree(username)) {
            status.append(MessageManager.getMessage("msg.nameNotFree"));
            return false;
        }
        return true;
    }
}
