package com.epam.hplus.controller.filters;

import com.epam.hplus.model.beans.User;
import com.epam.hplus.util.resources.ConfigurationManger;
import com.epam.hplus.util.resources.LogManager;
import com.epam.hplus.util.resources.MessageManager;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import static com.epam.hplus.util.constants.Context.REQUEST_ERROR_CODE;
import static com.epam.hplus.util.constants.Context.REQUEST_ERROR_MESSAGE;
import static com.epam.hplus.util.constants.Context.SESSION_USER_ROLE;

@WebFilter("/controller")
public class UserRoleFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleFilter.class);
    private static final String ACTION = "command";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String action = req.getParameter(ACTION);
        if (action != null && isRestrictedAction(action, request)) {
            LOGGER.info(LogManager.getMessage("log.unauthorized"),
                    request.getRequestURI(), action);
            req.setAttribute(REQUEST_ERROR_CODE,
                    MessageManager.getMessage("msg.errorCode403"));
            req.setAttribute(REQUEST_ERROR_MESSAGE,
                    MessageManager.getMessage("msg.errorMessage403"));
            req.getRequestDispatcher(ConfigurationManger.getProperty("page.error"))
                    .forward(req, res);
            return;
        }
        chain.doFilter(request, res);
    }

    private boolean isRestrictedAction(String action, HttpServletRequest request) {
        return (isRestrictedResource(action, RestrictedActions.getAdminOnlyActions())
                && userIsNotInRole(request, User.ROLE_ADMIN))
                || (isRestrictedResource(action, RestrictedActions.getManagerOnlyActions())
                && userIsNotInRole(request, User.ROLE_MANAGER));
    }

    private boolean isRestrictedResource(String action, List<String> actions) {
        for (String restrictedAction : actions) {
            if (action.equalsIgnoreCase(restrictedAction)) {
                return true;
            }
        }
        return false;
    }

    private boolean userIsNotInRole(HttpServletRequest request, int userRole) {
        return (int) request.getSession().getAttribute(SESSION_USER_ROLE) != userRole;
    }
}
