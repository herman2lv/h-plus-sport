package com.epam.hplus.model.dao.Impl;

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
import static com.epam.hplus.util.constants.Database.PRODUCTS_TABLE;

public class ProductDaoJdbc implements ProductDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDaoJdbc.class);
    private static final ProductDaoJdbc INSTANCE = new ProductDaoJdbc();
    private static final String SELECT_ALL_FROM = "SELECT * FROM ";
    private static final String WHERE = " WHERE ";
    private static final String EQUALS = " = ";
    private static final String QUESTION_MARK = "?";
    private static final String LIKE = " like ";
    private static final String DELETE_FROM = "DELETE FROM ";
    private static final String COMA = ", ";
    private static final String SET = " SET ";
    private static final String UPDATE = "UPDATE ";
    private static final int UPDATE_PRODUCT_NAME_INDEX = 1;
    private static final int UPDATE_PRODUCT_IMG_INDEX = 2;
    private static final int UPDATE_PRODUCT_COST_INDEX = 3;
    private static final int UPDATE_PRODUCT_DESCRIPTION_INDEX = 4;
    private static final int UPDATE_PRODUCT_ACTIVE_INDEX = 5;
    private static final int UPDATE_PRODUCT_ID_INDEX = 6;
    private static final String AND = " AND ";
    private static final String TRUE = " TRUE ";

    private ProductDaoJdbc() {
    }

    public static ProductDaoJdbc getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean updateProduct(Product product) {
        String query = UPDATE + PRODUCTS_TABLE + SET
                + PRODUCTS_PRODUCT_NAME + EQUALS + QUESTION_MARK + COMA
                + PRODUCTS_IMAGE_PATH + EQUALS + QUESTION_MARK + COMA
                + PRODUCTS_COST + EQUALS + QUESTION_MARK + COMA
                + PRODUCTS_DESCRIPTION + EQUALS + QUESTION_MARK + COMA
                + PRODUCTS_ACTIVE + EQUALS + QUESTION_MARK
                + WHERE + PRODUCTS_PRODUCT_ID + EQUALS + QUESTION_MARK;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
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
    public List<Product> searchProducts(String searchString) {
        List<Product> products = new ArrayList<>();
        String query = SELECT_ALL_FROM + PRODUCTS_TABLE + WHERE + PRODUCTS_PRODUCT_NAME
                + LIKE + QUESTION_MARK + AND + PRODUCTS_ACTIVE + EQUALS + TRUE;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            String searchPattern = "%" + searchString + "%";
            statement.setString(1, searchPattern);
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
    public Product getProductById(int id) {
        Product product = null;
        String query = SELECT_ALL_FROM + PRODUCTS_TABLE
                + WHERE + PRODUCTS_PRODUCT_ID + EQUALS + QUESTION_MARK;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
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
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        String query = SELECT_ALL_FROM + PRODUCTS_TABLE
                + WHERE + PRODUCTS_ACTIVE + EQUALS + TRUE;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                products.add(createInstanceOfProduct(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return products;
    }
}
