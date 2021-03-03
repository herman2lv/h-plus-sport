package com.epam.hplus.controller.commands.impl.authorized.admin;

import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.controller.commands.util.Paginator;
import com.epam.hplus.controller.commands.util.RequestProcessor;
import com.epam.hplus.model.beans.Product;
import com.epam.hplus.model.service.ProductService;
import com.epam.hplus.util.resources.ConfigurationManger;
import jakarta.servlet.http.HttpServletRequest;

import static com.epam.hplus.util.constants.Context.REQUEST_PAGE;
import static com.epam.hplus.util.constants.Context.REQUEST_PRODUCT;

public class EditProductPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        Integer currentPage = Paginator.getCurrentPage(req);
        req.setAttribute(REQUEST_PAGE, currentPage);
        int productId = RequestProcessor.getIntFromRequest(req, REQUEST_PRODUCT);
        Product product = ProductService.getProduct(productId);
        req.setAttribute(REQUEST_PRODUCT, product);
        return ConfigurationManger.getProperty("page.editProduct");
    }
}
