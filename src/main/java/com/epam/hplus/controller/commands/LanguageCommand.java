package com.epam.hplus.controller.commands;

import com.epam.hplus.resources.ConfigurationManger;
import jakarta.servlet.http.HttpServletRequest;

import static com.epam.hplus.constants.Context.REQUEST_LANGUAGE;
import static com.epam.hplus.constants.Context.SESSION_LANGUAGE;

public class LanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String chosenLanguage = req.getParameter(REQUEST_LANGUAGE);
        req.getSession().setAttribute(SESSION_LANGUAGE, chosenLanguage);
        return ConfigurationManger.getProperty("page.index");
    }
}
