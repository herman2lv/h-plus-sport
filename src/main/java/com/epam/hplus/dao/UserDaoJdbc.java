package com.epam.hplus.dao;

import com.epam.hplus.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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

    @Override
    public int createUser(Connection connection, User user) {
        String query = INSERT_INTO + USERS_TABLE + " values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(USERS_USERNAME_INDEX, user.getUsername());
            statement.setString(USERS_PASSWORD_INDEX, user.getPassword());
            statement.setString(USERS_FIRST_NAME_INDEX, user.getFirstName());
            statement.setString(USERS_LAST_NAME_INDEX, user.getLastName());
            statement.setDate(USERS_DOB_INDEX, new java.sql.Date(user.getDateOfBirth().getTime()));
            statement.setString(USERS_ACTIVITY_INDEX, user.getActivity());
            return statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return 0;
        }
    }

    @Override
    public User getUserProfile(Connection connection, String username) {
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
        return new User(nameOriginalCase, password, firstName, lastName, activity, dateOfBirth);
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
}
