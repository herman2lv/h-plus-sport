package com.epam.hplus.service;

import com.epam.hplus.beans.User;
import com.epam.hplus.dao.Dao;
import com.epam.hplus.dao.DbConnector;

import java.sql.Connection;

public class UserService {
    private UserService() {
    }

    public static boolean registerNewUser(User user) {
        Connection connection = DbConnector.getConnection();
        int rowsAffected = new Dao().createUser(connection, user);
        return rowsAffected == 1;
    }
}
