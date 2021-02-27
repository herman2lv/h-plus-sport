package com.epam.hplus.controller.commands;

import com.epam.hplus.resources.ConfigurationManger;
import jakarta.servlet.http.HttpServletRequest;

public class RegisterUserPage implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return ConfigurationManger.getProperty("page.register");
    }
}
