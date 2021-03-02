package com.epam.hplus.model.service;

import com.epam.hplus.model.beans.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A service class of model layer, intended to process operations with
 * customer's {@code Cart}.
 */
public class CartService {
    /**
     * Private constructor to prevent instantiating of class object.
     */
    private CartService() {
    }

    /**
     * Add {@code Product} (if not null) into the user's cart.
     * @param cart {@code List} of {@code Product} represents user's cart
     * @param product {@code Product} that needs to be added into the cart
     * @return {@code true} if product was successfully added
     */
    public static boolean addProduct(List<Product> cart, Product product) {
        if (product != null) {
            return cart.add(product);
        }
        return false;
    }

    /**
     * Remove {@code Product} from user's cart.
     * @param cart {@code List} of {@code Product} represents user's cart
     * @param product {@code Product} that needs to be removed from the cart
     * @return {@code true} if product was successfully removed
     */
    public static boolean removeProduct(List<Product> cart, Product product) {
        return cart.remove(product);
    }

    /**
     * Group cart, represented by {@code List} of {@code Product}, by product,
     * and count number of each product in the cart.
     * @param cart {@code List} of {@code Product} represents user's cart
     * @return {@code Map} of {@code Product} and {@code Long}.
     * Each {@code Map.Entry} represent product and its number in the user's
     * cart.
     */
    public static Map<Product, Long> groupProducts(List<Product> cart) {
        return cart.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
    }

    /**
     * Count total cost of products in the user's cart.
     * @param cart {@code List} of {@code Product} represents user's cart
     * @return {@code BigDecimal} value of total cost of all {@code Product}
     * in the user's cart
     */
    public static BigDecimal countTotalCost(List<Product> cart) {
        return cart.stream()
                .map(Product::getCost)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
