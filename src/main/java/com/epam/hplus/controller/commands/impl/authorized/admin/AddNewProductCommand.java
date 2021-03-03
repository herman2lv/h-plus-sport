package com.epam.hplus.controller.commands.impl.authorized.admin;

import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.controller.commands.util.ImageProcessor;
import com.epam.hplus.controller.commands.util.Paginator;
import com.epam.hplus.controller.commands.util.RequestProcessor;
import com.epam.hplus.model.beans.Product;
import com.epam.hplus.model.service.ProductService;
import com.epam.hplus.model.validators.ProductValidator;
import com.epam.hplus.util.resources.ConfigurationManger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

public class AddNewProductCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        Paginator.transferPageToSession(req);
        String productName = req.getParameter("productName");
        BigDecimal cost = RequestProcessor.getBigDecimalFromRequest(req, "cost");
        String description = req.getParameter("description");
        String imageFileName = uploadImage(req);
        String imagePath = "images/" + imageFileName;
        if (isValidData(productName, cost) && imageFileName != null) {
            Product product = new Product();
            product.setName(productName);
            product.setCost(cost);
            product.setDescription(description);
            product.setProductImgPath(imagePath);
            product.setActive(true);
            ProductService.createProduct(product);
        }
        return ConfigurationManger.getProperty("page.productManagementRedirect");
    }

    private boolean isValidData(String productName, BigDecimal cost) {
        ProductValidator validator = new ProductValidator();
        return validator.isValidCost(cost)
                && validator.isValidProductName(productName);

    }

    private String uploadImage(HttpServletRequest req) {
        try {
            String fileName = UUID.randomUUID().toString() + ".jpg";
            return ImageProcessor.uploadFile(req.getPart("image"), fileName);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
            return null;
        }
    }
}
