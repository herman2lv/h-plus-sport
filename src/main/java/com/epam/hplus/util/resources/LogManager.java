package com.epam.hplus.util.resources;

import java.util.ResourceBundle;

public class LogManager {
    private LogManager() {
    }

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("logs");

    public static String getMessage(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}
