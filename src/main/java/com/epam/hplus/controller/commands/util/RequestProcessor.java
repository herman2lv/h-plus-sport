package com.epam.hplus.controller.commands.util;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class RequestProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestProcessor.class);

    private RequestProcessor() {
    }

    public static int getIntFromRequest(HttpServletRequest req, String paramName) {
        int number = 0;
        try {
            number = Integer.parseInt(req.getParameter(paramName));
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return number;
    }

    public static BigDecimal getBigDecimalFromRequest(HttpServletRequest req, String paramName) {
        BigDecimal number = BigDecimal.ZERO;
        try {
            number = BigDecimal.valueOf(Double.parseDouble(req.getParameter(paramName)));
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return number;
    }
}
