package com.epam.hplus.model.validators;

import java.math.BigDecimal;

public class ProductValidator {
    private static final String PRODUCT_REGEX = "[a-zA-Zа-яА-я0-9_\\- ().']{4,50}";
    protected static final String JPG = ".jpg";

    public boolean isValidProductName(String productName) {
        return productName.matches(PRODUCT_REGEX);
    }

    public boolean isValidImage(String originalName) {
        return originalName.endsWith(JPG);
    }

    public boolean isValidCost(BigDecimal cost) {
        return cost.compareTo(BigDecimal.ZERO) > 0;
    }
}
