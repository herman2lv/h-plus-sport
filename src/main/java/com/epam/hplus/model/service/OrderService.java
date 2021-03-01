package com.epam.hplus.model.service;

import com.epam.hplus.model.beans.Order;
import com.epam.hplus.model.dao.Impl.OrderDaoJdbc;
import com.epam.hplus.model.dao.OrderDao;

import java.util.List;

public class OrderService {
    private OrderService() {
    }

    public static List<Order> getOrdersOfUser(String username) {
        OrderDao orderDao = new OrderDaoJdbc();
        return orderDao.getOrdersByUser(username);
    }

    public static boolean createOrder(Order order) {
        OrderDao orderDao = new OrderDaoJdbc();
        return orderDao.createOrder(order) > 0;
    }

    public static boolean removeOrder(int orderId) {
        OrderDao orderDao = new OrderDaoJdbc();
        return orderDao.removeOrder(orderId);
    }

    public static List<Order> getOrders() {
        OrderDao orderDao = new OrderDaoJdbc();
        return orderDao.getOrders();
    }

    public static boolean approveOrder(int orderId) {
        return setOrderStatus(orderId, true);
    }

    private static boolean setOrderStatus(int orderId, boolean status) {
        OrderDao orderDao = new OrderDaoJdbc();
        Order order = orderDao.getOrderById(orderId);
        order.setStatus(status);
        return orderDao.updateOrder(order);
    }

    public static boolean rejectOrder(int orderId) {
        return setOrderStatus(orderId, false);
    }
}
