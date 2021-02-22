package com.epam.hplus.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.epam.hplus.constants.Context.APP_REDIRECT_LINK;

@WebServlet(urlPatterns = "/redirect")
public class RedirectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String redirectLink = getServletContext().getInitParameter(APP_REDIRECT_LINK);
        resp.sendRedirect(redirectLink);
    }
}
