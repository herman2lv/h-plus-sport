package com.epam.hplus.model.dao;

import com.epam.hplus.model.beans.Product;

import java.util.List;

public interface ProductDao {
    int createProduct(Product product);
    List<Product> searchProducts(String searchString, int currentIndex, int itemsOnPage);
    int countSearchResults(String searchString);
    List<Product> getProducts(int currentIndex, int itemsOnPage);
    int countProducts();
    Product getProductById(int productId);
    boolean updateProduct(Product product);
}
