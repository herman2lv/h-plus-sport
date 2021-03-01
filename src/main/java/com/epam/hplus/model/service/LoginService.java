package com.epam.hplus.model.service;

import com.epam.hplus.model.dao.Impl.UserDaoJdbc;
import com.epam.hplus.model.dao.UserDao;

public class LoginService {
    private LoginService() {
    }

    public static boolean isValidUser(String username, String password) {
        UserDao userDao = new UserDaoJdbc();
        return userDao.validateUserCredentials(username, password);
    }
}
