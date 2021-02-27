package com.epam.hplus.service;

import com.epam.hplus.dao.Dao;
import com.epam.hplus.dao.DbConnector;

import java.sql.Connection;

public class Login {
    private Login() {
    }

    public static boolean isValidUser(String username, String password) {
        Connection connection = DbConnector.getConnection();
        return new Dao().validateUser(connection, username, password);
    }
}
