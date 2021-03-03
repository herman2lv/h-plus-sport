DROP database if exists hplus;

CREATE database hplus;

USE hplus;

CREATE table products
(
    product_id   int primary key auto_increment,
    product_name varchar(50),
    image_path   varchar(100),
    cost         decimal(6, 2),
    description  text,
    active       boolean
);

CREATE table users
(
    username      varchar(50) primary key not null,
    password      varchar(50)             not null,
    first_name    varchar(50),
    last_name     varchar(50),
    date_of_birth date,
    activity      varchar(100),
    user_role     int                     not null,
    active        boolean
);

CREATE table orders
(
    order_id            int primary key auto_increment,
    username            varchar(50)   not null,
    order_date          date,
    order_cost          decimal(6, 2) not null,
    confirmation_status boolean,
    FOREIGN KEY (username) REFERENCES users (username)
);

CREATE table orders_details
(
    order_id           int,
    product_id         int,
    number_of_products int not null,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES orders (order_id),
    FOREIGN KEY (product_id) REFERENCES products (product_id)
);

INSERT into users
values ('admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', -- password=admin
        'Andrew', 'Webber', '1986-04-26', 'Exercise in Gym', 1, true);
INSERT into users
values ('user', '12dea96fec20593566ab75692c9949596833adc9', -- password=user
        'Mark', 'Johnson', '1991-12-30', 'Playing a sport', 3, true);
INSERT into users
values ('luka', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', -- password=1234
        'Alexander', 'Showshenko', '1954-08-30', 'Playing a sport', 2, true);
INSERT into users
values ('herman', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', -- password=1234
        'Herman', 'House', '1999-09-15', 'Exercise in Gym', 2, true);
INSERT into users
values ('boris', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', -- password=1234
        'June', 'Swift', '1979-08-06', 'Exercise in Gym', 2, true);
INSERT into users
values ('kitty', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', -- password=1234
        'Ann', 'Cruise', '1978-11-14', 'Playing a sport', 3, true);
INSERT into users
values ('maroon', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', -- password=1234
        'Tom', 'Hornet', '1969-12-22', 'Exercise in Gym', 3, true);
INSERT into users
values ('wissenbad', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', -- password=1234
        'Karen', 'Jakobson', '1982-06-26', 'Playing a sport', 3, true);
INSERT into users
values ('bechebe', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', -- password=1234
        'Elizabeth', 'Horeing', '1984-06-23', 'Exercise in Gym', 3, true);
INSERT into users
values ('candibober', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', -- password=1234
        'Manilla', 'Windsdor', '1998-04-08', 'Playing a sport', 3, true);
INSERT into users
values ('kandibober', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', -- password=1234
        'Cameron', 'Canterberry', '1991-09-05', 'Exercise in Gym', 3, true);
INSERT into users
values ('lolka', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', -- password=1234
        'Johanna', 'Fairystin', '1992-10-05', 'Playing a sport', 3, true);
INSERT into users
values ('native', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', -- password=1234
        'Rebekka', 'Kimanno', '1989-12-02', 'Exercise in Gym', 3, true);
INSERT into users
values ('youmotherfriend', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', -- password=1234
        'Camilla', 'Schwarzkopf', '1972-11-11', 'Exercise in Gym', 3, true);
INSERT into users
values ('letsdoit', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', -- password=1234
        'Bernard', 'Totenbrom', '1976-01-10', 'Playing a sport', 3, true);
INSERT into users
values ('camelcompact', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', -- password=1234
        'Nissan', 'Burstenster', '1965-05-12', 'Exercise in Gym', 3, true);
INSERT into users
values ('nairobi', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', -- password=1234
        'Lima', 'Hopkins', '1974-03-17', 'Exercise in Gym', 3, true);
INSERT into users
values ('yamaha', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', -- password=1234
        'Mandy', 'West', '1998-07-18', 'Playing a sport', 3, true);
INSERT into users
values ('mariupol', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', -- password=1234
        'John', 'Black', '1949-08-17', 'Exercise in Gym', 3, true);
