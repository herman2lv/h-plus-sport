package com.epam.hplus.resources;

import java.util.ResourceBundle;

public class ConfigurationManger {
    private ConfigurationManger() {
    }

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("config");

    public static String getProperty(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}
