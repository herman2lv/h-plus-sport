package com.epam.hplus.controller;

import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.controller.commands.factory.CommandFactory;
import com.epam.hplus.util.resources.ConfigurationManger;
import com.epam.hplus.util.resources.MessageManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/controller")
@MultipartConfig(fileSizeThreshold = Controller.MB1,
        maxFileSize = Controller.MB10,
        maxRequestSize = Controller.MB100)
public class Controller extends HttpServlet {
    protected static final int MB1 = 1024 * 1024;
    protected static final int MB10 = 1024 * 1024 * 10;
    protected static final int MB100 = 1024 * 1024 * 100;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Command command = CommandFactory.defineCommand(req);
        String page = command.execute(req);
        if (page != null) {
            req.getRequestDispatcher(page).forward(req, resp);
        } else {
            page = ConfigurationManger.getProperty("page.index");
            req.getSession()
                    .setAttribute("nullPage", MessageManager.getMessage("msg.nullPage"));
            resp.sendRedirect(req.getContextPath() + page);
        }
    }
}
