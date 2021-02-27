package com.epam.hplus.service;

import com.epam.hplus.beans.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CartService {
    private CartService() {
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
