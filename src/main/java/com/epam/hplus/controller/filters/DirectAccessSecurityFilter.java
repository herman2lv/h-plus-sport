package com.epam.hplus.controller.filters;

import com.epam.hplus.util.resources.MessageManager;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebFilter(urlPatterns = {"/jsp/*"})
public class DirectAccessSecurityFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(DirectAccessSecurityFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        LOGGER.info(MessageManager.getMessage("log.directAccess"), req.getRequestURI());
        resp.sendRedirect(req.getContextPath());
    }
}
