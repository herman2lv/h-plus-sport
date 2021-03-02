package com.epam.hplus.controller.commands.impl;

import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.util.resources.ConfigurationManger;
import com.epam.hplus.util.resources.MessageManager;
import jakarta.servlet.http.HttpServletRequest;

import static com.epam.hplus.util.constants.Context.REQUEST_LANGUAGE;
import static com.epam.hplus.util.constants.Context.SESSION_LANGUAGE;

public class LanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String chosenLanguage = req.getParameter(REQUEST_LANGUAGE);
        req.getSession().setAttribute(SESSION_LANGUAGE, chosenLanguage);
        MessageManager.setLocale(chosenLanguage);
        return ConfigurationManger.getProperty("page.index");
    }
}
