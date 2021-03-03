package com.epam.hplus.model.service;

import com.epam.hplus.model.beans.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(value = Parameterized.class)
public class CartServiceTest {
    private final List<Product> cart;
    private final BigDecimal expectedTotalCost;
    private final Map<Product, Long> expectedMap;

    public CartServiceTest(List<Product> cart, BigDecimal expectedTotalCost,
                           Map<Product, Long> expectedMap) {
        this.cart = cart;
        this.expectedTotalCost = expectedTotalCost;
        this.expectedMap = expectedMap;
    }

    @Test
    public void addProductPositiveTest() {
        Assert.assertTrue(CartService.addProduct(new ArrayList<>(), new Product()));
    }

    @Test
    public void addProductNegativeTest() {
        Assert.assertFalse(CartService.addProduct(new ArrayList<>(), null));
    }

    @Test
    public void countTotalCostTest() {
        Assert.assertEquals(expectedTotalCost, CartService.countTotalCost(cart));
    }

    @Test
    public void groupProductsTest() {
        Assert.assertEquals(expectedMap, CartService.groupProducts(cart));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Product product1 = new Product();
        product1.setProductId(1);
        product1.setCost(BigDecimal.valueOf(12.10));
        Product product2 = new Product();
        product2.setProductId(2);
        product2.setCost(BigDecimal.valueOf(10.90));
        Product product3 = new Product();
        product3.setProductId(3);
        product3.setCost(BigDecimal.valueOf(2.50));
        Product product4 = new Product();
        product4.setProductId(4);
        product4.setCost(BigDecimal.valueOf(25.30));
        Product product5 = new Product();
        product5.setProductId(5);
        product5.setCost(BigDecimal.valueOf(5.00));
        return Arrays.asList(new Object[][]{
                {Arrays.asList(product1, product2, product3, product4, product5),
                        BigDecimal.valueOf(55.80),
                        new HashMap<>(Map.of(product1, 1L,
                                product2, 1L,
                                product3, 1L,
                                product4, 1L,
                                product5, 1L))},
                {Arrays.asList(product1, product1, product1, product1, product1),
                        BigDecimal.valueOf(60.50),
                        new HashMap<>(Map.of(product1, 5L))},
                {Arrays.asList(product1, product2, product3, product1, product2),
                        BigDecimal.valueOf(48.50),
                        new HashMap<>(Map.of(product1, 2L,
                                product2, 2L,
                                product3, 1L))},
                {Arrays.asList(product2, product2, product2, product5, product5),
                        BigDecimal.valueOf(42.70),
                        new HashMap<>(Map.of(product2, 3L,
                                product5, 2L))},
                {Arrays.asList(product1, product2, product1, product1, product1),
                        BigDecimal.valueOf(59.30),
                        new HashMap<>(Map.of(product1, 4L,
                                product2, 1L))},
                {Arrays.asList(product5, product5, product3),
                        BigDecimal.valueOf(12.50),
                        new HashMap<>(Map.of(product3, 1L,
                                product5, 2L))},
                {Arrays.asList(product2, product4, product2, product4, product2),
                        BigDecimal.valueOf(83.30),
                        new HashMap<>(Map.of(product2, 3L,
                                product4, 2L))},
                {Collections.singletonList(product1),
                        BigDecimal.valueOf(12.10),
                        new HashMap<>(Map.of(product1, 1L))},
                {Arrays.asList(product3, product3, product3, product3, product3),
                        BigDecimal.valueOf(12.50),
                        new HashMap<>(Map.of(product3, 5L))},
                {Arrays.asList(product3, product4, product3, product4, product5),
                        BigDecimal.valueOf(60.60),
                        new HashMap<>(Map.of(product3, 2L,
                                product4, 2L,
                                product5, 1L))},
                {Arrays.asList(product5, product5, product5, product5, product5, product5),
                        BigDecimal.valueOf(30.00),
                        new HashMap<>(Map.of(product5, 6L))},
                {Arrays.asList(product5, product5),
                        BigDecimal.valueOf(10.00),
                        new HashMap<>(Map.of(product5, 2L))}
        });
    }
}
