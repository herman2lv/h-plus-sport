package com.epam.hplus.service;

import com.epam.hplus.beans.Order;
import com.epam.hplus.dao.Dao;
import com.epam.hplus.dao.DbConnector;

import java.sql.Connection;
import java.util.List;

public class OrdersService {
    private OrdersService() {
    }

    public static List<Order> getOrdersOfUser(String username) {
        Connection connection = DbConnector.getConnection();
        return new Dao().getOrdersOfUser(connection, username);
    }

}
