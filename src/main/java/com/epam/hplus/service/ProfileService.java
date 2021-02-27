package com.epam.hplus.service;

import com.epam.hplus.beans.User;
import com.epam.hplus.dao.Dao;
import com.epam.hplus.dao.DbConnector;

import java.sql.Connection;

public class ProfileService {
    private ProfileService() {
    }

    public static User getUserProfile(String username) {
        Connection connection = DbConnector.getConnection();
        return new Dao().getUserProfile(connection, username);
    }
}
