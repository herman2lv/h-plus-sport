package com.epam.hplus.controller.commands;

public enum CommandEnum {
    LOGIN(new LoginCommand()), LOGOUT(new LogoutCommand());

    private final Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
