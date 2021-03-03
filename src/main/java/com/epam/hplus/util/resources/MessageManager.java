package com.epam.hplus.util.resources;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    protected static final String BUNDLE_NAME = "messages";

    private MessageManager() {
    }

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);


    public static String getMessage(String key) {
        return resourceBundle.getString(key);
    }

    public static void setLocale(String language) {
        resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, new Locale(language));
    }
}
