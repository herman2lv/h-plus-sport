package com.epam.hplus.controller.filters;

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

import static com.epam.hplus.util.constants.Context.REQUEST_ERROR;
import static com.epam.hplus.util.constants.Context.SESSION_USERNAME;

@WebFilter(urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String action = req.getParameter("command");
        if (isRestrictedAction(action, request)) {
            LOGGER.info(LogManager.getMessage("log.unauthorized"),
                    request.getRequestURI(), action);
            req.setAttribute(REQUEST_ERROR, MessageManager.getMessage("msg.unauthorized"));
            req.getRequestDispatcher(
                    ConfigurationManger.getProperty("page.login")).forward(request, res);
            return;
        }
        chain.doFilter(request, res);
    }

    private boolean isRestrictedAction(String action, HttpServletRequest request) {
        return action != null
               && isRestrictedResource(action)
               && userIsNotAuthorized(request);
    }

    private boolean isRestrictedResource(String action) {
        for (String restrictedAction : RestrictedActions.getAuthenticatedUserOnlyActions()) {
            if (action.equalsIgnoreCase(restrictedAction)) {
                return true;
            }
        }
        return false;
    }

    private boolean userIsNotAuthorized(HttpServletRequest request) {
        return request.getSession().getAttribute(SESSION_USERNAME) == null;
    }
}
