package com.epam.hplus.controller.commands.impl.authorized.admin;

import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.controller.commands.util.RequestProcessor;
import com.epam.hplus.model.beans.Product;
import com.epam.hplus.model.service.ProductService;
import com.epam.hplus.model.validators.ProductValidator;
import com.epam.hplus.util.resources.ConfigurationManger;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;

import static com.epam.hplus.util.constants.Context.REQUEST_PRODUCT;

public class EditProductCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String productName = req.getParameter("productName");
        BigDecimal cost = RequestProcessor.getBigDecimalFromRequest(req, "cost");
        String description = req.getParameter("description");
        int productId = RequestProcessor.getIntFromRequest(req, REQUEST_PRODUCT);
        if (isValidData(productName, cost)) {
            Product product = ProductService.getProduct(productId);
            product.setName(productName);
            product.setCost(cost);
            product.setDescription(description);
            ProductService.updateProduct(product);
        }
        return ConfigurationManger.getProperty("page.productManagementRedirect");

    }

    private boolean isValidData(String productName, BigDecimal cost) {
        ProductValidator validator = new ProductValidator();
        return validator.isValidCost(cost)
                && validator.isValidProductName(productName);

    }
}
