package com.epam.hplus.dao;

import static com.epam.hplus.constants.Database.ORDERS_DETAILS_NUMBER;
import static com.epam.hplus.constants.Database.ORDERS_DETAILS_PRODUCT_ID_FULL;
import static com.epam.hplus.constants.Database.ORDERS_DETAILS_ORDER_ID_FULL;
import static com.epam.hplus.constants.Database.PRODUCTS_PRODUCT_ID_FULL;
import static com.epam.hplus.constants.Database.ORDERS_DETAILS_TABLE;
import static com.epam.hplus.constants.Database.ORDERS_ORDER_COST;
import static com.epam.hplus.constants.Database.ORDERS_ORDER_DATE;
import static com.epam.hplus.constants.Database.ORDERS_ORDER_ID;
import static com.epam.hplus.constants.Database.ORDERS_TABLE;
import static com.epam.hplus.constants.Database.ORDERS_USERNAME;
import static com.epam.hplus.constants.Database.PRODUCTS_COST;
import static com.epam.hplus.constants.Database.PRODUCTS_DESCRIPTION;
import static com.epam.hplus.constants.Database.PRODUCTS_IMAGE_PATH;
import static com.epam.hplus.constants.Database.PRODUCTS_PRODUCT_ID;
import static com.epam.hplus.constants.Database.PRODUCTS_PRODUCT_NAME;
import static com.epam.hplus.constants.Database.PRODUCTS_TABLE;
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

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.hplus.beans.Order;
import com.epam.hplus.beans.Product;
import com.epam.hplus.beans.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dao {
    private static final Logger LOGGER = LoggerFactory.getLogger(Dao.class);
    private static final String SELECT_ALL_FROM = "SELECT * FROM ";
    private static final String WHERE = " WHERE ";
    private static final String EQUALS = " = ";

    public List<Product> searchProducts(Connection connection, String searchString) {
        List<Product> products = new ArrayList<>();
        try {
            String query = SELECT_ALL_FROM + PRODUCTS_TABLE + WHERE + PRODUCTS_PRODUCT_NAME
                           + " like '%" + searchString + "%'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                products.add(createInstanceOfProduct(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return products;
    }

    private Product createInstanceOfProduct(ResultSet resultSet) throws SQLException {
        return new Product(resultSet.getLong(PRODUCTS_PRODUCT_ID),
                resultSet.getString(PRODUCTS_PRODUCT_NAME),
                resultSet.getString(PRODUCTS_IMAGE_PATH),
                resultSet.getBigDecimal(PRODUCTS_COST),
                resultSet.getString(PRODUCTS_DESCRIPTION));
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
            statement.setDate(USERS_DOB_INDEX, new java.sql.Date(user.getDateOfBirth().getTime()));
            statement.setString(USERS_ACTIVITY_INDEX, user.getActivity());
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return rowsAffected;
    }

    public User getUserProfile(Connection connection, String username) {
        User user = null;
        try {
            String query = SELECT_ALL_FROM + USERS_TABLE + WHERE + USERS_USERNAME + EQUALS + "?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                String nameOriginalCase = set.getString(USERS_USERNAME);
                String password = set.getString(USERS_PASSWORD);
                String firstName = set.getString(USERS_FIRST_NAME);
                String lastName = set.getString(USERS_LAST_NAME);
                String activity = set.getString(USERS_ACTIVITY);
                Date dateOfBirth = set.getDate(USERS_DOB);
                user = new User(nameOriginalCase, password, firstName, lastName, activity, dateOfBirth);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }

    public boolean validateUser(Connection connection, String username, String password) {
        try {
            String query = SELECT_ALL_FROM + USERS_TABLE + WHERE + USERS_USERNAME + EQUALS
                           + "? AND " + USERS_PASSWORD + EQUALS + "?" + " COLLATE utf8mb4_bin";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    public List<Order> getOrdersOfUser(Connection connection, String username) {
        List<Order> orders = new ArrayList<>();
        try {
            String queryOrders = SELECT_ALL_FROM + ORDERS_TABLE
                                 + WHERE + ORDERS_USERNAME + EQUALS + "?";
            PreparedStatement statementOrders = connection.prepareStatement(queryOrders);
            String queryProducts = SELECT_ALL_FROM + ORDERS_DETAILS_TABLE
                                   + " LEFT JOIN " + PRODUCTS_TABLE
                                   + " ON " + ORDERS_DETAILS_PRODUCT_ID_FULL
                                   + EQUALS + PRODUCTS_PRODUCT_ID_FULL
                                   + WHERE + ORDERS_DETAILS_ORDER_ID_FULL + EQUALS + "?";
            PreparedStatement statementProducts = connection.prepareStatement(queryProducts);
            statementOrders.setString(1, username);
            ResultSet ordersSet = statementOrders.executeQuery();
            while (ordersSet.next()) {
                int orderId = ordersSet.getInt(ORDERS_ORDER_ID);
                statementProducts.setInt(1, orderId);
                ResultSet productsSet = statementProducts.executeQuery();
                Map<Product, Integer> products = new HashMap<>();
                while (productsSet.next()) {
                    products.put(createInstanceOfProduct(productsSet),
                            productsSet.getInt(ORDERS_DETAILS_NUMBER));
                }
                Date orderDate = new Date(ordersSet.getDate(ORDERS_ORDER_DATE).getTime());
                BigDecimal cost = ordersSet.getBigDecimal(ORDERS_ORDER_COST);
                orders.add(new Order(orderId, username, orderDate, products, cost));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return orders;
    }
}
