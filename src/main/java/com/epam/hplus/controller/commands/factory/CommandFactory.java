package com.epam.hplus.controller.commands.factory;

import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.controller.commands.enums.CommandEnum;
import com.epam.hplus.controller.commands.impl.EmptyCommand;
import com.epam.hplus.util.resources.MessageManager;
import jakarta.servlet.http.HttpServletRequest;

import static com.epam.hplus.util.constants.Context.REQUEST_COMMAND;
import static com.epam.hplus.util.constants.Context.REQUEST_ERROR_CODE;
import static com.epam.hplus.util.constants.Context.REQUEST_ERROR_MESSAGE;

public class CommandFactory {
    private CommandFactory() {
    }

    public static Command defineCommand(HttpServletRequest req) {
        String action = req.getParameter(REQUEST_COMMAND);
        Command command = new EmptyCommand();
        if (action == null || action.isEmpty()) {
            sendPageNotFound(req);
            return command;
        }
        try {
            command = CommandEnum.valueOf(action.toUpperCase()).getCommand();
        } catch (IllegalArgumentException e) {
            sendPageNotFound(req);
        }
        return command;
    }

    private static void sendPageNotFound(HttpServletRequest req) {
        req.setAttribute(REQUEST_ERROR_CODE,
                MessageManager.getMessage("msg.errorCode404"));
        req.setAttribute(REQUEST_ERROR_MESSAGE,
                MessageManager.getMessage("msg.errorMessage404"));
    }
}
