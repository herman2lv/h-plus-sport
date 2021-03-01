package com.epam.hplus.controller.commands.impl.authorized.admin;

import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.model.beans.User;
import com.epam.hplus.util.resources.ConfigurationManger;
import com.epam.hplus.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static com.epam.hplus.util.constants.Context.REQUEST_USERS;

public class UserManagementCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        List<User> users = UserService.getUsers();
        req.setAttribute(REQUEST_USERS, users);
        return ConfigurationManger.getProperty("page.userManagement");
    }
}
