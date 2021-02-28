DROP database if exists hplus;

CREATE database hplus;

USE hplus;

CREATE table products (
    product_id int primary key auto_increment, 
    product_name varchar(50), 
    image_path varchar(100),
    cost decimal (6,2),
    description text
);

CREATE table users (
    username varchar(50) primary key not null,
    password varchar(50) not null, 
    first_name varchar(50), 
    last_name varchar(50),
    date_of_birth date,
    activity varchar(100),
    user_role int not null
);
     
CREATE table orders (
    order_id int primary key auto_increment, 
    username varchar(50) not null,
    order_date date, 
    order_cost decimal(6,2) not null,
    confirmation_status BOOLEAN,
    FOREIGN KEY (username) REFERENCES users(username)
);

CREATE table orders_details (
    order_id int,
    product_id int,
    number_of_products int not null,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

INSERT into users values('admin','admin','Andrew','Webber','1986-04-26','Exercise in Gym', 1);
INSERT into users values('user','user','Mark','Johnson','1991-12-30','Playing a sport', 3);
INSERT into users values('luka','1234','Alexander','Showshenko','1954-08-30','Playing a sport', 3);
INSERT into users values('herman','1234','Herman','House','1999-09-15','Exercise in Gym', 2);

INSERT into orders (username, order_date, order_cost, confirmation_status)
    values('admin', '2020-03-07', 55.23, true),
        ('admin', '2021-01-02', 52.23, true),
        ('user', '2027-01-09', 25.01, true),
        ('luka', '2021-01-12', 33.99, true),
        ('herman', '2021-01-17', 82.99, true),
        ('luka', '2021-02-02', 38.89, true),
        ('user', '2021-02-21', 27.00, true),
        ('admin', '2021-02-22', 75.00, true);

INSERT into products (product_name, image_path, cost, description)
    values("Mineralwater Blueberry","images/mineralwater-blueberry.jpg", 2.14, "Freshing mineral water"),
        ("Mineralwater Lemonlime","images/mineralwater-lemonlime.jpg", 2.32, "Freshing mineral water"),
        ("Mineralwater Orange","images/mineralwater-orange.jpg", 3.14, "Freshing mineral water"),
        ("Mineralwater Peach","images/mineralwater-peach.jpg", 1.89, "Freshing mineral water"),
        ("Mineralwater Raspberry","images/mineralwater-raspberry.jpg", 5.22, "Freshing mineral water"),
        ("Mineralwater Strawberry","images/mineralwater-strawberry.jpg", 4.11, "Freshing mineral water"),
        ("Proteinbar Chocolate","images/proteinbar-chocolate.jpg", 7.39, "Nutritious protein bar"),
        ("Proteinbar Lemon","images/proteinbar-lemon.jpg", 8.88, "Nutritious protein bar"),
        ("Proteinbar Peanutbutter","images/proteinbar-peanutbutter.jpg", 7.99, "Nutritious protein bar"),
        ("Vitamin A","images/vitamin-a.jpg", 22.82, "Healthy vitamins"),
        ("Vitamin B complex","images/vitamin-bcomplex.jpg", 27.22, "Healthy vitamins"),
        ("Vitamin Calcium","images/vitamin-c.jpg", 26.11, "Healthy vitamins");

INSERT into orders_details 
    values(1, 1, 2),
        (1, 2, 1),
        (2, 3, 5),
        (2, 2, 2),
        (2, 4, 3),
        (3, 8, 2),
        (4, 9, 1),
        (5, 10, 2),
        (6, 11, 1),
        (6, 12, 3),
        (7, 3, 1),
        (8, 7, 3),
        (8, 3, 8),
        (8, 9, 1),
        (8, 10, 1);

