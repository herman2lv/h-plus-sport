package com.epam.hplus.model.service;

import com.epam.hplus.model.beans.Order;
import com.epam.hplus.model.dao.Impl.OrderDaoJdbc;
import com.epam.hplus.model.dao.OrderDao;

import java.util.List;

public class OrderService {
    private static final OrderDao ORDER_DAO = OrderDaoJdbc.getInstance();

    private OrderService() {
    }

    public static List<Order> getOrdersOfUser(String username, int currentIndex, int itemsOnPage) {
        return ORDER_DAO.getOrdersByUser(username, currentIndex, itemsOnPage);
    }

    public static int countOrdersOfUser(String username) {
        return ORDER_DAO.countOrdersOfUser(username);
    }

    public static boolean createOrder(Order order) {
        return ORDER_DAO.createOrder(order) > 0;
    }

    public static boolean removeOrder(int orderId) {
        return ORDER_DAO.removeOrder(orderId);
    }

    public static List<Order> getOrders(int currentIndex, int itemsOnPage) {
        return ORDER_DAO.getOrders(currentIndex, itemsOnPage);
    }

    public static int countOrders() {
        return ORDER_DAO.countOrders();
    }

    public static boolean approveOrder(int orderId) {
        return setOrderStatus(orderId, true);
    }

    private static boolean setOrderStatus(int orderId, boolean status) {
        Order order = ORDER_DAO.getOrderById(orderId);
        order.setStatus(status);
        return ORDER_DAO.updateOrder(order);
    }

    public static boolean rejectOrder(int orderId) {
        return setOrderStatus(orderId, false);
    }
}
