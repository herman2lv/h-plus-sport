package com.epam.hplus.dao;

import com.epam.hplus.beans.User;

import java.sql.Connection;

public interface UserDao {
        int createUser(Connection connection, User user);
        User getUserProfile(Connection connection, String username);
        boolean validateUser(Connection connection, String username, String password);
}
