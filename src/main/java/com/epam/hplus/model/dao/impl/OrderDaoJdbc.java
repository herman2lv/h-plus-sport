package com.epam.hplus.model.dao.impl;

import com.epam.hplus.model.beans.Order;
import com.epam.hplus.model.beans.Product;
import com.epam.hplus.model.dao.OrderDao;
import com.epam.hplus.model.pool.ConnectionPool;
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

import static com.epam.hplus.util.constants.Database.ORDERS_CONFIRMATION;
import static com.epam.hplus.util.constants.Database.ORDERS_DETAILS_NUMBER_OF_PRODUCTS;
import static com.epam.hplus.util.constants.Database.ORDERS_ORDER_COST;
import static com.epam.hplus.util.constants.Database.ORDERS_ORDER_DATE;
import static com.epam.hplus.util.constants.Database.ORDERS_ORDER_ID;
import static com.epam.hplus.util.constants.Database.ORDERS_USERNAME;
import static com.epam.hplus.util.constants.Database.PRODUCTS_ACTIVE;
import static com.epam.hplus.util.constants.Database.PRODUCTS_COST;
import static com.epam.hplus.util.constants.Database.PRODUCTS_DESCRIPTION;
import static com.epam.hplus.util.constants.Database.PRODUCTS_IMAGE_PATH;
import static com.epam.hplus.util.constants.Database.PRODUCTS_PRODUCT_ID;
import static com.epam.hplus.util.constants.Database.PRODUCTS_PRODUCT_NAME;

