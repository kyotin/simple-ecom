CREATE TABLE product
(
    id       int auto_increment primary key,
    name     varchar(500),
    description varchar(500),
    thumbnail varchar(500),
    price double,
    quantity int,
    created_date timestamp default now(),
    updated_date timestamp default now()
);

CREATE TABLE img (
    id int auto_increment primary key,
    link varchar(500),
    created_date timestamp default now(),
    updated_date timestamp default now()
);
Alter table img add column name varchar(100);

CREATE TABLE category (
    id int auto_increment primary key ,
    name varchar(100),
    parent_id int,
    created_date timestamp default now(),
    updated_date timestamp default now(),
    FOREIGN KEY (parent_id) REFERENCES category(id)
);

CREATE TABLE product_img (
    product_id int,
    img_id int,
    primary key (product_id, img_id)
);

CREATE TABLE product_category (
    category_id int,
    product_id int,
    primary key (category_id, product_id)
);

INSERT INTO category(name) VALUE ('CAR');
INSERT INTO category(name, parent_id) SELECT 'TOYOTA', id FROM category where name = 'CAR';
INSERT INTO category(name, parent_id) SELECT 'MAZDA', id FROM category where name = 'CAR';
INSERT INTO category(name, parent_id) SELECT 'KIA', id FROM category where name = 'CAR';

INSERT INTO img(link, name) VALUE ('https://kiamotorsvietnam.com.vn//storage/soluto/kiamorning-3.png', 'morning-1');
INSERT INTO img(link, name) VALUE ('https://kiamotorsvietnam.com.vn//storage/product/new-kiamorning/z2273367431618-f9958824d79e162e123299cce0b20115-1.png', 'morning-2');
INSERT INTO img(link, name) VALUE ('https://kiamotorsvietnam.com.vn//storage/kiasoluto/kiasoluto-20190917-10093591-3.png', 'soluto-1');
INSERT INTO img(link, name) VALUE ('https://kiamotorsvietnam.com.vn//storage/kiacerato/cerato-profile-01-2-resize2.jpg', 'soluto-2');
INSERT INTO img(link, name) VALUE ('https://kiamotorsvietnam.com.vn//storage/seltos/lineup/trangden-l-2.png', 'seltos-1');
INSERT INTO img(link, name) VALUE ('https://mazdamotors.vn/media/he3g2zjv/all-new-mazda3_pc_1.jpg', 'mazda3-1');
INSERT INTO img(link, name) VALUE ('https://mazdamotors.vn/media/1fgd11jg/all-new-mazda3_pc_2.jpg', 'mazda3-2');
INSERT INTO img(link, name) VALUE ('https://mazdamotors.vn/media/oi0pj1fj/new-mazda-cx-5-pc-3.jpg', 'mazdacx5-1');
INSERT INTO img(link, name) VALUE ('https://mazdamotors.vn/media/qgxfptbh/new-mazda-cx-5-pc-2.jpg', 'mazdacx5-2');
INSERT INTO img(link, name) VALUE ('https://www.toyota.com.vn/data/tmv-albums/17/q4oqkr.jpg', 'fortuner-1');
INSERT INTO img(link, name) VALUE ('https://www.toyota.com.vn/data/news/8570/_1/rcgmj1.jpg', 'rush-1');
INSERT INTO img(link, name) VALUE ('https://www.toyota.com.vn/data/news/8570/_1/qz1gc4.jpg', 'rush-2');
INSERT INTO img(link, name) VALUE ('https://www.toyota.com.vn/data/news/8570/_1/kbl3al.jpg', 'rush-3');
INSERT INTO img(link, name) VALUE ('https://www.toyota.com.vn/data/news/8570/_1/ns1s0q.jpg', 'rush-4');

INSERT INTO product(name, description, thumbnail, price, quantity) VALUES ('KIA Morning', 'Small hatchback car', 'https://kiamotorsvietnam.com.vn//storage/soluto/kiamorning-3.png', 22000, 100);
INSERT INTO product(name, description, thumbnail, price, quantity) VALUES ('KIA Soluto', 'Small sedan car', 'https://kiamotorsvietnam.com.vn//storage/kiasoluto/kiasoluto-20190917-10093591-3.png', 23000, 100);
INSERT INTO product(name, description, thumbnail, price, quantity) VALUES ('KIA Seltos', 'Small SUV car', 'https://kiamotorsvietnam.com.vn//storage/seltos/lineup/trangden-l-2.png', 32000, 100);
INSERT INTO product(name, description, thumbnail, price, quantity) VALUES ('Mazda 3', 'Medium sedan car', 'https://mazdamotors.vn/media/1fgd11jg/all-new-mazda3_pc_2.jpg', 30000, 100);
INSERT INTO product(name, description, thumbnail, price, quantity) VALUES ('Mazda CX5', '5 seats SUV car', 'https://mazdamotors.vn/media/qgxfptbh/new-mazda-cx-5-pc-2.jpg', 42000, 100);
INSERT INTO product(name, description, thumbnail, price, quantity) VALUES ('TOYOTA Rush', 'Small SUV car', 'https://www.toyota.com.vn/data/news/8570/_1/ns1s0q.jpg',35000, 100);

INSERT INTO product_img(product_id, img_id) SELECT p.id, i.id FROM product p, img i WHERE p.name LIKE '%morning%' and i.name LIKE ('%morning%');
INSERT INTO product_img(product_id, img_id) SELECT p.id, i.id FROM product p, img i WHERE p.name LIKE '%Soluto%' and i.name LIKE ('%soluto%');
INSERT INTO product_img(product_id, img_id) SELECT p.id, i.id FROM product p, img i WHERE p.name LIKE '%Seltos%' and i.name LIKE ('%seltos%');
INSERT INTO product_img(product_id, img_id) SELECT p.id, i.id FROM product p, img i WHERE p.name LIKE '%Mazda 3%' and i.name LIKE ('%mazda3%');
INSERT INTO product_img(product_id, img_id) SELECT p.id, i.id FROM product p, img i WHERE p.name LIKE '%Mazda CX5%' and i.name LIKE ('%mazda-cx-5%');
INSERT INTO product_img(product_id, img_id) SELECT p.id, i.id FROM product p, img i WHERE p.name LIKE '%TOYOTA Rush%' and i.name LIKE ('rush%');

INSERT INTO product_category(category_id, product_id) SELECT c.id, p.id FROM category c, product p WHERE c.name = 'KIA' and p.name LIKE 'KIA%';
INSERT INTO product_category(category_id, product_id) SELECT c.id, p.id FROM category c, product p WHERE c.name = 'MAZDA' and p.name LIKE 'Mazda%';
INSERT INTO product_category(category_id, product_id) SELECT c.id, p.id FROM category c, product p WHERE c.name = 'TOYOTA' and p.name LIKE 'TOYOTA%';
