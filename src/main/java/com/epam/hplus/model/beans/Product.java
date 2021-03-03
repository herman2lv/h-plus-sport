package com.epam.hplus.model.beans;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Bean class of model layer represents product.
 */
public class Product {
    /**
     * Unique identification numeric value to define the product.
     */
    private long productId;
    /**
     * Trade name of the product.
     */
    private String name;
    /**
     * {@code String} represents path of the image of product at data storage.
     */
    private String productImgPath;
    /**
     * Cost of the product.
     */
    private BigDecimal cost;
    /**
     * Description of the product to provide common data about it and its general characteristics.
     */
    private String description;
    /**
     * Actual status of the product. {@code True} stands for actual products that are provided by
     * supplier, {@code false} stands for products that have been already outdated and no longer
     * supplied.
     */
    private boolean active;

    /**
     * Explicit default constructor.
     */
    public Product() {
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code long} productId that represents unique identification numeric value to define
     * the product.
     */
    public long getProductId() {
        return productId;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param productId {@code long} unique identification numeric value to define the product.
     */
    public void setProductId(long productId) {
        this.productId = productId;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code String} name that represents trade name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param name {@code String} represents trade name of the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code String} productImgPath that represents path of the image of product at data
     * storage.
     */
    public String getProductImgPath() {
        return productImgPath;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param productImgPath {@code String} represents path of the image of product at data
     *                       storage.
     */
    public void setProductImgPath(String productImgPath) {
        this.productImgPath = productImgPath;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return cost that represents {@code BigDecimal} value of the product's price.
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param cost {@code BigDecimal} value represents cost of the product.
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code String} description of the product to provide common data about it and its
     * general characteristics.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param description {@code String} description of the product to provide common data about it
     *                    and its general characteristics.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code boolean} active that represents actual status of the product. {@code True}
     * stands for actual products that are provided by supplier, {@code false} stands for products
     * that have been already outdated and no longer supplied.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param active {@code boolean} represents actual status of the product. {@code True} stands
     *               for actual products that are provided by supplier, {@code false} stands for
     *               products that have been already outdated and no longer supplied.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Standard implementation of the equals method. Compare this instance of product to another
     * object.
     *
     * @param object {@code Object} to be compared with this {@code Product}
     * @return {@code true} is object to compare is instance of {@code Product} and it has the same
     * value of all class members
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Product product = (Product) object;
        return productId == product.productId
                && Objects.equals(name, product.name)
                && Objects.equals(productImgPath, product.productImgPath)
                && Objects.equals(cost, product.cost)
                && Objects.equals(description, product.description);
    }

    /**
     * Implementation of the hashCode() method. Uses method hash() of the {@code Objects} class get
     * the hash value of the class members.
     *
     * @return {@code int} value of the hash value fo the all class members
     */
    @Override
    public int hashCode() {
        return Objects.hash(productId, name, productImgPath, cost, description);
    }
}
