package com.epam.hplus.model.dao.impl;

import com.epam.hplus.model.beans.User;
import com.epam.hplus.model.dao.UserDao;
import com.epam.hplus.model.pool.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.epam.hplus.util.constants.Database.USERS_ACTIVE;
import static com.epam.hplus.util.constants.Database.USERS_ACTIVE_INDEX;
import static com.epam.hplus.util.constants.Database.USERS_ACTIVITY;
import static com.epam.hplus.util.constants.Database.USERS_ACTIVITY_INDEX;
import static com.epam.hplus.util.constants.Database.USERS_DOB;
import static com.epam.hplus.util.constants.Database.USERS_DOB_INDEX;
import static com.epam.hplus.util.constants.Database.USERS_FIRST_NAME;
import static com.epam.hplus.util.constants.Database.USERS_FIRST_NAME_INDEX;
import static com.epam.hplus.util.constants.Database.USERS_LAST_NAME;
import static com.epam.hplus.util.constants.Database.USERS_LAST_NAME_INDEX;
import static com.epam.hplus.util.constants.Database.USERS_PASSWORD;
import static com.epam.hplus.util.constants.Database.USERS_PASSWORD_INDEX;
import static com.epam.hplus.util.constants.Database.USERS_ROLE;
import static com.epam.hplus.util.constants.Database.USERS_ROLE_INDEX;
import static com.epam.hplus.util.constants.Database.USERS_USERNAME;
import static com.epam.hplus.util.constants.Database.USERS_USERNAME_INDEX;

public class UserDaoJdbc implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoJdbc.class);
    private static final UserDaoJdbc INSTANCE = new UserDaoJdbc();
    private static final String INSERT_USER = "INSERT into users values (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final int UPDATE_USER_PASSWORD_COLUMN = 1;
    private static final int UPDATE_USER_FIRST_NAME_COLUMN = 2;
    private static final int UPDATE_USER_LAST_NAME_COLUMN = 3;
    private static final int UPDATE_USER_DOB_COLUMN = 4;
    private static final int UPDATE_USER_ACTIVITY_COLUMN = 5;
    private static final int UPDATE_USER_ROLE_COLUMN = 6;
    private static final int UPDATE_USER_ACTIVE_COLUMN = 7;
    private static final int UPDATE_USER_USERNAME_COLUMN = 8;
    private static final String SELECT_USER_BY_USERNAME =
            "SELECT * FROM users WHERE username = ? AND active = TRUE";
    private static final String SELECT_USER_BY_CREDENTIALS =
            "SELECT * FROM users WHERE username = ? AND password = ? AND active = TRUE";
    private static final String SELECT_ALL_USERS =
            "SELECT * FROM users WHERE active = TRUE LIMIT ?, ?";
    private static final int LIMIT_CURRENT_INDEX = 1;
    private static final int LIMIT_ON_PAGE_INDEX = 2;
    private static final String COUNT_USERS = "SELECT COUNT(*) FROM users WHERE active = TRUE";
    protected static final int INVALID_COUNT = -1;
    private static final String UPDATE_USER = "UPDATE users SET password = ?, "
            + "first_name = ?, last_name = ?, date_of_birth = ?, activity = ?, "
            + "user_role = ?, active = ? WHERE username = ?";

    private UserDaoJdbc() {
    }

    public static UserDaoJdbc getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean createUser(User user) {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
            statement.setString(USERS_USERNAME_INDEX, user.getUsername());
            statement.setString(USERS_PASSWORD_INDEX, user.getPassword());
            statement.setString(USERS_FIRST_NAME_INDEX, user.getFirstName());
            statement.setString(USERS_LAST_NAME_INDEX, user.getLastName());
            statement.setDate(USERS_DOB_INDEX, new java.sql.Date(user.getDateOfBirth().getTime()));
            statement.setString(USERS_ACTIVITY_INDEX, user.getActivity());
            statement.setInt(USERS_ROLE_INDEX, user.getRole());
            statement.setBoolean(USERS_ACTIVE_INDEX, user.isActive());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public User getUser(String username) {
        User user = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_USERNAME)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = createInstanceOfUser(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public boolean validateUserCredentials(String username, String password) {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SELECT_USER_BY_CREDENTIALS)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet set = statement.executeQuery()) {
                if (set.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    private User createInstanceOfUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUsername(resultSet.getString(USERS_USERNAME));
        user.setPassword(resultSet.getString(USERS_PASSWORD));
        user.setFirstName(resultSet.getString(USERS_FIRST_NAME));
        user.setLastName(resultSet.getString(USERS_LAST_NAME));
        user.setActivity(resultSet.getString(USERS_ACTIVITY));
        user.setDateOfBirth(resultSet.getDate(USERS_DOB));
        user.setRole(resultSet.getInt(USERS_ROLE));
        user.setActive(resultSet.getBoolean(USERS_ACTIVE));
        return user;
    }

    @Override
    public List<User> getUsers(int currentIndex, int itemsOnPage) {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS)) {
            statement.setInt(LIMIT_CURRENT_INDEX, currentIndex);
            statement.setInt(LIMIT_ON_PAGE_INDEX, itemsOnPage);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(createInstanceOfUser(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return users;
    }

    @Override
    public int countUsers() {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(COUNT_USERS)) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return INVALID_COUNT;
    }

    @Override
    public boolean updateUser(User user) {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setString(UPDATE_USER_PASSWORD_COLUMN, user.getPassword());
            statement.setString(UPDATE_USER_FIRST_NAME_COLUMN, user.getFirstName());
            statement.setString(UPDATE_USER_LAST_NAME_COLUMN, user.getLastName());
            statement.setDate(UPDATE_USER_DOB_COLUMN,
                    new java.sql.Date(user.getDateOfBirth().getTime()));
            statement.setString(UPDATE_USER_ACTIVITY_COLUMN, user.getActivity());
            statement.setInt(UPDATE_USER_ROLE_COLUMN, user.getRole());
            statement.setBoolean(UPDATE_USER_ACTIVE_COLUMN, user.isActive());
            statement.setString(UPDATE_USER_USERNAME_COLUMN, user.getUsername());
            if (statement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }
}
