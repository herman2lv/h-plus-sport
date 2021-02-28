package com.epam.hplus.service;

import com.epam.hplus.beans.Order;
import com.epam.hplus.dao.DbConnector;
import com.epam.hplus.dao.OrderDao;
import com.epam.hplus.dao.OrderDaoJdbc;

import java.sql.Connection;
import java.util.List;

public class OrderService {
    private OrderService() {
    }

    public static List<Order> getOrdersOfUser(String username) {
        Connection connection = DbConnector.getConnection();
        OrderDao orderDao = new OrderDaoJdbc();
        return orderDao.getOrdersByUser(connection, username);
    }

    public static boolean createOrder(Order order) {
        Connection connection = DbConnector.getConnection();
        OrderDao orderDao = new OrderDaoJdbc();
        return orderDao.createOrder(connection, order) > 0;
    }

    public static boolean removeOrder(int orderId) {
        Connection connection = DbConnector.getConnection();
        OrderDao orderDao = new OrderDaoJdbc();
        return orderDao.removeOrder(connection, orderId);
    }

    public static List<Order> getOrders() {
        Connection connection = DbConnector.getConnection();
        OrderDao orderDao = new OrderDaoJdbc();
        return orderDao.getOrders(connection);
    }

    public static boolean approveOrder(int orderId) {
        return setOrderStatus(orderId, true);
    }

    private static boolean setOrderStatus(int orderId, boolean status) {
        Connection connection = DbConnector.getConnection();
        OrderDao orderDao = new OrderDaoJdbc();
        Order order = orderDao.getOrderById(connection, orderId);
        order.setStatus(status);
        return orderDao.updateOrder(connection, order);
    }

    public static boolean rejectOrder(int orderId) {
        return setOrderStatus(orderId, false);
    }
}
