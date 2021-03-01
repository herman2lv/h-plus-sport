package com.epam.hplus.controller.commands;

import com.epam.hplus.model.beans.Product;
import com.epam.hplus.model.service.ProductService;
import com.epam.hplus.util.resources.ConfigurationManger;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static com.epam.hplus.util.constants.Context.REQUEST_PRODUCTS;

public class ProductManagementCommand implements Command {

    @Override
    public String execute(HttpServletRequest req) {
        List<Product> products = ProductService.getProducts();
        req.setAttribute(REQUEST_PRODUCTS, products);
        return ConfigurationManger.getProperty("page.productManagement");
    }
}
