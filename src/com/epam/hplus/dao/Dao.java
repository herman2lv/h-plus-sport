package com.epam.hplus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.hplus.beans.DbConnectionConfig;
import com.epam.hplus.beans.Product;
import com.epam.hplus.beans.User;

public class Dao {
	private static final String IMAGE_PATH = "image_path";
	private static final String PRODUCT_NAME = "product_name";
	private static final String PRODUCT_ID = "product_id";

	public List<Product> searchProducts(DbConnectionConfig config, String searchString) {
		List<Product> products = new ArrayList<>();
		try {
			Connection connection = DbConnection.getConnectionToDatabase(config);
			String query = "SELECT * FROM products WHERE product_name like '%" + searchString + "%'";
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery(query);
			while (set.next()) {
				products.add(new Product(set.getLong(PRODUCT_ID), set.getString(PRODUCT_NAME), 
						set.getString(IMAGE_PATH)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public int createUser(DbConnectionConfig config, User user) {
		int rowsAffected = 0;
		try {
			Connection connection = DbConnection.getConnectionToDatabase(config);
			String query = "INSERT into users values (?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setInt(5, user.getAge());
			statement.setString(6, user.getActivity());
			rowsAffected = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}
	
	public String getTable(DbConnectionConfig config, String table) {
		String query = "SELECT * FROM ";
		switch (table) {
			case "users":
				query += "users";
				break;
			case "orders":
				query += "orders";
				break;
			default:
				table = "products";
				query += "products";
		}
		StringBuilder out = new StringBuilder();
		try {
			Connection connection = DbConnection.getConnectionToDatabase(config);
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery(query);
			out.append(table).append("\n");
			for (int i = 1; i <= set.getMetaData().getColumnCount(); i++) {
				out.append(String.format("%-25.25s |", set.getMetaData().getColumnLabel(i)));
			}
			out.append("\n");
			while (set.next()) {
				for (int i = 1; i <= set.getMetaData().getColumnCount(); i++) {
					out.append(String.format("%-25.25s |", set.getString(i)));
				}
				out.append("\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return out.toString();
	}
}
