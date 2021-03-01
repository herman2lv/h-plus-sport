package com.epam.hplus.model.service;

import com.epam.hplus.model.beans.User;
import com.epam.hplus.model.dao.Impl.UserDaoJdbc;
import com.epam.hplus.model.dao.UserDao;

import java.util.List;

public class UserService {
    private static final UserDao USER_DAO = UserDaoJdbc.getInstance();


    private UserService() {
    }

    public static boolean deleteUser(String username) {
        return USER_DAO.deleteUser(username);
    }

    public static boolean setUserCustomerRole(User user) {
        return changeUserRole(user, User.ROLE_CUSTOMER);
    }

    public static boolean setUserManagerRole(User user) {
        return changeUserRole(user, User.ROLE_MANAGER);
    }

    private static boolean changeUserRole(User user, int userRole) {
        user.setRole(userRole);
        return USER_DAO.updateUser(user);
    }

    public static List<User> getUsers() {
        return USER_DAO.getUsers();
    }

    public static boolean registerNewUser(User user) {
        int rowsAffected = USER_DAO.createUser(user);
        return rowsAffected == 1;
    }

    public static User getUser(String username) {
        return USER_DAO.getUser(username);
    }

    public static boolean isUsernameFree(String username) {
        return USER_DAO.getUser(username) == null;
    }

    public static boolean isUsernameNotFree(String username) {
        return !isUsernameFree(username);
    }
}
