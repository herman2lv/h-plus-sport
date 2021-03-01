package com.epam.hplus.controller.filters;

import com.epam.hplus.model.beans.User;
import com.epam.hplus.util.resources.ConfigurationManger;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.epam.hplus.util.constants.Context.SESSION_USER_ROLE;

@WebFilter("/controller")
public class AdminFilter implements Filter {
    private List<String> restrictedActions;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        restrictedActions = new ArrayList<>(Arrays.asList(
                "user_management",
                "delete_user",
                "delete_product",
                "make_customer",
                "make_manager",
                "product_management"));
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String action = req.getParameter("command");
        if (isRestrictedAction(action, request)) {
            req.getRequestDispatcher(ConfigurationManger.getProperty("page.index"))
                    .forward(request, res);
            return;
        }
        chain.doFilter(request, res);
    }

    private boolean isRestrictedAction(String action, HttpServletRequest request) {
        return action != null
                && isRestrictedResource(action)
                && userIsNotAdmin(request);
    }

    private boolean isRestrictedResource(String action) {
        for (String restrictedAction : restrictedActions) {
            if (action.equalsIgnoreCase(restrictedAction)) {
                return true;
            }
        }
        return false;
    }

    private boolean userIsNotAdmin(HttpServletRequest request) {
        return (int) request.getSession().getAttribute(SESSION_USER_ROLE) != User.ROLE_ADMIN;
    }
}
