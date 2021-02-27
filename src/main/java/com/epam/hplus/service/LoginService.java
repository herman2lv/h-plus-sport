package com.epam.hplus.service;

import com.epam.hplus.dao.DbConnector;
import com.epam.hplus.dao.UserDao;
import com.epam.hplus.dao.UserDaoJdbc;

import java.sql.Connection;

public class LoginService {
    private LoginService() {
    }

    public static boolean isValidUser(String username, String password) {
        Connection connection = DbConnector.getConnection();
        UserDao userDao = new UserDaoJdbc();
        return userDao.validateUser(connection, username, password);
    }
}
