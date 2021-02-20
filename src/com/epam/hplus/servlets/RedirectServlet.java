package com.epam.hplus.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/redirect")
public class RedirectServlet extends HttpServlet {
	private static final String GITHUB_LINK = "https://github.com/herman2lv/h-plus-sport";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.sendRedirect(GITHUB_LINK);
	}
}
