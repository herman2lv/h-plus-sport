package com.epam.hplus.dao;

import com.epam.hplus.beans.Order;

import java.sql.Connection;
import java.util.List;

public interface OrdersDao {
    List<Order> getOrdersOfUser(Connection connection, String username);
}
