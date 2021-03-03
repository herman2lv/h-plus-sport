package com.epam.hplus.controller.commands.util;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.hplus.util.constants.Context.REQUEST_PAGE;

public class Paginator {
    private static final Logger LOGGER = LoggerFactory.getLogger(Paginator.class);
    public static final int ITEMS_ON_PAGE = 5;

    private Paginator() {
    }

    public static int countCurrentIndex(int currentPage) {
        if (currentPage < 1) {
            return 0;
        }
        return (currentPage - 1) * ITEMS_ON_PAGE;
    }

    public static int countNumberOfPages(int numberOfItems) {
        if (numberOfItems < 0) {
            return 0;
        }
        int lastPage = numberOfItems % ITEMS_ON_PAGE > 0 ? 1 : 0;
        return numberOfItems / ITEMS_ON_PAGE + lastPage;
    }

    public static void transferPageToSession(HttpServletRequest req) {
        int currentPage = RequestProcessor.getIntFromRequest(req, REQUEST_PAGE);
        if (currentPage == 0) {
            currentPage = 1;
        }
        req.getSession().setAttribute(REQUEST_PAGE, currentPage);
    }

    public static Integer getCurrentPage(HttpServletRequest req) {
        Integer currentPage = RequestProcessor.getIntFromRequest(req, REQUEST_PAGE);
        if (currentPage == 0) {
            try {
                currentPage = (Integer) req.getSession().getAttribute(REQUEST_PAGE);
                if (currentPage == null) {
                    currentPage = 1;
                }
            } catch (ClassCastException e) {
                LOGGER.error(e.getMessage(), e);
                currentPage = 1;
            }
        }
        return currentPage;
    }
}
