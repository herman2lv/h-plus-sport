package com.epam.hplus.servlets;

import java.io.IOException;
import java.sql.Connection;

import com.epam.hplus.beans.User;
import com.epam.hplus.dao.Dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.hplus.constants.Context.APP_DB_CONNECTION;
import static com.epam.hplus.constants.Context.REQUEST_REGISTRATION_STATUS;
import static com.epam.hplus.constants.Context.REQUEST_ACTIVITY;
import static com.epam.hplus.constants.Context.REQUEST_AGE;
import static com.epam.hplus.constants.Context.REQUEST_FIRST_NAME;
import static com.epam.hplus.constants.Context.REQUEST_LAST_NAME;
import static com.epam.hplus.constants.Context.REQUEST_PASSWORD;
import static com.epam.hplus.constants.Context.REQUEST_USERNAME;
import static com.epam.hplus.constants.JspFiles.REGISTER_JSP;

@WebServlet(urlPatterns = "/register")
public class RegisterUserServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterUserServlet.class);
    private static final String SUCCESS_MESSAGE = "User registered successfully";
    private static final String ERROR_MESSAGE = "User wasn't registered, an error occurred";
    protected static final String LOG_UNSUCCESSFUL_REGISTRATION =
            "Unsuccessful attempt to register new user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(REGISTER_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = createInstanceOfUser(req);
        Connection connection = (Connection) getServletContext().getAttribute(APP_DB_CONNECTION);
        int rowsAffected = new Dao().createUser(connection, user);
        String registrationStatus;
        if (rowsAffected == 0) {
            registrationStatus = ERROR_MESSAGE;
            LOGGER.info(LOG_UNSUCCESSFUL_REGISTRATION);
        } else {
            registrationStatus = SUCCESS_MESSAGE;
        }
        req.setAttribute(REQUEST_REGISTRATION_STATUS, registrationStatus);
        req.getRequestDispatcher(REGISTER_JSP).forward(req, resp);
    }

    private User createInstanceOfUser(HttpServletRequest req) {
        String username = req.getParameter(REQUEST_USERNAME);
        String password = req.getParameter(REQUEST_PASSWORD);
        String firstName = req.getParameter(REQUEST_FIRST_NAME);
        String lastName = req.getParameter(REQUEST_LAST_NAME);
        String activity = req.getParameter(REQUEST_ACTIVITY);
        int age = Integer.valueOf(req.getParameter(REQUEST_AGE));
        return new User(username, password, firstName, lastName, activity, age);
    }
}
