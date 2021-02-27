package com.epam.hplus.controller.commands;

public enum CommandEnum {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    HOME(new HomeCommand()),
    SEARCH(new SearchCommand()),
    PROFILE(new ProfileCommand()),
    ADDPRODUCT(new AddProductCommand()),
    REDIRECT(new RedirectCommand());

    private final Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
