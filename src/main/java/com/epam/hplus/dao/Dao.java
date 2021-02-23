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
            ResultSet resultSet = getSetOfProducts(connection, searchString);
            while (resultSet.next()) {
                products.add(createInstanceOfProduct(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return products;
    }

    private ResultSet getSetOfProducts(Connection connection, String searchString)
            throws SQLException {
        String query = SELECT_ALL_FROM + PRODUCTS_TABLE + WHERE + PRODUCTS_PRODUCT_NAME
                       + " like '%" + searchString + "%'";
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    private Product createInstanceOfProduct(ResultSet resultSet) throws SQLException {
        return new Product(resultSet.getLong(PRODUCTS_PRODUCT_ID),
                resultSet.getString(PRODUCTS_PRODUCT_NAME),
                resultSet.getString(PRODUCTS_IMAGE_PATH),
                resultSet.getBigDecimal(PRODUCTS_COST),
                resultSet.getString(PRODUCTS_DESCRIPTION));
    }

    public Product getProductById(Connection connection, int id) {
        Product product = null;
        try {
            ResultSet resultSet = getSetWithProduct(connection, id);
            if (resultSet.next()) {
                product = createInstanceOfProduct(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return product;
    }

    private ResultSet getSetWithProduct(Connection connection, int id) throws SQLException {
        String query = SELECT_ALL_FROM + PRODUCTS_TABLE + WHERE + PRODUCTS_PRODUCT_ID + " = " + id;
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public int createUser(Connection connection, User user) {
        int rowsAffected = 0;
        try {
            rowsAffected = insertUser(connection, user);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return rowsAffected;
    }

    private int insertUser(Connection connection, User user) throws SQLException {
        String query = "INSERT into " + USERS_TABLE + " values (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(USERS_USERNAME_INDEX, user.getUsername());
        statement.setString(USERS_PASSWORD_INDEX, user.getPassword());
        statement.setString(USERS_FIRST_NAME_INDEX, user.getFirstName());
        statement.setString(USERS_LAST_NAME_INDEX, user.getLastName());
        statement.setDate(USERS_DOB_INDEX, new java.sql.Date(user.getDateOfBirth().getTime()));
        statement.setString(USERS_ACTIVITY_INDEX, user.getActivity());
        return statement.executeUpdate();
    }

    public User getUserProfile(Connection connection, String username) {
        User user = null;
        try {
            ResultSet resultSet = getSetOfUsers(connection, username);
            if (resultSet.next()) {
                user = createInstanceOfUser(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }

    private ResultSet getSetOfUsers(Connection connection, String username)
            throws SQLException {
        String query = SELECT_ALL_FROM + USERS_TABLE + WHERE + USERS_USERNAME + EQUALS + "?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        return statement.executeQuery();
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

    public boolean validateUser(Connection connection, String username, String password) {
        try {
            ResultSet set = getSetOfUsers(connection, username, password);
            if (set.next()) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    private ResultSet getSetOfUsers(Connection connection, String username, String password)
            throws SQLException {
        String query = SELECT_ALL_FROM + USERS_TABLE + WHERE + USERS_USERNAME + EQUALS
                       + "? AND " + USERS_PASSWORD + EQUALS + "?" + " COLLATE utf8mb4_bin";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        return statement.executeQuery();
    }

    public List<Order> getOrdersOfUser(Connection connection, String username) {
        List<Order> orders = new ArrayList<>();
        try {
            ResultSet ordersSet = getSetOfOrders(connection, username);
            while (ordersSet.next()) {
                orders.add(createOrder(connection, ordersSet, username));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return orders;
    }

    private ResultSet getSetOfOrders(Connection connection, String username)
            throws SQLException {
        String queryOrders = SELECT_ALL_FROM + ORDERS_TABLE
                             + WHERE + ORDERS_USERNAME + EQUALS + "?";
        PreparedStatement statementOrders = connection.prepareStatement(queryOrders);
        statementOrders.setString(1, username);
        return statementOrders.executeQuery();
    }

    private ResultSet getProductsFromOrder(Connection connection, int orderId)
            throws SQLException {
        String queryProducts = SELECT_ALL_FROM + ORDERS_DETAILS_TABLE
                               + " LEFT JOIN " + PRODUCTS_TABLE
                               + " ON " + ORDERS_DETAILS_PRODUCT_ID_FULL
                               + EQUALS + PRODUCTS_PRODUCT_ID_FULL
                               + WHERE + ORDERS_DETAILS_ORDER_ID_FULL + EQUALS + "?";
        PreparedStatement statementProducts = connection.prepareStatement(queryProducts);
        statementProducts.setInt(1, orderId);
        return statementProducts.executeQuery();
    }

    private Map<Product, Integer> createMapOfProducts(ResultSet productsSet)
            throws SQLException {
        Map<Product, Integer> products = new HashMap<>();
        while (productsSet.next()) {
            products.put(createInstanceOfProduct(productsSet),
                    productsSet.getInt(ORDERS_DETAILS_NUMBER));
        }
        return products;
    }

    private Order createOrder(Connection connection, ResultSet ordersSet, String username)
            throws SQLException {
        int orderId = ordersSet.getInt(ORDERS_ORDER_ID);
        ResultSet productsSet = getProductsFromOrder(connection, orderId);
        Map<Product, Integer> products = createMapOfProducts(productsSet);
        Date orderDate = new Date(ordersSet.getDate(ORDERS_ORDER_DATE).getTime());
        BigDecimal cost = ordersSet.getBigDecimal(ORDERS_ORDER_COST);
        return new Order(orderId, username, orderDate, products, cost);
    }
}
