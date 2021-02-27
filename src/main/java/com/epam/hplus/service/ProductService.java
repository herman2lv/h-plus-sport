package com.epam.hplus.service;

import com.epam.hplus.beans.Product;
import com.epam.hplus.dao.Dao;
import com.epam.hplus.dao.DbConnector;

import java.sql.Connection;

public class ProductService {
    private ProductService() {
    }

    public static Product getProduct(int productId) {
        Connection connection = DbConnector.getConnection();
        return new Dao().getProductById(connection, productId);
    }
}
