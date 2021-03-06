package com.epam.hplus.controller.commands.impl.authorized.admin;

import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.controller.commands.util.Paginator;
import com.epam.hplus.model.service.UserService;
import com.epam.hplus.util.resources.ConfigurationManger;
import jakarta.servlet.http.HttpServletRequest;

import static com.epam.hplus.util.constants.Context.REQUEST_USER;

public class DeleteUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String username = req.getParameter(REQUEST_USER);
        UserService.deleteUser(username);
        Paginator.transferPageToSession(req);
        return ConfigurationManger.getProperty("page.userManagementRedirect");
    }
}
