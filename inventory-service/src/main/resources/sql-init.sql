CREATE TABLE t_sku (
  id INT AUTO_INCREMENT PRIMARY KEY,
  product_id INT NOT NULL UNIQUE,
  stock_count INT NOT NULL,
  price DECIMAL(10, 2) NOT NULL,
  sales INT NOT NULL
);

INSERT INTO t_sku (product_id, stock_count, price, sales) VALUES (1, 100, 50.00, 0);
INSERT INTO t_sku (product_id, stock_count, price, sales) VALUES (2, 150, 60.00, 0);
INSERT INTO t_sku (product_id, stock_count, price, sales) VALUES (3, 200, 70.00, 0);
INSERT INTO t_sku (product_id, stock_count, price, sales) VALUES (4, 250, 80.00, 0);
INSERT INTO t_sku (product_id, stock_count, price, sales) VALUES (5, 300, 90.00, 0);
INSERT INTO t_sku (product_id, stock_count, price, sales) VALUES (6, 350, 100.00, 0);
INSERT INTO t_sku (product_id, stock_count, price, sales) VALUES (7, 400, 110.00, 0);
INSERT INTO t_sku (product_id, stock_count, price, sales) VALUES (8, 450, 120.00, 0);
INSERT INTO t_sku (product_id, stock_count, price, sales) VALUES (9, 500, 130.00, 0);
