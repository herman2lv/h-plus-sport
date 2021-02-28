package com.epam.hplus.dao;

import com.epam.hplus.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.hplus.constants.Database.USERS_ACTIVITY;
import static com.epam.hplus.constants.Database.USERS_ACTIVITY_INDEX;
import static com.epam.hplus.constants.Database.USERS_DOB;
import static com.epam.hplus.constants.Database.USERS_DOB_INDEX;
import static com.epam.hplus.constants.Database.USERS_FIRST_NAME;
import static com.epam.hplus.constants.Database.USERS_FIRST_NAME_INDEX;
import static com.epam.hplus.constants.Database.USERS_LAST_NAME;
import static com.epam.hplus.constants.Database.USERS_LAST_NAME_INDEX;
import static com.epam.hplus.constants.Database.USERS_PASSWORD;
import static com.epam.hplus.constants.Database.USERS_PASSWORD_INDEX;
import static com.epam.hplus.constants.Database.USERS_ROLE;
import static com.epam.hplus.constants.Database.USERS_ROLE_INDEX;
import static com.epam.hplus.constants.Database.USERS_TABLE;
import static com.epam.hplus.constants.Database.USERS_USERNAME;
import static com.epam.hplus.constants.Database.USERS_USERNAME_INDEX;

public class UserDaoJdbc implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoJdbc.class);
    private static final String SELECT_ALL_FROM = "SELECT * FROM ";
    private static final String WHERE = " WHERE ";
    private static final String EQUALS = " = ";
    private static final String INSERT_INTO = "INSERT into ";
    private static final String QUESTION_MARK = "?";
    private static final String AND = " AND ";
    private static final String MAKE_QUERY_CASE_SENSITIVE = " COLLATE utf8mb4_bin";
    private static final String UPDATE = "UPDATE ";
    private static final String SET = " SET ";
    private static final String COMA = ", ";
    private static final int UPDATE_USER_PASSWORD_COLUMN = 1;
    private static final int UPDATE_USER_FIRST_NAME_COLUMN = 2;
    private static final int UPDATE_USER_LAST_NAME_COLUMN = 3;
    private static final int UPDATE_USER_DOB_COLUMN = 4;
    private static final int UPDATE_USER_ACTIVITY_COLUMN = 5;
    private static final int UPDATE_USER_ROLE_COLUMN = 6;
    private static final int UPDATE_USER_USERNAME_COLUMN = 7;
    private static final String DELETE_FROM = "DELETE FROM ";

    @Override
    public int createUser(Connection connection, User user) {
        String query = INSERT_INTO + USERS_TABLE + " values (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(USERS_USERNAME_INDEX, user.getUsername());
            statement.setString(USERS_PASSWORD_INDEX, user.getPassword());
            statement.setString(USERS_FIRST_NAME_INDEX, user.getFirstName());
            statement.setString(USERS_LAST_NAME_INDEX, user.getLastName());
            statement.setDate(USERS_DOB_INDEX, new java.sql.Date(user.getDateOfBirth().getTime()));
            statement.setString(USERS_ACTIVITY_INDEX, user.getActivity());
            statement.setInt(USERS_ROLE_INDEX, user.getRole());
            return statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return 0;
        }
    }

    @Override
    public User getUser(Connection connection, String username) {
        User user = null;
        String query = SELECT_ALL_FROM + USERS_TABLE
                       + WHERE + USERS_USERNAME + EQUALS + QUESTION_MARK;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
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
    public boolean validateUserCredentials(Connection connection, String username,
                                           String password) {
        String query = SELECT_ALL_FROM + USERS_TABLE
                       + WHERE + USERS_USERNAME + EQUALS + QUESTION_MARK
                       + AND + USERS_PASSWORD + EQUALS + QUESTION_MARK
                       + MAKE_QUERY_CASE_SENSITIVE;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
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
        String nameOriginalCase = resultSet.getString(USERS_USERNAME);
        String password = resultSet.getString(USERS_PASSWORD);
        String firstName = resultSet.getString(USERS_FIRST_NAME);
        String lastName = resultSet.getString(USERS_LAST_NAME);
        String activity = resultSet.getString(USERS_ACTIVITY);
        Date dateOfBirth = resultSet.getDate(USERS_DOB);
        int role = resultSet.getInt(USERS_ROLE);
        return new User(nameOriginalCase, password, firstName, lastName,
                activity, dateOfBirth, role);
    }

    @Override
    public boolean isUsernameFree(Connection connection, String username) {
        String query = SELECT_ALL_FROM + USERS_TABLE
                       + WHERE + USERS_USERNAME + EQUALS + QUESTION_MARK;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                return !resultSet.next();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public List<User> getUsers(Connection connection) {
        List<User> users = new ArrayList<>();
        String query = SELECT_ALL_FROM + USERS_TABLE;
        try (Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                users.add(createInstanceOfUser(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return users;
    }

    @Override
    public boolean updateUser(Connection connection, User user) {
        String query = UPDATE + USERS_TABLE + SET
                + USERS_PASSWORD + EQUALS + QUESTION_MARK + COMA
                + USERS_FIRST_NAME + EQUALS + QUESTION_MARK + COMA
                + USERS_LAST_NAME + EQUALS + QUESTION_MARK + COMA
                + USERS_DOB + EQUALS + QUESTION_MARK + COMA
                + USERS_ACTIVITY + EQUALS + QUESTION_MARK + COMA
                + USERS_ROLE + EQUALS + QUESTION_MARK
                + WHERE + USERS_USERNAME + EQUALS + QUESTION_MARK;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(UPDATE_USER_PASSWORD_COLUMN, user.getPassword());
            statement.setString(UPDATE_USER_FIRST_NAME_COLUMN, user.getFirstName());
            statement.setString(UPDATE_USER_LAST_NAME_COLUMN, user.getLastName());
            statement.setDate(UPDATE_USER_DOB_COLUMN,
                    new java.sql.Date(user.getDateOfBirth().getTime()));
            statement.setString(UPDATE_USER_ACTIVITY_COLUMN, user.getActivity());
            statement.setInt(UPDATE_USER_ROLE_COLUMN, user.getRole());
            statement.setString(UPDATE_USER_USERNAME_COLUMN, user.getUsername());
            if (statement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return false;
    }

    @Override
    public boolean deleteUser(Connection connection, String username) {
        String query = DELETE_FROM + USERS_TABLE
                + WHERE + USERS_USERNAME + EQUALS + QUESTION_MARK;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }
}
