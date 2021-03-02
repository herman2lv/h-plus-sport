package com.epam.hplus.controller.commands.impl.authorized.admin;

import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.controller.commands.util.Paginator;
import com.epam.hplus.model.beans.Product;
import com.epam.hplus.model.service.ProductService;
import com.epam.hplus.util.resources.ConfigurationManger;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static com.epam.hplus.controller.commands.util.Paginator.ITEMS_ON_PAGE;
import static com.epam.hplus.util.constants.Context.REQUEST_COMMAND;
import static com.epam.hplus.util.constants.Context.REQUEST_CURRENT_INDEX;
import static com.epam.hplus.util.constants.Context.REQUEST_NUMBER_OF_ITEMS;
import static com.epam.hplus.util.constants.Context.REQUEST_NUMBER_OF_PAGES;
import static com.epam.hplus.util.constants.Context.REQUEST_PAGE;
import static com.epam.hplus.util.constants.Context.REQUEST_PAGINATOR_COMMAND;
import static com.epam.hplus.util.constants.Context.REQUEST_PRODUCTS;

public class ProductManagementCommand implements Command {

    @Override
    public String execute(HttpServletRequest req) {
        Integer currentPage = Paginator.getCurrentPage(req);
        req.setAttribute(REQUEST_PAGE, currentPage);
        int currentIndex = Paginator.countCurrentIndex(currentPage);
        req.setAttribute(REQUEST_CURRENT_INDEX, currentIndex);
        List<Product> products = ProductService.getProducts(currentIndex, ITEMS_ON_PAGE);
        req.setAttribute(REQUEST_PRODUCTS, products);
        int numberOfProducts = ProductService.countProducts();
        req.setAttribute(REQUEST_NUMBER_OF_ITEMS, numberOfProducts);
        int numberOfPages = Paginator.countNumberOfPages(numberOfProducts);
        req.setAttribute(REQUEST_NUMBER_OF_PAGES, numberOfPages);
        req.setAttribute(REQUEST_PAGINATOR_COMMAND, req.getParameter(REQUEST_COMMAND));
        return ConfigurationManger.getProperty("page.productManagement");
    }
}
