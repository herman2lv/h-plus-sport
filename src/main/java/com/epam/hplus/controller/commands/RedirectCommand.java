package com.epam.hplus.controller.commands;

import com.epam.hplus.resources.ConfigurationManger;
import jakarta.servlet.http.HttpServletRequest;

public class RedirectCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        req.getSession().invalidate();
        return ConfigurationManger.getProperty("page.github");
    }
}
