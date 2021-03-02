package com.epam.hplus.model.service;

import com.epam.hplus.model.beans.Product;
import com.epam.hplus.model.dao.impl.ProductDaoJdbc;
import com.epam.hplus.model.dao.ProductDao;

import java.util.List;

public class ProductService {
    private static final ProductDao PRODUCT_DAO = ProductDaoJdbc.getInstance();

    private ProductService() {
    }

    public static Product getProduct(int productId) {
        return PRODUCT_DAO.getProductById(productId);
    }

    public static List<Product> searchProducts(String searchString,
                                               int currentIndex, int itemsOnPage) {
        return PRODUCT_DAO.searchProducts(searchString, currentIndex, itemsOnPage);
    }

    public static int countSearchResults(String searchString) {
        return PRODUCT_DAO.countSearchResults(searchString);
    }

    public static List<Product> getProducts(int currentIndex, int itemsOnPage) {
        return PRODUCT_DAO.getProducts(currentIndex, itemsOnPage);
    }

    public static int countProducts() {
        return PRODUCT_DAO.countProducts();
    }

    public static boolean deleteProduct(int productId) {
        Product product = PRODUCT_DAO.getProductById(productId);
        product.setActive(false);
        return PRODUCT_DAO.updateProduct(product);
    }

    public static boolean updateProduct(Product product) {
        return PRODUCT_DAO.updateProduct(product);
    }
}
