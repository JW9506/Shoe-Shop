
create table t_customer (
  id int AUTO_INCREMENT primary key,
  name varchar(255) not null,
  city varchar(255) not null,
  email varchar(255) not null,
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);
create table t_order (
  id int AUTO_INCREMENT primary key,
  customer_id int not null,
  details varchar(255) not null,
  payment_status ENUM('PAID', 'UNPAID') not null,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  foreign key (customer_id) references t_customer(id)
);

CREATE TABLE t_orderitem (
  id INT AUTO_INCREMENT PRIMARY KEY,
  product_id INT,
  order_id INT,
  quantity INT,
  total_price DECIMAL(10, 2),
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  FOREIGN KEY (order_id) REFERENCES t_order(id)
);

insert into t_customer (name, city, email, created_at, updated_at) values ('Jack', 'Egypt','Jack@example.com', '2023-06-30', '2023-06-30');
insert into t_customer (name, city, email, created_at, updated_at) values ('Kacy', 'Dubai' ,'kacy@example.com', '2023-06-30', '2023-06-30');
insert into t_customer (name, city, email, created_at, updated_at) values ('Alex', 'London', 'alex@example.com', '2023-06-30', '2023-06-30');
insert into t_customer (name, city, email, created_at, updated_at) values ('Maria', 'New York', 'maria@example.com', '2023-06-30', '2023-06-30');
insert into t_customer (name, city, email, created_at, updated_at) values ('Sofia', 'Berlin', 'sofia@example.com', '2023-06-30', '2023-06-30');
insert into t_customer (name, city, email, created_at, updated_at) values ('Liam', 'Paris', 'liam@example.com', '2023-06-30', '2023-06-30');
insert into t_customer (name, city, email, created_at, updated_at) values ('Emma', 'Tokyo', 'emma@example.com', '2023-06-30', '2023-06-30');

insert into t_order (customer_id, details, payment_status, created_at, updated_at) values (1, 'this is some order details', 'UNPAID', '2023-06-30', '2023-06-30');
insert into t_order (customer_id, details, payment_status, created_at, updated_at) values (3, 'Order for electronics', 'PAID', '2023-06-30', '2023-06-30');
insert into t_order (customer_id, details, payment_status, created_at, updated_at) values (4, 'Fashion items order', 'UNPAID', '2023-06-30', '2023-06-30');
insert into t_order (customer_id, details, payment_status, created_at, updated_at) values (5, 'Books and stationery', 'PAID', '2023-06-30', '2023-06-30');
insert into t_order (customer_id, details, payment_status, created_at, updated_at) values (6, 'Sports equipment purchase', 'UNPAID', '2023-06-30', '2023-06-30');
insert into t_order (customer_id, details, payment_status, created_at, updated_at) values (7, 'Groceries and essentials', 'PAID', '2023-06-30', '2023-06-30');

insert into t_orderitem (product_id, order_id, quantity, total_price, created_at, updated_at) values (1, 1, 2, 100.00, '2023-07-01 10:05:00', '2023-07-01 10:05:00');
insert into t_orderitem (product_id, order_id, quantity, total_price, created_at, updated_at) values (2, 2, 1, 50.00, '2023-07-01 11:15:00', '2023-07-01 11:15:00');
insert into t_orderitem (product_id, order_id, quantity, total_price, created_at, updated_at) values (3, 3, 3, 150.00, '2023-07-01 12:30:00', '2023-07-01 12:30:00');

