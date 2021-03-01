package com.epam.hplus.controller.commands.factory;

import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.controller.commands.enums.CommandEnum;
import com.epam.hplus.controller.commands.EmptyCommand;
import com.epam.hplus.util.resources.MessageManager;
import jakarta.servlet.http.HttpServletRequest;

public class CommandFactory {
    private CommandFactory() {
    }

    public static Command defineCommand(HttpServletRequest req) {
        String action = req.getParameter("command");
        Command command = new EmptyCommand();
        if (action == null || action.isEmpty()) {
            return command;
        }
        try {
            command = CommandEnum.valueOf(action.toUpperCase()).getCommand();
        } catch (IllegalArgumentException e) {
            req.setAttribute("wrongAction", command
                                            + MessageManager.getMessage("msg.wrongAction"));
        }
        return command;
    }
}
