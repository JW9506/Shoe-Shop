create table t_categories (
    category_id int AUTO_INCREMENT primary key,
    description text,
    name varchar(255) not null,
    parent_category_id INT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (parent_category_id) REFERENCES t_categories(category_id)
);

create table t_product (
    id int AUTO_INCREMENT primary key,
    parent_category_id int not null,
    name varchar(255) not null,
    description text,
    original_price DECIMAL(10, 2),
    sale_price DECIMAL(10, 2),
    rating int,
    is_in_stock boolean,
    brand varchar(255),
    image_url varchar(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (parent_category_id) REFERENCES t_categories(category_id)
);

-- Insert parent category
INSERT INTO t_categories (name, description) VALUES ('Running Shoes', 'All types of running shoes.');

-- Insert sub-t_categories
INSERT INTO t_categories (name, description, parent_category_id) VALUES ('Road Running', 'Shoes for road running', (SELECT category_id FROM t_categories WHERE name = 'Running Shoes'));
INSERT INTO t_categories (name, description, parent_category_id) VALUES ('Trail Running', 'Shoes for trail running', (SELECT category_id FROM t_categories WHERE name = 'Running Shoes'));

-- -- Insert sub-sub-t_categories
INSERT INTO t_categories (name, description, parent_category_id) VALUES ('Mens Road Running', 'Road running shoes for men', (SELECT category_id FROM t_categories WHERE name = 'Road Running'));
INSERT INTO t_categories (name, description, parent_category_id) VALUES ('Womens Road Running', 'Road running shoes for women', (SELECT category_id FROM t_categories WHERE name = 'Road Running'));
INSERT INTO t_categories (name, description, parent_category_id) VALUES ('Mens Trail Running', 'Trail running shoes for men', (SELECT category_id FROM t_categories WHERE name = 'Trail Running'));
INSERT INTO t_categories (name, description, parent_category_id) VALUES ('Womens Trail Running', 'Trail running shoes for women', (SELECT category_id FROM t_categories WHERE name = 'Trail Running'));

insert into t_product (parent_category_id, name, description, original_price, sale_price, rating, is_in_stock, brand, image_url) values (4, 'Nike', 'Super fast Nike', 23.99, NULL, 5, true, 'Nike', 'https://i.ibb.co/ByYrksp/placehold-shoe.png');
insert into t_product (parent_category_id, name, description, original_price, sale_price, rating, is_in_stock, brand, image_url) values (5, 'Adidas', 'Lightweight Adidas', 45.99, NULL, 4, true, 'Adidas', 'https://i.ibb.co/ByYrksp/placehold-shoe.png');
insert into t_product (parent_category_id, name, description, original_price, sale_price, rating, is_in_stock, brand, image_url) values (6, 'Reebok', 'Comfortable Reebok', 39.99, NULL, 3, true, 'Reebok', 'https://i.ibb.co/ByYrksp/placehold-shoe.png');
insert into t_product (parent_category_id, name, description, original_price, sale_price, rating, is_in_stock, brand, image_url) values (2, 'Converse', 'Stylish Converse', 54.99, NULL, 5, true, 'Converse', 'https://i.ibb.co/ByYrksp/placehold-shoe.png');
insert into t_product (parent_category_id, name, description, original_price, sale_price, rating, is_in_stock, brand, image_url) values (7, 'New Balance', 'Durable New Balance', 65.00, NULL, 4, true, 'New Balance', 'https://i.ibb.co/ByYrksp/placehold-shoe.png');
insert into t_product (parent_category_id, name, description, original_price, sale_price, rating, is_in_stock, brand, image_url) values (3, 'Puma', 'High performance Puma', 49.99, NULL, 3, true, 'Puma', 'https://i.ibb.co/ByYrksp/placehold-shoe.png');
insert into t_product (parent_category_id, name, description, original_price, sale_price, rating, is_in_stock, brand, image_url) values (4, 'Asics', 'Reliable Asics', 59.99, NULL, 5, true, 'Asics', 'https://i.ibb.co/ByYrksp/placehold-shoe.png');
insert into t_product (parent_category_id, name, description, original_price, sale_price, rating, is_in_stock, brand, image_url) values (5, 'Nike', 'Innovative Nike', 79.99, NULL, 4, true, 'Nike', 'https://i.ibb.co/ByYrksp/placehold-shoe.png');
insert into t_product (parent_category_id, name, description, original_price, sale_price, rating, is_in_stock, brand, image_url) values (6, 'Adidas', 'Sporty Adidas', 67.99, NULL, 3, true, 'Adidas', 'https://i.ibb.co/ByYrksp/placehold-shoe.png');
