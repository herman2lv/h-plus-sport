package com.epam.hplus.model.dao;

import com.epam.hplus.model.beans.Product;

import java.util.List;

public interface ProductDao {
    List<Product> searchProducts(String searchString);
    List<Product> getProducts();
    Product getProductById(int productId);
    boolean updateProduct(Product product);
}
