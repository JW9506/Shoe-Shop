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
    price DECIMAL(10, 2),
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

insert into t_product (parent_category_id, name, description, price) values (4, 'Nike', 'Super fast Nike', 23.99);
insert into t_product (parent_category_id, name, description, price) values (3, 'Adidas', 'Super fast Adidas', 13.99);
insert into T_PRODUCT (PARENT_CATEGORY_ID , NAME ,DESCRIPTION ,PRICE ) values
(7,'Jordan', 'Super fast Jordan', 59.99);
insert into T_PRODUCT (PARENT_CATEGORY_ID, NAME, DESCRIPTION, PRICE) values (5, 'Adidas', 'Super fast Reebok', 89.99);
insert into T_PRODUCT (PARENT_CATEGORY_ID, NAME, DESCRIPTION, PRICE) values (4, 'Adidas', 'Lightweight Converse', 39.99);
insert into T_PRODUCT (PARENT_CATEGORY_ID, NAME, DESCRIPTION, PRICE) values (4, 'Converse', 'High performance Reebok', 69.99);
insert into T_PRODUCT (PARENT_CATEGORY_ID, NAME, DESCRIPTION, PRICE) values (7, 'Puma', 'Super fast Puma', 29.99);
insert into T_PRODUCT (PARENT_CATEGORY_ID, NAME, DESCRIPTION, PRICE) values (5, 'Nike', 'Lightweight Converse', 69.99);
insert into T_PRODUCT (PARENT_CATEGORY_ID, NAME, DESCRIPTION, PRICE) values (3, 'Puma', 'Lightweight Nike', 19.99);
insert into T_PRODUCT (PARENT_CATEGORY_ID, NAME, DESCRIPTION, PRICE) values (4, 'Puma', 'Lightweight Adidas', 89.99);
insert into T_PRODUCT (PARENT_CATEGORY_ID, NAME, DESCRIPTION, PRICE) values (6, 'Puma', 'Lightweight Asics', 59.99);
