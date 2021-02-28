package com.epam.hplus.dao;

import com.epam.hplus.beans.Order;

import java.sql.Connection;
import java.util.List;

public interface OrderDao {
    List<Order> getOrdersOfUser(Connection connection, String username);
    int createOrder(Connection connection, Order order);
    boolean removeOrder(Connection connection, int orderId);
}
