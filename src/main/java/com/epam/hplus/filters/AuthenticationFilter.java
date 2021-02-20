package com.epam.hplus.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    private static final String USERNAME = "username";
    private static final String ANY_CHARS_BETWEEN_SLASHES_REGEX = "/.*[^/].*/";
    private static final String ANY_CHAR_REGEX = ".*";
    private static final String LOGIN_JSP = "/login.jsp";
    private static final String ERROR_MESSAGE = "You are not authorized. Please, login";
    private static final String ERROR = "error";
    private List<String> restrictedResources;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        restrictedResources = new ArrayList<String>(Arrays.asList(
                "profile",
                "orderHistory"));
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        if (isRestrictedResource(uri) && userIsNotAthorized(request)) {
            req.setAttribute(ERROR, ERROR_MESSAGE);
            req.getRequestDispatcher(LOGIN_JSP).forward(request, res);
        }
        chain.doFilter(request, res);
    }

    private boolean isRestrictedResource(String uri) {
        for (String restrictedUri : restrictedResources) {
            if (uri.matches(ANY_CHARS_BETWEEN_SLASHES_REGEX + restrictedUri + ANY_CHAR_REGEX)) {
                return true;
            }
        }
        return false;
    }

    private boolean userIsNotAthorized(HttpServletRequest request) {
        return request.getSession().getAttribute(USERNAME) == null;
    }
}