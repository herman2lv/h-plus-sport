package com.epam.hplus.util.resources;

import java.util.ResourceBundle;

public class LogManager {
    protected static final String BUNDLE_NAME = "logs";

    private LogManager() {
    }

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    public static String getMessage(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}
