package com.epam.hplus.controller.commands;

import com.epam.hplus.beans.Product;
import com.epam.hplus.resources.ConfigurationManger;
import com.epam.hplus.service.Search;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static com.epam.hplus.constants.Context.REQUEST_PRODUCTS;
import static com.epam.hplus.constants.Context.REQUEST_SEARCH;
import static com.epam.hplus.constants.Context.SESSION_SEARCH_STRING;

public class SearchCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String searchString = req.getParameter(REQUEST_SEARCH);
        if (searchString == null) {
            searchString = "";
        }
        HttpSession session = req.getSession();
        session.setAttribute(SESSION_SEARCH_STRING, searchString);
        List<Product> products = Search.search(searchString);
        req.setAttribute(REQUEST_PRODUCTS, products);
        return ConfigurationManger.getProperty("page.search");
    }
}