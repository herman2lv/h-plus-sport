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
import java.util.ArrayList;
import java.util.List;

import static com.epam.hplus.constants.Database.PRODUCTS_COST;
import static com.epam.hplus.constants.Database.PRODUCTS_DESCRIPTION;
import static com.epam.hplus.constants.Database.PRODUCTS_IMAGE_PATH;
import static com.epam.hplus.constants.Database.PRODUCTS_PRODUCT_ID;
import static com.epam.hplus.constants.Database.PRODUCTS_PRODUCT_NAME;
import static com.epam.hplus.constants.Database.PRODUCTS_TABLE;

public class ProductDaoJdbc implements ProductDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDaoJdbc.class);
    private static final ProductDaoJdbc INSTANCE = new ProductDaoJdbc();
    private static final String SELECT_ALL_FROM = "SELECT * FROM ";
    private static final String WHERE = " WHERE ";
    private static final String EQUALS = " = ";
    private static final String QUESTION_MARK = "?";
    private static final String LIKE = " like ";

    private ProductDaoJdbc() {
    }

    public static ProductDaoJdbc getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Product> searchProducts(String searchString) {
        List<Product> products = new ArrayList<>();
        String query = SELECT_ALL_FROM + PRODUCTS_TABLE + WHERE + PRODUCTS_PRODUCT_NAME
                       + LIKE + QUESTION_MARK;
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
        return new Product(resultSet.getLong(PRODUCTS_PRODUCT_ID),
                resultSet.getString(PRODUCTS_PRODUCT_NAME),
                resultSet.getString(PRODUCTS_IMAGE_PATH),
                resultSet.getBigDecimal(PRODUCTS_COST),
                resultSet.getString(PRODUCTS_DESCRIPTION));
    }
}
