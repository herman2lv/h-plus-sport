package com.epam.hplus.service;

import com.epam.hplus.beans.User;
import com.epam.hplus.dao.DbConnector;
import com.epam.hplus.dao.UserDao;
import com.epam.hplus.dao.UserDaoJdbc;

import java.sql.Connection;

public class ProfileService {
    private ProfileService() {
    }

    public static User getUserProfile(String username) {
        Connection connection = DbConnector.getConnection();
        UserDao userDao = new UserDaoJdbc();
        return userDao.getUserProfile(connection, username);
    }
}
