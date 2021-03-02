package com.epam.hplus.controller.commands.util;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.hplus.util.constants.Context.REQUEST_PAGE;

public class Paginator {
    private static final Logger LOGGER = LoggerFactory.getLogger(Paginator.class);
    public static final int ITEMS_ON_PAGE = 2;

    private Paginator() {
    }

    public static int countCurrentIndex(int currentPage) {
        return (currentPage - 1) * ITEMS_ON_PAGE;
    }

    public static int countNumberOfPages(int numberOfItems) {
        int lastPage = numberOfItems % ITEMS_ON_PAGE > 0 ? 1 : 0;
        return numberOfItems / ITEMS_ON_PAGE + lastPage;
    }

    public static void transferPageToSession(HttpServletRequest req) {
        int currentPage = RequestProcessor.getIntFromRequest(req, REQUEST_PAGE);
        LOGGER.info("CURRENT PAGE = {}", currentPage);
        if (currentPage == 0) {
            currentPage = 1;
        }
        req.getSession().setAttribute(REQUEST_PAGE, currentPage);
        LOGGER.info("CURRENT PAGE SET TO SESSION = {}", currentPage);
    }
}
