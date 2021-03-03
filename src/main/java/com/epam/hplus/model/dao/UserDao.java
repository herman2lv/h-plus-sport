package com.epam.hplus.model.dao;

import com.epam.hplus.model.beans.User;

import java.util.List;

public interface UserDao {
    int createUser(User user);

    User getUser(String username);

    boolean updateUser(User user);

    List<User> getUsers(int currentIndex, int itemsOnPage);

    int countUsers();

    boolean validateUserCredentials(String username, String password);
}
