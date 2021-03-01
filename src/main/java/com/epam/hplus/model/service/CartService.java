package com.epam.hplus.model.service;

import com.epam.hplus.model.beans.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CartService {
    private CartService() {
    }

    public static boolean addProduct(List<Product> cart, Product product) {
        if (product != null) {
            return cart.add(product);
        }
        return false;
    }

    public static boolean removeProduct(List<Product> cart, Product product) {
        return cart.remove(product);
    }

    public static Map<Product, Long> groupProducts(List<Product> productsList) {
        return productsList.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
    }

    public static BigDecimal countTotalCost(List<Product> productsList) {
        return productsList.stream()
                .map(Product::getCost)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
