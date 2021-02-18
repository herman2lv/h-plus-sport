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

@WebServlet(urlPatterns="/addProduct")
public class AddProductServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		List<String> cart = (ArrayList<String>) session.getAttribute("cart");
		if (cart == null) {
			cart = new ArrayList<>();
		}
		cart.add(req.getParameter("product"));
		session.setAttribute("cart", cart);
		String searchString = (String) session.getAttribute("searchString");
		DbConnectionConfig config = Servlets.getDbConfig(getServletContext());
		List<Product> products = new Dao().searchProducts(config, searchString);
		req.setAttribute("products", products);
		req.setAttribute("cartSize", cart.size());
		req.getRequestDispatcher("/search.jsp").forward(req, resp);
	}
}
