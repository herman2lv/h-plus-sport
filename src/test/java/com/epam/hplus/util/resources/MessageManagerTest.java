package com.epam.hplus.util.resources;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class MessageManagerTest {
    private final static String KEY = "msg.unauthorized";
    private final static String RU_MESSAGE =
            "Вы не авторизованы. Пожалуйста, войдите под своим логином/зарегистрируйтесь";
    private final static String EN_MESSAGE = "You are not authorized. Please, login";
    private final static String RUSSIAN = "ru";
    private final static String ENGLISH = "en";

    @Test
    public void getMessageDefaultLocaleTest() {
        MessageManager.setLocale(Locale.getDefault().toString());
        Assert.assertEquals(EN_MESSAGE, MessageManager.getMessage(KEY));
    }

    @Test
    public void getMessageEnglishSystemLocaleTest() {
        MessageManager.setLocale(Locale.ENGLISH.toString());
        Assert.assertEquals(EN_MESSAGE, MessageManager.getMessage(KEY));
    }

    @Test
    public void getMessageRussianLocaleTest() {
        MessageManager.setLocale(RUSSIAN);
        Assert.assertEquals(RU_MESSAGE, MessageManager.getMessage(KEY));
    }

    @Test
    public void getMessageEnglishLocaleTest() {
        MessageManager.setLocale(ENGLISH);
        Assert.assertEquals(EN_MESSAGE, MessageManager.getMessage(KEY));
    }

    @Test
    public void getMessageChangeLocaleTest() {
        for (int i = 0; i < 3; i++) {
            MessageManager.setLocale(ENGLISH);
            Assert.assertEquals(EN_MESSAGE, MessageManager.getMessage(KEY));
            MessageManager.setLocale(RUSSIAN);
            Assert.assertEquals(RU_MESSAGE, MessageManager.getMessage(KEY));
        }
    }
}
