package com.epam.hplus.dao;

import com.epam.hplus.beans.Order;

import java.sql.Connection;
import java.util.List;

public interface OrderDao {
    List<Order> getOrdersByUser(Connection connection, String username);
    List<Order> getOrders(Connection connection);
    Order getOrderById(Connection connection, int orderId);
    int createOrder(Connection connection, Order order);
    boolean removeOrder(Connection connection, int orderId);
    boolean updateOrder(Connection connection, Order order);
}
