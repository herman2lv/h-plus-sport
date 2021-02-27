package com.epam.hplus.service;

import com.epam.hplus.beans.User;
import com.epam.hplus.dao.DbConnector;
import com.epam.hplus.dao.UserDao;
import com.epam.hplus.dao.UserDaoJdbc;

import java.sql.Connection;

public class UserService {
    private UserService() {
    }

    public static boolean registerNewUser(User user) {
        Connection connection = DbConnector.getConnection();
        UserDao userDao = new UserDaoJdbc();
        int rowsAffected = userDao.createUser(connection, user);
        return rowsAffected == 1;
    }
}
