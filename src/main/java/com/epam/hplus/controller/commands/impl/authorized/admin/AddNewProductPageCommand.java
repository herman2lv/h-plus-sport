package com.epam.hplus.controller.commands.impl.authorized.admin;

import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.util.resources.ConfigurationManger;
import jakarta.servlet.http.HttpServletRequest;

public class AddNewProductPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return ConfigurationManger.getProperty("page.addNewProduct");
    }
}
