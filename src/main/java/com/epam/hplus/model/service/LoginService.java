package com.epam.hplus.model.service;

import com.epam.hplus.model.dao.Impl.UserDaoJdbc;
import com.epam.hplus.model.dao.UserDao;

public class LoginService {
    private static final UserDao USER_DAO = UserDaoJdbc.getInstance();
    private LoginService() {
    }

    public static boolean isValidUser(String username, String password) {
        return USER_DAO.validateUserCredentials(username, password);
    }
}
