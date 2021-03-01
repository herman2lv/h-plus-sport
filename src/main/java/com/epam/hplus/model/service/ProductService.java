package com.epam.hplus.model.service;

import com.epam.hplus.model.beans.Product;
import com.epam.hplus.model.dao.Impl.ProductDaoJdbc;
import com.epam.hplus.model.dao.ProductDao;

import java.util.List;

public class ProductService {
    private ProductService() {
    }

    public static Product getProduct(int productId) {
        ProductDao productDao = new ProductDaoJdbc();
        return productDao.getProductById(productId);
    }

    public static List<Product> searchProducts(String searchString) {
        ProductDao productDao = new ProductDaoJdbc();
        return productDao.searchProducts(searchString);
    }
}
