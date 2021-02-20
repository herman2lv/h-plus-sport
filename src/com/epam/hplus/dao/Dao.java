package com.epam.hplus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.epam.hplus.beans.Order;
import com.epam.hplus.beans.Product;
import com.epam.hplus.beans.User;

public class Dao {
	private static final String IMAGE_PATH = "image_path";
	private static final String PRODUCT_NAME = "product_name";
	private static final String PRODUCT_ID = "product_id";

	public List<Product> searchProducts(Connection connection, String searchString) {
		List<Product> products = new ArrayList<>();
		try {
			String query = "SELECT * FROM products WHERE product_name like '%" 
					+ searchString + "%'";
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
	
	public int createUser(Connection connection, User user) {
		int rowsAffected = 0;
		try {
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
	
	public User getUserProfile(Connection connection, String username) {
		User user = null;
		try {
			String query = "SELECT * FROM users WHERE username = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet set = statement.executeQuery();
			if (set.next()) {
				String nameOriginalCase = set.getString("username");
				String password = set.getString("password");
				String firstName = set.getString("first_name");
				String lastName = set.getString("last_name");
				String activity = set.getString("activity");
				int age = set.getInt("age");
				user = new User(nameOriginalCase, password, firstName, lastName, activity, age);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public boolean validateUser(Connection connection, String username, String password) {
		try {
			String query = "SELECT * FROM users WHERE username = ? AND password = ?"
					+ " COLLATE utf8mb4_bin";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet set = statement.executeQuery();
			if (set.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Order> getOrdersOfUser(Connection connection, String username) {
		List<Order> orders = new ArrayList<>();
		try {
			String query = "SELECT * FROM orders WHERE user_name = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				int id = set.getInt("order_id");
				String product = set.getString("product_name");
				String productImgPath = set.getString("image_path");
				Date orderDate = new Date(set.getDate("order_date").getTime());
				orders.add(new Order(id, product, productImgPath, orderDate, username));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
}
