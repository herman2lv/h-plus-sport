package com.epam.hplus.service;

import com.epam.hplus.beans.Product;
import com.epam.hplus.dao.DbConnector;
import com.epam.hplus.dao.ProductDao;
import com.epam.hplus.dao.ProductDaoJdbc;

import java.sql.Connection;
import java.util.List;

public class SearchService {
    private SearchService() {
    }

    public static List<Product> search(String searchString) {
        Connection connection = DbConnector.getConnection();
        ProductDao productDao = new ProductDaoJdbc();
        return productDao.searchProducts(connection, searchString);
    }
}
