package com.epam.hplus.model.validators;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class ProductValidatorTest {
    private final ProductValidator validator = new ProductValidator();
    private final BigDecimal validCost;
    private final BigDecimal notValidCost;
    private final String validImage;
    private final String notValidImage;
    private final String validProductName;
    private final String notValidProductName;

    public ProductValidatorTest(BigDecimal validCost, BigDecimal notValidCost,
                                String validImage, String notValidImage,
                                String validProductName, String notValidProductName) {
        this.validCost = validCost;
        this.notValidCost = notValidCost;
        this.validImage = validImage;
        this.notValidImage = notValidImage;
        this.validProductName = validProductName;
        this.notValidProductName = notValidProductName;
    }

    @Test
    public void isValidCostPositiveTest() {
        Assert.assertTrue(validator.isValidCost(validCost));
    }

    @Test
    public void isValidCostNegativeTest() {
        Assert.assertFalse(validator.isValidCost(notValidCost));
    }

    @Test
    public void isValidImagePositiveTest() {
        Assert.assertTrue(validator.isValidImage(validImage));
    }

    @Test
    public void isNotValidImagePositiveTest() {
        Assert.assertFalse(validator.isValidImage(notValidImage));
    }

    @Test
    public void isValidProductNameTest() {
        Assert.assertTrue(validator.isValidProductName(validProductName));
    }

    @Test
    public void isNotValidProductNameTest() {
        Assert.assertFalse(validator.isValidProductName(notValidProductName));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {BigDecimal.valueOf(12.12), BigDecimal.valueOf(-12.12),
                        "image.jpg", "image",
                        "aAzZаАяЯ0123456789_- ()'.", "too long product name contains more than 50 characters"},
                {BigDecimal.valueOf(12342.12), BigDecimal.ZERO,
                        "name.with.several.dots.jpg", "jpg.image",
                        "not too short", "<name>"},
                {BigDecimal.valueOf(563.12), BigDecimal.valueOf(-0.0000000001),
                        "s.jpg", "image.jpeg",
                        "not too long", "sh"},
                {BigDecimal.valueOf(1), BigDecimal.valueOf(-1),
                        "loooooooooooongName.jpg", "image.jpg.rar",
                        "aAzZаАяЯ0123456789_- ()", "product/ name/"},
                {BigDecimal.valueOf(10), BigDecimal.valueOf(-10),
                        "very_StRaNgE-n.a.m.e.jpg", "image.jpg.7z",
                        "aAzZаАяЯ0123456789_- ()", "\\back \\slash"},
                {BigDecimal.valueOf(1000), BigDecimal.valueOf(-223555.12),
                        "имя_на_кириллице.jpg", "&&&.jpg!",
                        "валидное имя на кириллице", "hm* it"},
                {BigDecimal.valueOf(99999999999L), BigDecimal.valueOf(0.0000000000),
                        ".jpg", "null",
                        "valid letters and numbers", "why not?"},
                {BigDecimal.valueOf(12.00000000000001), BigDecimal.valueOf(-0.0),
                        "-!90))9.jpg", "",
                        "Name with '.com'", "with,coma"},
                {BigDecimal.valueOf(43.2443), BigDecimal.valueOf(-134242.12),
                        "dfmldf.jpg", "кириллица.озп",
                        "That's great", "кириллица с запятой,"},
                {BigDecimal.valueOf(78.010101), BigDecimal.valueOf(0),
                        "null.jpg", "image.jpg.png",
                        "Elizabeth the II", "double quote\""},
                {BigDecimal.valueOf(0.00000000000001), BigDecimal.valueOf(-12.12),
                        "jpg.jpg.jpg", "jpg",
                        "Terminator 2", "<-- arrows -->"}
        });
    }
}
