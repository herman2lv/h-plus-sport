package com.epam.hplus.model.dao.impl;

import com.epam.hplus.model.beans.Product;
import com.epam.hplus.model.dao.ProductDao;
import com.epam.hplus.model.pool.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.epam.hplus.util.constants.Database.PRODUCTS_ACTIVE;
import static com.epam.hplus.util.constants.Database.PRODUCTS_COST;
import static com.epam.hplus.util.constants.Database.PRODUCTS_DESCRIPTION;
import static com.epam.hplus.util.constants.Database.PRODUCTS_IMAGE_PATH;
import static com.epam.hplus.util.constants.Database.PRODUCTS_PRODUCT_ID;
import static com.epam.hplus.util.constants.Database.PRODUCTS_PRODUCT_NAME;

public class ProductDaoJdbc implements ProductDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDaoJdbc.class);
    private static final ProductDaoJdbc INSTANCE = new ProductDaoJdbc();
    private static final String SELECT_ALL_PRODUCTS =
            "SELECT * FROM products WHERE active = TRUE LIMIT ?, ?";
    private static final int SELECT_ALL_LIMIT_CURRENT_INDEX = 1;
    private static final int SELECT_ALL_LIMIT_ON_PAGE_INDEX = 2;
    private static final String COUNT_PRODUCTS =
            "SELECT COUNT(*) FROM products WHERE active = TRUE";
    private static final String COUNT_SEARCH_RESULTS =
            "SELECT COUNT(*) FROM products WHERE product_name LIKE ? AND active = TRUE";
    private static final String SELECT_PRODUCT_BY_ID =
            "SELECT * FROM products WHERE product_id = ?";
    private static final String SELECT_PRODUCTS_LIKE =
            "SELECT * FROM products WHERE product_name LIKE ? AND active = TRUE LIMIT ?, ?";
    private static final int SEARCH_PATTERN_INDEX = 1;
    private static final int SELECT_SEARCH_LIMIT_CURRENT_INDEX = 2;
    private static final int SELECT_SEARCH_LIMIT_ON_PAGE_INDEX = 3;
    private static final String UPDATE_PRODUCT = "UPDATE products SET product_name = ?, "
            + "image_path = ?, cost = ?, description = ?, active = ? WHERE product_id = ?";
    private static final int UPDATE_PRODUCT_NAME_INDEX = 1;
    private static final int UPDATE_PRODUCT_IMG_INDEX = 2;
    private static final int UPDATE_PRODUCT_COST_INDEX = 3;
    private static final int UPDATE_PRODUCT_DESCRIPTION_INDEX = 4;
    private static final int UPDATE_PRODUCT_ACTIVE_INDEX = 5;
    private static final int UPDATE_PRODUCT_ID_INDEX = 6;
    private static final String INSERT_PRODUCT = "INSERT INTO products "
            + "(product_name, image_path, cost, description, active) "
            + "VALUES (?, ?, ?, ?, ?)";
    protected static final String ANY_CHAR = "%";
    protected static final int INVALID_COUNT = -1;
    protected static final int INVALID_ID = -1;

    private ProductDaoJdbc() {
    }

    public static ProductDaoJdbc getInstance() {
        return INSTANCE;
    }

    @Override
    public int createProduct(Product product) {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PRODUCT)) {
            statement.setString(UPDATE_PRODUCT_NAME_INDEX, product.getName());
            statement.setString(UPDATE_PRODUCT_IMG_INDEX, product.getProductImgPath());
            statement.setBigDecimal(UPDATE_PRODUCT_COST_INDEX, product.getCost());
            statement.setString(UPDATE_PRODUCT_DESCRIPTION_INDEX, product.getDescription());
            statement.setBoolean(UPDATE_PRODUCT_ACTIVE_INDEX, product.isActive());
            if (statement.executeUpdate() > 0) {
                try (ResultSet keys = statement.getGeneratedKeys()) {
                    return keys.getInt(1);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return INVALID_ID;
    }

    @Override
    public boolean updateProduct(Product product) {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT)) {
            statement.setString(UPDATE_PRODUCT_NAME_INDEX, product.getName());
            statement.setString(UPDATE_PRODUCT_IMG_INDEX, product.getProductImgPath());
            statement.setBigDecimal(UPDATE_PRODUCT_COST_INDEX, product.getCost());
            statement.setString(UPDATE_PRODUCT_DESCRIPTION_INDEX, product.getDescription());
            statement.setBoolean(UPDATE_PRODUCT_ACTIVE_INDEX, product.isActive());
            statement.setLong(UPDATE_PRODUCT_ID_INDEX, product.getProductId());
            if (statement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public List<Product> searchProducts(String searchString, int currentIndex, int itemsOnPage) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCTS_LIKE)) {
            String searchPattern = ANY_CHAR + searchString + ANY_CHAR;
            statement.setString(SEARCH_PATTERN_INDEX, searchPattern);
            statement.setInt(SELECT_SEARCH_LIMIT_CURRENT_INDEX, currentIndex);
            statement.setInt(SELECT_SEARCH_LIMIT_ON_PAGE_INDEX, itemsOnPage);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(createInstanceOfProduct(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return products;
    }

    @Override
    public int countSearchResults(String searchString) {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_SEARCH_RESULTS)) {
            String searchPattern = ANY_CHAR + searchString + ANY_CHAR;
            statement.setString(1, searchPattern);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return INVALID_COUNT;
    }

    @Override
    public Product getProductById(int id) {
        Product product = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    product = createInstanceOfProduct(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return product;
    }

    private Product createInstanceOfProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setProductId(resultSet.getLong(PRODUCTS_PRODUCT_ID));
        product.setName(resultSet.getString(PRODUCTS_PRODUCT_NAME));
        product.setProductImgPath(resultSet.getString(PRODUCTS_IMAGE_PATH));
        product.setCost(resultSet.getBigDecimal(PRODUCTS_COST));
        product.setDescription(resultSet.getString(PRODUCTS_DESCRIPTION));
        product.setActive(resultSet.getBoolean(PRODUCTS_ACTIVE));
        return product;
    }


    @Override
    public List<Product> getProducts(int currentIndex, int itemsOnPage) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PRODUCTS)) {
            statement.setInt(SELECT_ALL_LIMIT_CURRENT_INDEX, currentIndex);
            statement.setInt(SELECT_ALL_LIMIT_ON_PAGE_INDEX, itemsOnPage);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(createInstanceOfProduct(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return products;
    }

    @Override
    public int countProducts() {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(COUNT_PRODUCTS)) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return INVALID_COUNT;
    }
}
