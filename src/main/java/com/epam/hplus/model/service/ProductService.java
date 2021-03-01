package com.epam.hplus.model.service;

import com.epam.hplus.model.beans.Product;
import com.epam.hplus.model.dao.Impl.ProductDaoJdbc;
import com.epam.hplus.model.dao.ProductDao;

import java.util.List;

public class ProductService {
    private static final ProductDao PRODUCT_DAO = ProductDaoJdbc.getInstance();

    private ProductService() {
    }

    public static Product getProduct(int productId) {
        return PRODUCT_DAO.getProductById(productId);
    }

    public static List<Product> searchProducts(String searchString) {
        return PRODUCT_DAO.searchProducts(searchString);
    }

    public static List<Product> getProducts() {
        return PRODUCT_DAO.getProducts();
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
