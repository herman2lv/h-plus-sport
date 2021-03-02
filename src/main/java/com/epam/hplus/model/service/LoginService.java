package com.epam.hplus.model.service;

import com.epam.hplus.model.dao.Impl.UserDaoJdbc;
import com.epam.hplus.model.dao.UserDao;

/**
 * A service class of model layer, intended to provide the information
 * needed to user's authorization process.
 */
public class LoginService {
    /**
     * DAO object to get stored information about registered {@code User}
     * and to save info to the storage.
     */
    private static final UserDao USER_DAO = UserDaoJdbc.getInstance();

    /**
     * Private constructor to prevent instantiating of class object.
     */
    private LoginService() {
    }

    /**
     * Validate users credentials. User considered valid if DAO object
     * contains information that there is active user account with such
     * username and password
     * @param username {@code String} represents login (username) of the
     *                               user's account
     * @param password {@code String} represents password of the user's
     *                               account. Should be in form that it
     *                               was provided to the DAO object
     *                               (encrypted if it stored encrypted)
     * @return {@code true} if user is valid
     */
    public static boolean isValidUser(String username, String password) {
        return USER_DAO.validateUserCredentials(username, password);
    }
}
