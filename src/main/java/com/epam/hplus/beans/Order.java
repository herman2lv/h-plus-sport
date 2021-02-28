package com.epam.hplus.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Order {
    private long orderId;
    private String username;
    private Date orderDate;
    private Map<Product, Long> listOfProducts;
    private BigDecimal orderCost;
    private boolean status;

    public Order(long orderId, String username, Date orderDate,
                 Map<Product, Long> listOfProducts, BigDecimal orderCost, boolean status) {
        this.orderId = orderId;
        this.username = username;
        this.orderDate = orderDate;
        this.listOfProducts = listOfProducts;
        this.orderCost = orderCost;
        this.status = status;
    }

    public Order(String username, Date orderDate,
                 Map<Product, Long> listOfProducts, BigDecimal orderCost, boolean status) {
        this.username = username;
        this.orderDate = orderDate;
        this.listOfProducts = listOfProducts;
        this.orderCost = orderCost;
        this.status = status;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<Entry<Product, Long>> getListOfProducts() {
        return new ArrayList<>(listOfProducts.entrySet());
    }

    public void setListOfProducts(Map<Product, Long> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public BigDecimal getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(BigDecimal orderCost) {
        this.orderCost = orderCost;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
