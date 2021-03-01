package com.epam.hplus.model.dao;

import com.epam.hplus.model.beans.Order;

import java.util.List;

public interface OrderDao {
    List<Order> getOrdersByUser(String username);
    List<Order> getOrders();
    Order getOrderById(int orderId);
    int createOrder(Order order);
    boolean removeOrder(int orderId);
    boolean updateOrder(Order order);
}
