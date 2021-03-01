package com.epam.hplus.util.resources;

import java.util.ResourceBundle;

public class MessageManager {
    private MessageManager() {
    }

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("messages");

    public static String getMessage(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}
