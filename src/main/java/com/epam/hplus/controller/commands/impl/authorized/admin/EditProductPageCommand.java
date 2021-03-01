package com.epam.hplus.controller.commands.impl.authorized.admin;

import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.controller.commands.util.RequestProcessor;
import com.epam.hplus.model.beans.Product;
import com.epam.hplus.model.service.ProductService;
import com.epam.hplus.util.resources.ConfigurationManger;
import jakarta.servlet.http.HttpServletRequest;

import static com.epam.hplus.util.constants.Context.REQUEST_PRODUCT;

public class EditProductPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        int productId = RequestProcessor.getIntFromRequest(req, REQUEST_PRODUCT);
        Product product = ProductService.getProduct(productId);
        req.setAttribute("productId", product.getProductId());
        req.setAttribute("productName", product.getName());
        req.setAttribute("imagePath", product.getProductImgPath());
        req.setAttribute("productCost", product.getCost());
        req.setAttribute("description", product.getDescription());
        return ConfigurationManger.getProperty("page.editProduct");
    }
}
