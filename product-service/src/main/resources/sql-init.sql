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
