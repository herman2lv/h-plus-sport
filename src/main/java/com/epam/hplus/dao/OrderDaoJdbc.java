package com.epam.hplus.dao;

import com.epam.hplus.beans.Order;
import com.epam.hplus.beans.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

import static com.epam.hplus.constants.Database.ORDERS_CONFIRMATION;
import static com.epam.hplus.constants.Database.ORDERS_DETAILS_NUMBER_OF_PRODUCTS;
import static com.epam.hplus.constants.Database.ORDERS_DETAILS_ORDER_ID;
import static com.epam.hplus.constants.Database.ORDERS_DETAILS_ORDER_ID_FULL;
import static com.epam.hplus.constants.Database.ORDERS_DETAILS_PRODUCT_ID_FULL;
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
import static com.epam.hplus.constants.Database.PRODUCTS_PRODUCT_ID_FULL;
import static com.epam.hplus.constants.Database.PRODUCTS_PRODUCT_NAME;
import static com.epam.hplus.constants.Database.PRODUCTS_TABLE;

public class OrderDaoJdbc implements OrderDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDaoJdbc.class);
    private static final String SELECT_ALL_FROM = "SELECT * FROM ";
    private static final String WHERE = " WHERE ";
    private static final String EQUALS = " = ";
    private static final String LEFT_JOIN = " LEFT JOIN ";
    private static final String ON = " ON ";
    private static final String QUESTION_MARK = "?";
    private static final String INSERT_INTO = "INSERT INTO ";
    private static final String COMA = ", ";
    private static final int INVALID_ID = -1;
    private static final int INSERT_ORDER_ID_COLUMN = 1;
    private static final int INSERT_ORDER_PRODUCT_ID_COLUMN = 2;
    private static final int INSERT_ORDERS_NUMBER_OF_PRODUCTS = 3;
    private static final int INSERT_ORDERS_USERNAME_COLUMN = 1;
    private static final int INSERT_ORDERS_DATE_COLUMN = 2;
    private static final int INSERT_ORDERS_COST_COLUMN = 3;
    private static final int INSERT_ORDERS_STATUS_CONFIRM_COLUMN = 4;
    private static final String DELETE_FROM = "DELETE FROM ";
    private static final String SELECT = "SELECT ";
    private static final String FROM = " FROM ";
    private static final String UPDATE = "UPDATE ";
    private static final String SET = " SET ";
    private static final int INSERT_ORDERS_ID_COLUMN = 5;

    @Override
    public List<Order> getOrdersByUser(Connection connection, String username) {
        List<Order> orders = new ArrayList<>();
        String queryOrders = SELECT_ALL_FROM + ORDERS_TABLE
                + WHERE + ORDERS_USERNAME + EQUALS + QUESTION_MARK;
        try (PreparedStatement statementOrders = connection.prepareStatement(queryOrders)) {
            statementOrders.setString(1, username);
            try (ResultSet ordersSet = statementOrders.executeQuery()) {
                while (ordersSet.next()) {
                    orders.add(createInstanceOfOrder(connection, ordersSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return orders;
    }

    private Map<Product, Long> createMapOfProducts(ResultSet productsSet)
            throws SQLException {
        Map<Product, Long> products = new HashMap<>();
        while (productsSet.next()) {
            products.put(createInstanceOfProduct(productsSet),
                    productsSet.getLong(ORDERS_DETAILS_NUMBER_OF_PRODUCTS));
        }
        return products;
    }

    private Order createInstanceOfOrder(Connection connection, ResultSet ordersSet)
            throws SQLException {
        int orderId = ordersSet.getInt(ORDERS_ORDER_ID);
        String queryProducts = SELECT_ALL_FROM + ORDERS_DETAILS_TABLE
                + LEFT_JOIN + PRODUCTS_TABLE
                + ON + ORDERS_DETAILS_PRODUCT_ID_FULL
                + EQUALS + PRODUCTS_PRODUCT_ID_FULL
                + WHERE + ORDERS_DETAILS_ORDER_ID_FULL + EQUALS + QUESTION_MARK;
        try (PreparedStatement statementProducts = connection.prepareStatement(queryProducts)) {
            statementProducts.setInt(1, orderId);
            try (ResultSet productsSet = statementProducts.executeQuery()) {
                Map<Product, Long> products = createMapOfProducts(productsSet);
                String username = ordersSet.getString(ORDERS_USERNAME);
                Date orderDate = new Date(ordersSet.getDate(ORDERS_ORDER_DATE).getTime());
                BigDecimal cost = ordersSet.getBigDecimal(ORDERS_ORDER_COST);
                boolean status = ordersSet.getBoolean(ORDERS_CONFIRMATION);
                return new Order(orderId, username, orderDate, products, cost, status);
            }
        }
    }

    private Product createInstanceOfProduct(ResultSet resultSet) throws SQLException {
        return new Product(resultSet.getLong(PRODUCTS_PRODUCT_ID),
                resultSet.getString(PRODUCTS_PRODUCT_NAME),
                resultSet.getString(PRODUCTS_IMAGE_PATH),
                resultSet.getBigDecimal(PRODUCTS_COST),
                resultSet.getString(PRODUCTS_DESCRIPTION));
    }

    @Override
    public int createOrder(Connection connection, Order order) {
        try {
            int orderId = insertOrder(connection, order);
            insertOrdersDetails(connection, order, orderId);
            return orderId;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return INVALID_ID;
        }
    }

    private void insertOrdersDetails(Connection connection, Order order, int orderId)
            throws SQLException {
        String productsQuery = INSERT_INTO + ORDERS_DETAILS_TABLE + " values (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(productsQuery)) {
            for (Map.Entry<Product, Long> entry : order.getListOfProducts()) {
                statement.setInt(INSERT_ORDER_ID_COLUMN, orderId);
                statement.setLong(INSERT_ORDER_PRODUCT_ID_COLUMN, entry.getKey().getProductId());
                statement.setLong(INSERT_ORDERS_NUMBER_OF_PRODUCTS, entry.getValue());
                statement.executeUpdate();
            }
        }
    }

    private int insertOrder(Connection connection, Order order) throws SQLException {
        String orderQuery = INSERT_INTO + ORDERS_TABLE
                + " (" + ORDERS_USERNAME + COMA + ORDERS_ORDER_DATE + COMA
                + ORDERS_ORDER_COST + COMA + ORDERS_CONFIRMATION + ") "
                + " values (?, ?, ?, ?)";
        try (PreparedStatement statement =
                     connection.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(INSERT_ORDERS_USERNAME_COLUMN, order.getUsername());
            statement.setDate(INSERT_ORDERS_DATE_COLUMN,
                    new java.sql.Date(order.getOrderDate().getTime()));
            statement.setBigDecimal(INSERT_ORDERS_COST_COLUMN, order.getOrderCost());
            statement.setBoolean(INSERT_ORDERS_STATUS_CONFIRM_COLUMN, false);
            if (statement.executeUpdate() > 0) {
                return getKey(statement);
            }
        }
        return INVALID_ID;
    }

    private int getKey(PreparedStatement statement) throws SQLException {
        try (ResultSet keys = statement.getGeneratedKeys()) {
            if (keys.next()) {
                return keys.getInt(1);
            }
        }
        return INVALID_ID;
    }

    @Override
    public boolean removeOrder(Connection connection, int orderId) {
        try {
            if (isApproved(connection, orderId)) {
                return false;
            }
            deleteOrderDetails(connection, orderId);
            deleteOrder(connection, orderId);
            return true;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    private void deleteOrder(Connection connection, int orderId) throws SQLException {
        String orderQuery = DELETE_FROM + ORDERS_TABLE
                + WHERE + ORDERS_ORDER_ID + EQUALS + QUESTION_MARK;
        try (PreparedStatement statement = connection.prepareStatement(orderQuery)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        }
    }

    private void deleteOrderDetails(Connection connection, int orderId) throws SQLException {
        String productsQuery = DELETE_FROM + ORDERS_DETAILS_TABLE
                + WHERE + ORDERS_DETAILS_ORDER_ID + EQUALS + QUESTION_MARK;
        try (PreparedStatement statement = connection.prepareStatement(productsQuery)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        }
    }

    private boolean isApproved(Connection connection, int orderId) throws SQLException {
        String query = SELECT + ORDERS_CONFIRMATION + FROM + ORDERS_TABLE
                + WHERE + ORDERS_ORDER_ID + EQUALS + QUESTION_MARK;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, orderId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean(ORDERS_CONFIRMATION);
                }
            }
        }
        return false;
    }

    @Override
    public List<Order> getOrders(Connection connection) {
        List<Order> orders = new ArrayList<>();
        String queryOrders = SELECT_ALL_FROM + ORDERS_TABLE;
        try (Statement statement = connection.createStatement();
             ResultSet ordersSet = statement.executeQuery(queryOrders)) {
            while (ordersSet.next()) {
                orders.add(createInstanceOfOrder(connection, ordersSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return orders;
    }

    @Override
    public Order getOrderById(Connection connection, int orderId) {
        Order order = null;
        String query = SELECT_ALL_FROM + ORDERS_TABLE
                + WHERE + ORDERS_ORDER_ID + EQUALS + QUESTION_MARK;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, orderId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    order = createInstanceOfOrder(connection, resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return order;
    }

    @Override
    public boolean updateOrder(Connection connection, Order order) {
        String query = UPDATE + ORDERS_TABLE + SET
                + ORDERS_USERNAME + EQUALS + QUESTION_MARK + COMA
                + ORDERS_ORDER_DATE + EQUALS + QUESTION_MARK + COMA
                + ORDERS_ORDER_COST + EQUALS + QUESTION_MARK + COMA
                + ORDERS_CONFIRMATION + EQUALS + QUESTION_MARK
                + WHERE + ORDERS_ORDER_ID + EQUALS + QUESTION_MARK;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(INSERT_ORDERS_USERNAME_COLUMN, order.getUsername());
            statement.setDate(INSERT_ORDERS_DATE_COLUMN,
                    new java.sql.Date(order.getOrderDate().getTime()));
            statement.setBigDecimal(INSERT_ORDERS_COST_COLUMN, order.getOrderCost());
            statement.setBoolean(INSERT_ORDERS_STATUS_CONFIRM_COLUMN, order.isStatus());
            statement.setLong(INSERT_ORDERS_ID_COLUMN, order.getOrderId());
            if (statement.executeUpdate() == 0) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return false;
    }
}
