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
			req.setAttribute("error", "You are not authorized. Please, login");
			req.getRequestDispatcher("/login.jsp").forward(request, res);
		}
		chain.doFilter(request, res);
	}
	
	private boolean isRestrictedResource(String uri) {
		for (String restrictedUri : restrictedResources) {
			if (uri.matches("/.*[^/].*/" + restrictedUri + ".*")) {
				return true;
			}
		}
		return false;
	}
	
	private boolean userIsNotAthorized(HttpServletRequest request) {
		return request.getSession().getAttribute("username") == null;
	}

}