INSERT into users
values ('lengsington', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', -- password=1234
        'Carrol', 'Miranst', '1978-11-12', 'Playing a sport', 2, true);
INSERT into users
values ('remarka', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', -- password=1234
        'Andrew', 'Lloyd', '1955-06-11', 'Exercise in Gym', 2, true);

INSERT into orders (username, order_date, order_cost, confirmation_status)
values ('admin', '2021-01-01', 45.23, true),
       ('admin', '2021-01-02', 52.23, true),
       ('user', '2027-01-09', 25.01, true),
       ('luka', '2021-01-12', 33.99, true),
       ('herman', '2021-01-17', 82.99, true),
       ('luka', '2021-02-02', 27.89, true),
       ('user', '2021-02-06', 55.35, true),
       ('boris', '2021-02-08', 103.67, true),
       ('lengsington', '2021-02-11', 75.72, true),
       ('remarka', '2021-02-12', 72.98, true),
       ('nairobi', '2021-02-13', 57.43, true),
       ('letsdoit', '2021-02-15', 54.67, true),
       ('boris', '2021-02-16', 46.32, true),
       ('user', '2021-02-17', 37.09, true),
       ('user', '2021-02-18', 17.04, true),
       ('candibober', '2021-02-18', 28.48, false),
       ('kandibober', '2021-02-19', 14.27, false),
       ('lolka', '2021-02-20', 35.99, false);

INSERT into products (product_name, image_path, cost, description, active)
values ('Mineralwater Blueberry', 'images/mineralwater-blueberry.jpg', 2.14,
        'Freshening mineral water with ultimate blueberry taste', true),
       ('Mineralwater Lemonlime', 'images/mineralwater-lemonlime.jpg', 2.32,
        'Crazy lemon and lime! You will like it!', true),
       ('Mineralwater Orange', 'images/mineralwater-orange.jpg', 3.14,
        'Freshening mineral water with classic orange taste. Even better than juice', true),
       ('Mineralwater Peach', 'images/mineralwater-peach.jpg', 1.89,
        'Freshening mineral water. Now with peach', true),
       ('Mineralwater Raspberry', 'images/mineralwater-raspberry.jpg', 5.22,
        'Freshening mineral water. Taste new raspberry and it will become your favourite!', true),
       ('Mineralwater Strawberry', 'images/mineralwater-strawberry.jpg', 4.11,
        'Freshening mineral water', true),
       ('Proteinbar Chocolate', 'images/proteinbar-chocolate.jpg', 7.39,
        'Nutritious protein bar balanced especially for adults', true),
       ('Proteinbar Lemon', 'images/proteinbar-lemon.jpg', 8.88,
        'Nutritious protein bar for professional athletes', true),
       ('Proteinbar Peanutbutter', 'images/proteinbar-peanutbutter.jpg', 7.99,
        'Nutritious protein bar for young athletes', true),
       ('Vitamin A', 'images/vitamin-a.jpg', 22.82,
        'Healthy vitamins for men 45+', true),
       ('Vitamin B complex', 'images/vitamin-bcomplex.jpg', 27.22,
        'Healthy vitamins. Multivitamin complex for all ages', true),
       ('Vitamin Calcium', 'images/vitamin-c.jpg', 26.11,
        'Healthy vitamins for professional athletes', true);

INSERT into orders_details
values (1, 1, 2),
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
       (9, 9, 1),
       (9, 6, 2),
       (9, 1, 2),
       (10, 12, 1),
       (10, 9, 4),
       (11, 2, 4),
       (11, 3, 4),
       (11, 5, 1),
       (11, 9, 1),
       (12, 8, 1),
       (12, 2, 2),
       (12, 1, 3),
       (13, 4, 4),
       (14, 6, 1),
       (14, 7, 2),
       (14, 11, 1),
       (15, 10, 3),
       (15, 8, 1),
       (15, 12, 2),
       (15, 7, 1),
       (16, 4, 2),
       (16, 3, 1),
       (17, 2, 6),
       (17, 11, 6),
       (18, 10, 1),
       (18, 7, 3),
       (18, 12, 1),
       (18, 11, 2),
       (18, 6, 3),
       (18, 4, 1);
