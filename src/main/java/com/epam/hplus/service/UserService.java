package com.epam.hplus.service;

import com.epam.hplus.beans.User;
import com.epam.hplus.dao.DbConnector;
import com.epam.hplus.dao.UserDao;
import com.epam.hplus.dao.UserDaoJdbc;
import com.epam.hplus.resources.MessageManager;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.epam.hplus.constants.Context.REQUEST_ACTIVITY;
import static com.epam.hplus.constants.Context.REQUEST_DOB;
import static com.epam.hplus.constants.Context.REQUEST_FIRST_NAME;
import static com.epam.hplus.constants.Context.REQUEST_LAST_NAME;
import static com.epam.hplus.constants.Context.REQUEST_PASSWORD;
import static com.epam.hplus.constants.Context.REQUEST_USERNAME;

public class UserService {
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String USERNAME_REGEX = "[a-zA-Z0-9_\\-]{4,30}";
    private static final String PASSWORD_REGEX = "[a-zA-Z0-9_\\-\\p{Punct}]{8,30}";
    private static final String PERSONAL_NAME = "[a-zA-Zа-яА-Я-]{4,50}";
    private static final Date MAX_DATE = new Date(1262296800000L); //2010-01-01
    private static final Date MIN_DATE = new Date(-1577929800000L); //1920-01-01

    private UserService() {
    }

    public static boolean registerNewUser(User user) {
        Connection connection = DbConnector.getConnection();
        UserDao userDao = new UserDaoJdbc();
        int rowsAffected = userDao.createUser(connection, user);
        return rowsAffected == 1;
    }

    public static User getUserProfile(String username) {
        Connection connection = DbConnector.getConnection();
        UserDao userDao = new UserDaoJdbc();
        return userDao.getUserProfile(connection, username);
    }

    public static User createInstanceOfUser(HttpServletRequest req) {
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

    public static boolean isValidUserData(User user, StringBuilder status) {
        if (isNotValidUsername(user.getUsername())) {
            status.append(MessageManager.getMessage("msg.notValidUsername"));
            return false;
        }
        if (isNotValidPassword(user.getPassword())) {
            status.append(MessageManager.getMessage("msg.notValidPassword"));
            return false;
        }
        if (isNotValidName(user.getFirstName()) || isNotValidName(user.getLastName())) {
            status.append(MessageManager.getMessage("msg.notValidName"));
            return false;
        }
        if (isNotValidDate(user.getDateOfBirth())) {
            status.append(MessageManager.getMessage("msg.notValidDate"));
            return false;
        }
        if (isUsernameNotFree(user.getUsername())) {
            status.append(MessageManager.getMessage("msg.nameNotFree"));
            return false;
        }
        return true;
    }

    private static boolean isValidUsername(String username) {
        return username.matches(USERNAME_REGEX);
    }

    private static boolean isNotValidUsername(String username) {
        return !isValidUsername(username);
    }

    private static boolean isUsernameFree(String username) {
        Connection connection = DbConnector.getConnection();
        UserDao userDao = new UserDaoJdbc();
        return userDao.isUsernameFree(connection, username);
    }

    private static boolean isUsernameNotFree(String username) {
        return !isUsernameFree(username);
    }

        private static boolean isValidPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    private static boolean isNotValidPassword(String password) {
        return !isValidPassword(password);
    }

    private static boolean isValidName(String personalName) {
        return personalName.matches(PERSONAL_NAME);
    }

    private static boolean isNotValidName(String personalName) {
        return !isValidName(personalName);
    }

    private static boolean isValidDate(Date date) {
        return date.compareTo(MIN_DATE) > 0 && date.compareTo(MAX_DATE) < 0;
    }

    private static boolean isNotValidDate(Date date) {
        return !isValidDate(date);
    }
}
