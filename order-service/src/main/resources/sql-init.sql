
create table t_customer (
  id int primary key,
  name varchar(255),
  city varchar(255)
);
create table t_order (
  id int primary key,
  customer_id int,
  details varchar(255),
  foreign key (customer_id) references t_customer(id)
);

insert into t_customer values (1, 'jacky', 'egypt');
insert into t_customer values (2, 'kacy', 'dubai');
insert into t_order values (1, 1, 'this is some order details');
