package com.epam.hplus.service;

import com.epam.hplus.beans.Product;
import com.epam.hplus.dao.DbConnector;
import com.epam.hplus.dao.ProductDao;
import com.epam.hplus.dao.ProductDaoJdbc;

import java.sql.Connection;

public class ProductService {
    private ProductService() {
    }

    public static Product getProduct(int productId) {
        Connection connection = DbConnector.getConnection();
        ProductDao productDao = new ProductDaoJdbc();
        return productDao.getProductById(connection, productId);
    }
}
