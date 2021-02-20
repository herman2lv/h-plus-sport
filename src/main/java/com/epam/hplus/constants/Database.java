package com.epam.hplus.constants;

public class Database {
    public static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String ORDERS_TABLE = "orders";
    public static final String ORDERS_ORDER_ID = "order_id";
    public static final String ORDERS_PRODUCT_NAME = "product_name";
    public static final String ORDERS_IMAGE_PATH = "image_path";
    public static final String ORDERS_ORDER_DATE = "order_date";
    public static final String ORDERS_USERNAME = "user_name";
    public static final String PRODUCTS_TABLE = "products";
    public static final String PRODUCTS_PRODUCT_NAME = "product_name";
    public static final String PRODUCTS_IMAGE_PATH = "image_path";
    public static final String PRODUCTS_PRODUCT_ID = "product_id";
    public static final String USERS_TABLE = "users";
    public static final String USERS_USERNAME = "username";
    public static final String USERS_PASSWORD = "password";
    public static final String USERS_FIRST_NAME = "first_name";
    public static final String USERS_LAST_NAME = "last_name";
    public static final String USERS_AGE = "age";
    public static final String USERS_ACTIVITY = "activity";
    public static final int USERS_USERNAME_INDEX = 1;
    public static final int USERS_PASSWORD_INDEX = 2;
    public static final int USERS_FIRST_NAME_INDEX = 3;
    public static final int USERS_LAST_NAME_INDEX = 4;
    public static final int USERS_AGE_INDEX = 5;
    public static final int USERS_ACTIVITY_INDEX = 6;

    private Database() {
    }
}