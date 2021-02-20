package com.epam.hplus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.epam.hplus.beans.Order;
import com.epam.hplus.beans.Product;
import com.epam.hplus.beans.User;

import static com.epam.hplus.constants.Database.ORDERS_IMAGE_PATH;
import static com.epam.hplus.constants.Database.ORDERS_ORDER_DATE;
import static com.epam.hplus.constants.Database.ORDERS_ORDER_ID;
import static com.epam.hplus.constants.Database.ORDERS_PRODUCT_NAME;
import static com.epam.hplus.constants.Database.ORDERS_TABLE;
import static com.epam.hplus.constants.Database.ORDERS_USERNAME;
import static com.epam.hplus.constants.Database.PRODUCTS_IMAGE_PATH;
import static com.epam.hplus.constants.Database.PRODUCTS_PRODUCT_ID;
import static com.epam.hplus.constants.Database.PRODUCTS_PRODUCT_NAME;
import static com.epam.hplus.constants.Database.PRODUCTS_TABLE;
import static com.epam.hplus.constants.Database.USERS_ACTIVITY;
import static com.epam.hplus.constants.Database.USERS_ACTIVITY_INDEX;
import static com.epam.hplus.constants.Database.USERS_AGE;
import static com.epam.hplus.constants.Database.USERS_AGE_INDEX;
import static com.epam.hplus.constants.Database.USERS_FIRST_NAME;
import static com.epam.hplus.constants.Database.USERS_FIRST_NAME_INDEX;
import static com.epam.hplus.constants.Database.USERS_LAST_NAME;
import static com.epam.hplus.constants.Database.USERS_LAST_NAME_INDEX;
import static com.epam.hplus.constants.Database.USERS_PASSWORD;
import static com.epam.hplus.constants.Database.USERS_PASSWORD_INDEX;
import static com.epam.hplus.constants.Database.USERS_TABLE;
import static com.epam.hplus.constants.Database.USERS_USERNAME;
import static com.epam.hplus.constants.Database.USERS_USERNAME_INDEX;

public class Dao {
    private static final String SELECT_ALL_FROM = "SELECT * FROM ";
    private static final String WHERE = " WHERE ";

    public List<Product> searchProducts(Connection connection, String searchString) {
        List<Product> products = new ArrayList<>();
        try {
            String query = SELECT_ALL_FROM + PRODUCTS_TABLE + WHERE
                           + PRODUCTS_PRODUCT_NAME + " like '%" + searchString + "%'";
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                products.add(new Product(set.getLong(PRODUCTS_PRODUCT_ID), set.getString(PRODUCTS_PRODUCT_NAME),
                        set.getString(PRODUCTS_IMAGE_PATH)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public int createUser(Connection connection, User user) {
        int rowsAffected = 0;
        try {
            String query = "INSERT into " + USERS_TABLE + " values (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(USERS_USERNAME_INDEX, user.getUsername());
            statement.setString(USERS_PASSWORD_INDEX, user.getPassword());
            statement.setString(USERS_FIRST_NAME_INDEX, user.getFirstName());
            statement.setString(USERS_LAST_NAME_INDEX, user.getLastName());
            statement.setInt(USERS_AGE_INDEX, user.getAge());
            statement.setString(USERS_ACTIVITY_INDEX, user.getActivity());
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    public User getUserProfile(Connection connection, String username) {
        User user = null;
        try {
            String query = SELECT_ALL_FROM + USERS_TABLE + WHERE + USERS_USERNAME + " = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                String nameOriginalCase = set.getString(USERS_USERNAME);
                String password = set.getString(USERS_PASSWORD);
                String firstName = set.getString(USERS_FIRST_NAME);
                String lastName = set.getString(USERS_LAST_NAME);
                String activity = set.getString(USERS_ACTIVITY);
                int age = set.getInt(USERS_AGE);
                user = new User(nameOriginalCase, password, firstName, lastName, activity, age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean validateUser(Connection connection, String username, String password) {
        try {
            String query = SELECT_ALL_FROM + USERS_TABLE + WHERE + USERS_USERNAME
                           + " = ? AND " + USERS_PASSWORD + " = ?" + " COLLATE utf8mb4_bin";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Order> getOrdersOfUser(Connection connection, String username) {
        List<Order> orders = new ArrayList<>();
        try {
            String query = SELECT_ALL_FROM + ORDERS_TABLE + WHERE + ORDERS_USERNAME + " = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                int id = set.getInt(ORDERS_ORDER_ID);
                String product = set.getString(ORDERS_PRODUCT_NAME);
                String productImgPath = set.getString(ORDERS_IMAGE_PATH);
                Date orderDate = new Date(set.getDate(ORDERS_ORDER_DATE).getTime());
                orders.add(new Order(id, product, productImgPath, orderDate, username));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
