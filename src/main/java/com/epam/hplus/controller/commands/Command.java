package com.epam.hplus.controller.commands;

import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest req);
}
