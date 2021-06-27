CREATE DATABASE dev_nab_order CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE dev_nab_order;

CREATE TABLE buyer
(
    id           int auto_increment primary key,
    name         varchar(500),
    phone_number varchar(20),
    email        varchar(200),
    home_address varchar(200)
);

CREATE TABLE place_order
(
    id           int auto_increment primary key,
    status       varchar(100),
    total_price  double,
    order_by     int,
    created_date timestamp default now(),
    updated_date timestamp default now(),
    version      int       default 0,
    FOREIGN KEY (order_by) REFERENCES buyer(id)
);

CREATE TABLE order_product
(
    id         int auto_increment primary key,
    product_id int,
    order_id   int,
    name       varchar(500),
    price      double,
    quantity   int,
    FOREIGN KEY (order_id) REFERENCES place_order(id),
    CONSTRAINT UNIQUE (product_id, order_id)
);

INSERT INTO buyer(name, phone_number, email, home_address) VALUES('Tin Nguyen', '0908235566', 'nguyentrongtin89@gmail.com', 'EraTown');
INSERT INTO place_order(status, total_price, order_by) VALUES ('NEW', 22234.0, 1);
INSERT INTO order_product(product_id, order_id, name, price, quantity) VALUES (1, 1, 'KIA Morning', 22000, 1);
INSERT INTO order_product(product_id, order_id, name, price, quantity) VALUES (2, 1, 'KIA Soluto', 23000, 1);
INSERT INTO order_product(product_id, order_id, name, price, quantity) VALUES (4, 1, 'Mazda 3', 30000, 1);
