package com.epam.hplus.controller.commands;

import com.epam.hplus.model.beans.Product;
import com.epam.hplus.util.resources.ConfigurationManger;
import com.epam.hplus.model.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static com.epam.hplus.util.constants.Context.REQUEST_PRODUCTS;
import static com.epam.hplus.util.constants.Context.REQUEST_SEARCH;
import static com.epam.hplus.util.constants.Context.SESSION_SEARCH_STRING;

public class SearchCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String searchString = req.getParameter(REQUEST_SEARCH);
        if (searchString == null) {
            searchString = "";
        }
        HttpSession session = req.getSession();
        session.setAttribute(SESSION_SEARCH_STRING, searchString);
        List<Product> products = ProductService.searchProducts(searchString);
        req.setAttribute(REQUEST_PRODUCTS, products);
        return ConfigurationManger.getProperty("page.search");
    }
}
