
create table t_customer (
  id int AUTO_INCREMENT primary key,
  name varchar(255),
  city varchar(255),
  email varchar(255)
);
create table t_order (
  id int AUTO_INCREMENT primary key,
  customer_id int,
  details varchar(255),
  foreign key (customer_id) references t_customer(id)
);

insert into t_customer (name, city, email) values ('jacky', 'egypt','foobar@aa.com');
insert into t_customer (name, city, email) values ('kacy', 'dubai' ,'foobar@aa.com');
insert into t_order (customer_id, details) values (1, 'this is some order details');
