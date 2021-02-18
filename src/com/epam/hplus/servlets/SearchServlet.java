package com.epam.hplus.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.epam.hplus.beans.DbConnectionConfig;
import com.epam.hplus.beans.Product;
import com.epam.hplus.dao.Dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String searchString = req.getParameter("search");
		HttpSession session = req.getSession();
		session.setAttribute("searchString", searchString);
		DbConnectionConfig config = Servlets.getDbConfig(getServletContext());
		List<Product> products = new Dao().searchProducts(config, searchString);
		List<String> cart = (ArrayList<String>) session.getAttribute("cart");
		int cartSize = 0;
		if (cart != null) {
			cartSize = cart.size();
		}
		req.setAttribute("cartSize", cartSize);
		req.setAttribute("products", products);
		req.getRequestDispatcher("search.jsp").forward(req, resp);
	}
}
