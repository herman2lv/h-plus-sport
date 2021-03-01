package com.epam.hplus.controller.commands;

import com.epam.hplus.util.resources.ConfigurationManger;
import jakarta.servlet.http.HttpServletRequest;

import static com.epam.hplus.util.constants.Context.SESSION_LANGUAGE;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String chosenLanguage = (String) req.getSession().getAttribute(SESSION_LANGUAGE);
        req.getSession().invalidate();
        req.getSession().setAttribute(SESSION_LANGUAGE, chosenLanguage);
        return ConfigurationManger.getProperty("page.index");
    }
}
