package com.epam.hplus.service;

import com.epam.hplus.beans.Product;
import com.epam.hplus.dao.Dao;
import com.epam.hplus.dao.DbConnector;

import java.sql.Connection;
import java.util.List;

public class SearchService {
    private SearchService() {
    }

    public static List<Product> search(String searchString) {
        Connection connection = DbConnector.getConnection();
        return new Dao().searchProducts(connection, searchString);
    }
}
