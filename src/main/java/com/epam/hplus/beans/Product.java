package com.epam.hplus.beans;

import java.math.BigDecimal;

public class Product {
    private long productId;
    private String name;
    private String productImgPath;
    private BigDecimal cost;
    private String description;

    public Product(long productId, String name, String productImgPath, BigDecimal cost, String description) {
        this.productId = productId;
        this.name = name;
        this.productImgPath = productImgPath;
        this.cost = cost;
        this.description = description;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductImgPath() {
        return productImgPath;
    }

    public void setProductImgPath(String productImgPath) {
        this.productImgPath = productImgPath;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
