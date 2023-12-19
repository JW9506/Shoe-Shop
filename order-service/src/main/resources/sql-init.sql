
create table t_customer (
  id int AUTO_INCREMENT primary key,
  name varchar(255) not null,
  city varchar(255) not null,
  email varchar(255) not null,
  created_at date,
  updated_at date
);
create table t_order (
  id int AUTO_INCREMENT primary key,
  customer_id int not null,
  details varchar(255) not null,
  created_at date,
  updated_at date,
  foreign key (customer_id) references t_customer(id)
);

insert into t_customer (name, city, email, created_at, updated_at) values ('jacky', 'egypt','foobar@aa.com', '2023-06-30', '2023-06-30');
insert into t_customer (name, city, email, created_at, updated_at) values ('kacy', 'dubai' ,'foobar@aa.com', '2023-06-30', '2023-06-30');
insert into t_order (customer_id, details, created_at, updated_at) values (1, 'this is some order details', '2023-06-30', '2023-06-30');
