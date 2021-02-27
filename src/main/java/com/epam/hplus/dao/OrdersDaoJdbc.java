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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.hplus.constants.Database.ORDERS_DETAILS_NUMBER_OF_PRODUCTS;
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

public class OrdersDaoJdbc implements OrdersDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrdersDaoJdbc.class);
    private static final String SELECT_ALL_FROM = "SELECT * FROM ";
    private static final String WHERE = " WHERE ";
    private static final String EQUALS = " = ";
    private static final String LEFT_JOIN = " LEFT JOIN ";
    private static final String ON = " ON ";
    private static final String QUESTION_MARK = "?";

    @Override
    public List<Order> getOrdersOfUser(Connection connection, String username) {
        List<Order> orders = new ArrayList<>();
        String queryOrders = SELECT_ALL_FROM + ORDERS_TABLE
                             + WHERE + ORDERS_USERNAME + EQUALS + QUESTION_MARK;
        try (PreparedStatement statementOrders = connection.prepareStatement(queryOrders)) {
            statementOrders.setString(1, username);
            try (ResultSet ordersSet = statementOrders.executeQuery()) {
                while (ordersSet.next()) {
                    orders.add(createOrder(connection, ordersSet, username));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return orders;
    }

    private Map<Product, Integer> createMapOfProducts(ResultSet productsSet)
            throws SQLException {
        Map<Product, Integer> products = new HashMap<>();
        while (productsSet.next()) {
            products.put(createInstanceOfProduct(productsSet),
                    productsSet.getInt(ORDERS_DETAILS_NUMBER_OF_PRODUCTS));
        }
        return products;
    }

    private Order createOrder(Connection connection, ResultSet ordersSet, String username)
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
                Map<Product, Integer> products = createMapOfProducts(productsSet);
                Date orderDate = new Date(ordersSet.getDate(ORDERS_ORDER_DATE).getTime());
                BigDecimal cost = ordersSet.getBigDecimal(ORDERS_ORDER_COST);
                return new Order(orderId, username, orderDate, products, cost);
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
}
