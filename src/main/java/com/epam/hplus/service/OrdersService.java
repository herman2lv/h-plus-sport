package com.epam.hplus.service;

import com.epam.hplus.beans.Order;
import com.epam.hplus.dao.DbConnector;
import com.epam.hplus.dao.OrdersDao;
import com.epam.hplus.dao.OrdersDaoJdbc;

import java.sql.Connection;
import java.util.List;

public class OrdersService {
    private OrdersService() {
    }

    public static List<Order> getOrdersOfUser(String username) {
        Connection connection = DbConnector.getConnection();
        OrdersDao ordersDao = new OrdersDaoJdbc();
        return ordersDao.getOrdersOfUser(connection, username);
    }

}
