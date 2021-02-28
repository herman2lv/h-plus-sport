package com.epam.hplus.dao;

import com.epam.hplus.beans.User;

import java.sql.Connection;
import java.util.List;

public interface UserDao {
        int createUser(Connection connection, User user);
        User getUser(Connection connection, String username);
        boolean updateUser(Connection connection, User user);
        boolean deleteUser(Connection connection, String username);
        List<User> getUsers(Connection connection);
        boolean validateUserCredentials(Connection connection, String username, String password);
        boolean isUsernameFree(Connection connection, String username);
}
