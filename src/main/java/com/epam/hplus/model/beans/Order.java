package com.epam.hplus.model.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Bean class of model layer represents user's order.
 */
public class Order {
    /**
     * Unique identification numeric value to define the order.
     */
    private long orderId;
    /**
     * Name of the {@code User} which is owner of the order.
     */
    private String username;
    /**
     * Date of order creation.
     */
    private Date orderDate;
    /**
     * List of products represented by {@code Map} of {@code Product} and {@code Long} that
     * represents product's number.
     */
    private Map<Product, Long> listOfProducts;
    /**
     * Total cost of the {@code Order}. The sum of price of all {@code Product} in the order.
     */
    private BigDecimal orderCost;
    /**
     * Confirmation status of the order. {@code True} stands for approved and {@code false} stands
     * for rejected/pending.
     */
    private boolean confirmationStatus;

    /**
     * Constructor with all parameters, used to create instance of {@code Order} which already has
     * unique {@param orderId} and to retrieve information about user's order from data storage.
     *
     * @param orderId            {@code long} value of unique order identification number
     * @param username           {@code String} represents name of order's owner
     * @param orderDate          {@code Date} of order creation
     * @param listOfProducts     {@code Map} represents list of orders and its number
     * @param orderCost          {@code BigDecimal} value of total cost of order's {@code Product}
     * @param confirmationStatus {@code boolean} value represents confirmation status of teh order
     */
    public Order(long orderId, String username, Date orderDate, Map<Product, Long> listOfProducts,
                 BigDecimal orderCost, boolean confirmationStatus) {
        this.orderId = orderId;
        this.username = username;
        this.orderDate = orderDate;
        this.listOfProducts = listOfProducts;
        this.orderCost = orderCost;
        this.confirmationStatus = confirmationStatus;
    }

    /**
     * Constructor with all parameters except {@param orderId}, used to create instance of {@code
     * Order} which doesn't have unique {@param orderId} yet and to sen information about user's
     * order to data storage.
     *
     * @param username           {@code String} represents name of order's owner
     * @param orderDate          {@code Date} of order creation
     * @param listOfProducts     {@code Map} represents list of orders and its number
     * @param orderCost          {@code BigDecimal} value of total cost of order's {@code Product}
     * @param confirmationStatus {@code boolean} value represents confirmation status of teh order
     */
    public Order(String username, Date orderDate, Map<Product, Long> listOfProducts,
                 BigDecimal orderCost, boolean confirmationStatus) {
        this.username = username;
        this.orderDate = orderDate;
        this.listOfProducts = listOfProducts;
        this.orderCost = orderCost;
        this.confirmationStatus = confirmationStatus;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code long} orderId
     */
    public long getOrderId() {
        return orderId;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param orderId {@code long} value of unique order identification number
     */
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code String} username represents name of order's owner
     */
    public String getUsername() {
        return username;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param username {@code String} represents name of order's owner
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code Date} of order creation
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param orderDate {@code Date} of order creation
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Getter method returns iterable version of list of {@code Product} in order to facilitate
     * process of displaying and processing of data.
     *
     * @return {@code List} of {@code Map.Entry} of {@code Product} and its number represented by
     * {@code Long} value
     */
    public List<Entry<Product, Long>> getListOfProducts() {
        return new ArrayList<>(listOfProducts.entrySet());
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param listOfProducts {@code Map} represents list of orders and its number
     */
    public void setListOfProducts(Map<Product, Long> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code BigDecimal} value of total cost of all order's products
     */
    public BigDecimal getOrderCost() {
        return orderCost;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param orderCost {@code BigDecimal} value of total cost of order's {@code Product}
     */
    public void setOrderCost(BigDecimal orderCost) {
        this.orderCost = orderCost;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code boolean} value that represents confirmation status of the order
     */
    public boolean isConfirmed() {
        return confirmationStatus;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param confirmationStatus {@code boolean} value represents confirmation status of teh order
     */
    public void setConfirmationStatus(boolean confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }
}
