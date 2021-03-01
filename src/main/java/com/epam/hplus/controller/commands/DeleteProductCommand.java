package com.epam.hplus.controller.commands;

import com.epam.hplus.controller.commands.util.RequestProcessor;
import com.epam.hplus.model.service.ProductService;
import com.epam.hplus.util.resources.ConfigurationManger;
import jakarta.servlet.http.HttpServletRequest;

import static com.epam.hplus.util.constants.Context.REQUEST_PRODUCT;

public class DeleteProductCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        int productId = RequestProcessor.getIntFromRequest(req, REQUEST_PRODUCT);
        ProductService.deleteProduct(productId);
        return ConfigurationManger.getProperty("page.productManagementRedirect");
    }
}
