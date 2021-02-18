package com.epam.hplus.servlets;

import java.io.IOException;
import java.util.List;

import com.epam.hplus.beans.DbConnectionConfig;
import com.epam.hplus.beans.Product;
import com.epam.hplus.dao.Dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String searchString = req.getParameter("search");
		DbConnectionConfig config = Servlets.getDbConfig(getServletContext());
		List<Product> products = new Dao().searchProducts(config, searchString);
		req.setAttribute("products", products);
		req.getRequestDispatcher("search.jsp").forward(req, resp);
//		String pagePath = req.getServletContext().getRealPath("/search.html");
//		String resultPage = getFileAsString(pagePath);
//		resultPage = replacePlaceholders(resultPage, products);
//		resp.getWriter().append(resultPage);
	}
	
//	private String getFileAsString(String filePath) {
//		StringBuilder fileString = new StringBuilder();
//		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//			String line;
//			while ((line = reader.readLine()) != null) {
//				fileString.append(line);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return fileString.toString();
//	}
//	
//	private String replacePlaceholders(String fileString, List<Product> products) {
//		fileString = MessageFormat.format(fileString, products.get(0).getProductImgPath(),
//				products.get(1).getProductImgPath(), products.get(2).getProductImgPath(), 
//				products.get(0).getName(), products.get(1).getName(), 
//				products.get(2).getName(), 0);
//		return fileString;
//	}
}
