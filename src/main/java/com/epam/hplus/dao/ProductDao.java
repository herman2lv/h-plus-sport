package com.epam.hplus.dao;

import com.epam.hplus.beans.Product;

import java.sql.Connection;
import java.util.List;

public interface ProductDao {
    List<Product> searchProducts(Connection connection, String searchString);
    Product getProductById(Connection connection, int id);
}