public class OrderDaoJdbc implements OrderDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDaoJdbc.class);
    private static final OrderDaoJdbc INSTANCE = new OrderDaoJdbc();
    private static final String SELECT_ORDERS_BY_USER =
            "SELECT * FROM orders WHERE username = ? LIMIT ?, ?";
    private static final int SELECT_BY_USER_USERNAME_INDEX = 1;
    private static final int SELECT_BY_USER_LIMIT_CURRENT_INDEX = 2;
    private static final int SELECT_BY_USER_LIMIT_ON_PAGE_INDEX = 3;
    private static final String SELECT_ORDERS_DETAILS = "SELECT * FROM orders_details "
            + "LEFT JOIN products ON orders_details.product_id = products.product_id "
            + "WHERE orders_details.order_id = ?";
    private static final String INSERT_ORDERS_DETAILS =
            "INSERT INTO orders_details VALUES (?, ?, ?)";
    private static final int INSERT_ORDER_ID_COLUMN = 1;
    private static final int INSERT_ORDER_PRODUCT_ID_COLUMN = 2;
    private static final int INSERT_ORDERS_NUMBER_OF_PRODUCTS = 3;
    private static final String INSERT_ORDER =
            "INSERT INTO orders (username, order_date, order_cost, confirmation_status) "
                    + "VALUES (?, ?, ?, ?)";
    private static final int INSERT_ORDERS_USERNAME_COLUMN = 1;
    private static final int INSERT_ORDERS_DATE_COLUMN = 2;
    private static final int INSERT_ORDERS_COST_COLUMN = 3;
    private static final int INSERT_ORDERS_STATUS_CONFIRM_COLUMN = 4;
    private static final String DELETE_ORDER = "DELETE FROM orders WHERE order_id = ?";
    private static final String DELETE_ORDERS_DETAILS =
            "DELETE FROM orders_details WHERE order_id = ?";
    private static final String SELECT_IS_APPROVED =
            "SELECT confirmation_status FROM orders WHERE order_id = ?";
    private static final String SELECT_ALL_ORDERS = "SELECT * FROM orders LIMIT ?, ?";
    private static final int SELECT_ALL_LIMIT_CURRENT_INDEX = 1;
    private static final int SELECT_ALL_LIMIT_ON_PAGE_INDEX = 2;
    private static final String SELECT_ORDER_BY_ID = "SELECT * FROM orders WHERE order_id = ?";
    private static final String UPDATE_ORDER =
            "UPDATE orders SET username = ?, order_date = ?, order_cost = ?, "
                    + "confirmation_status = ? WHERE order_id = ?";
    private static final int UPDATE_ORDER_ID_COLUMN = 5;
    private static final int INVALID_ID = -1;
    protected static final int INVALID_COUNT = -1;
    protected static final String COUNT_ORDERS = "SELECT COUNT(*) FROM orders";
    protected static final String COUNT_ORDERS_BY_USER =
            "SELECT COUNT(*) FROM orders WHERE username = ?";

    private OrderDaoJdbc() {
    }

    public static OrderDaoJdbc getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Order> getOrdersByUser(String username, int currentIndex, int itemsOnPage) {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SELECT_ORDERS_BY_USER)) {
            statement.setString(SELECT_BY_USER_USERNAME_INDEX, username);
            statement.setInt(SELECT_BY_USER_LIMIT_CURRENT_INDEX, currentIndex);
            statement.setInt(SELECT_BY_USER_LIMIT_ON_PAGE_INDEX, itemsOnPage);
            try (ResultSet ordersSet = statement.executeQuery()) {
                while (ordersSet.next()) {
                    orders.add(createInstanceOfOrder(connection, ordersSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return orders;
    }

    @Override
    public int countOrdersOfUser(String username) {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_ORDERS_BY_USER)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return INVALID_COUNT;
    }

    @Override
    public int countOrders() {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(COUNT_ORDERS)) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return INVALID_COUNT;
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
        try (PreparedStatement statementProducts = connection.prepareStatement(SELECT_ORDERS_DETAILS)) {
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
        Product product = new Product();
        product.setProductId(resultSet.getLong(PRODUCTS_PRODUCT_ID));
        product.setName(resultSet.getString(PRODUCTS_PRODUCT_NAME));
        product.setProductImgPath(resultSet.getString(PRODUCTS_IMAGE_PATH));
        product.setCost(resultSet.getBigDecimal(PRODUCTS_COST));
        product.setDescription(resultSet.getString(PRODUCTS_DESCRIPTION));
        product.setActive(resultSet.getBoolean(PRODUCTS_ACTIVE));
        return product;
    }

    @Override
    public int createOrder(Order order) {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
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
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ORDERS_DETAILS)) {
            for (Map.Entry<Product, Long> entry : order.getListOfProducts()) {
                statement.setInt(INSERT_ORDER_ID_COLUMN, orderId);
                statement.setLong(INSERT_ORDER_PRODUCT_ID_COLUMN, entry.getKey().getProductId());
                statement.setLong(INSERT_ORDERS_NUMBER_OF_PRODUCTS, entry.getValue());
                statement.executeUpdate();
            }
        }
    }

    private int insertOrder(Connection connection, Order order) throws SQLException {
        try (PreparedStatement statement =
                     connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
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
    public boolean removeOrder(int orderId) {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
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
        try (PreparedStatement statement = connection.prepareStatement(DELETE_ORDER)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        }
    }

    private void deleteOrderDetails(Connection connection, int orderId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_ORDERS_DETAILS)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        }
    }

    private boolean isApproved(Connection connection, int orderId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_IS_APPROVED)) {
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
    public List<Order> getOrders(int currentIndex, int itemsOnPage) {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ORDERS)) {
            statement.setInt(SELECT_ALL_LIMIT_CURRENT_INDEX, currentIndex);
            statement.setInt(SELECT_ALL_LIMIT_ON_PAGE_INDEX, itemsOnPage);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    orders.add(createInstanceOfOrder(connection, resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return orders;
    }

    @Override
    public Order getOrderById(int orderId) {
        Order order = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ORDER_BY_ID)) {
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
    public boolean updateOrder(Order order) {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER)) {
            statement.setString(INSERT_ORDERS_USERNAME_COLUMN, order.getUsername());
            statement.setDate(INSERT_ORDERS_DATE_COLUMN,
                    new java.sql.Date(order.getOrderDate().getTime()));
            statement.setBigDecimal(INSERT_ORDERS_COST_COLUMN, order.getOrderCost());
            statement.setBoolean(INSERT_ORDERS_STATUS_CONFIRM_COLUMN, order.isConfirmed());
            statement.setLong(UPDATE_ORDER_ID_COLUMN, order.getOrderId());
            if (statement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return false;
    }
}
