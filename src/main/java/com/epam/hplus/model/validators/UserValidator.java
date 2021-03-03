package com.epam.hplus.model.validators;

import java.util.Date;

public class UserValidator {
    private static final String USERNAME_REGEX = "[a-zA-Z0-9_\\-.]{4,30}";
    private static final String PASSWORD_REGEX = "[a-zA-Zа-яА-я0-9_\\-\\p{Punct}]{8,40}";
    private static final String PERSONAL_NAME = "[a-zA-Zа-яА-Я-]{2,50}";
    private static final Date MAX_DATE = new Date(1262296800000L); //2010-01-01
    private static final Date MIN_DATE = new Date(-1577929800000L); //1920-01-01

    public boolean isValidUsername(String username) {
        return username.matches(USERNAME_REGEX);
    }

    public boolean isNotValidUsername(String username) {
        return !isValidUsername(username);
    }

    public boolean isValidPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    public boolean isNotValidPassword(String password) {
        return !isValidPassword(password);
    }

    public boolean isValidName(String personalName) {
        return personalName.matches(PERSONAL_NAME);
    }

    public boolean isNotValidName(String personalName) {
        return !isValidName(personalName);
    }

    public boolean isValidDate(Date date) {
        return date.compareTo(MIN_DATE) > 0 && date.compareTo(MAX_DATE) < 0;
    }

    public boolean isNotValidDate(Date date) {
        return !isValidDate(date);
    }
}
