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
public class ManagerFilter implements Filter {
    private List<String> restrictedActions;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        restrictedActions = new ArrayList<>(Arrays.asList(
                "order_management",
                "remove_order_by_manager",
                "reject_order",
                "approve_order"));
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
                && userIsNotManager(request);
    }

    private boolean isRestrictedResource(String action) {
        for (String restrictedAction : restrictedActions) {
            if (action.equalsIgnoreCase(restrictedAction)) {
                return true;
            }
        }
        return false;
    }

    private boolean userIsNotManager(HttpServletRequest request) {
        return (int) request.getSession().getAttribute(SESSION_USER_ROLE) != User.ROLE_MANAGER;
    }
}
