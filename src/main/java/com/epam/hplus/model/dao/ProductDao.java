package com.epam.hplus.model.dao;

import com.epam.hplus.model.beans.Product;

import java.util.List;

public interface ProductDao {
    List<Product> searchProducts(String searchString);
    List<Product> getProducts(int currentIndex, int itemsOnPage);
    int countProducts();
    Product getProductById(int productId);
    boolean updateProduct(Product product);
}
