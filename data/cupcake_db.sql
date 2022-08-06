BEGIN TRANSACTION;

DROP TABLE IF EXISTS product_customer, product, customer;

CREATE TABLE product (
                         product_id serial,
                         name varchar(40) UNIQUE NOT NULL,
                         inventory int,
                         price decimal(19,4),
                         CONSTRAINT PK_product PRIMARY KEY (product_id)
);

CREATE TABLE customer (
                          customer_id serial,
                          fname varchar(20) not null,
                          lname varchar(20) not null,
                          birthday date not null,
                          username varchar(20) unique not null,
                          password varchar(50) not null,
                          constraint PK_customer primary key (customer_id)
);

CREATE TABLE product_customer (
                                    transaction_id serial,
                                  product_id int not null,
                                  customer_id int not null,
                                  purchase_time timestamp not null default current_timestamp,
                                  constraint PK_product_customer primary key (product_id, customer_id),
                                  constraint FK_product_id foreign key (product_id) references product(product_id),
                                  constraint FK_customer_id foreign key (customer_id) references customer(customer_id)
);

insert into product (name, inventory, price) values('Red Velvet', 50, 3.50);
insert into product (name, inventory, price) values('Lemon', 50, 5.00);
insert into product (name, inventory, price) values('FunFetti', 50, 3.00);
insert into product (name, inventory, price) values('Chocolate', 50, 3.50);

insert into customer (fname, lname, birthday, username, password) values('Jonathan','Ward','1994-05-23','jward','password');
insert into customer (fname, lname, birthday, username, password) values('Tyler','Bettilyon','1994-05-23','tbettilyon','password');
insert into customer (fname, lname, birthday, username, password) values('Brian','Steffes','1994-05-23','bsteffes','password');

insert into product_customer (product_id, customer_id, purchase_time) values(1, 1, '2022-08-03 12:14:10');
insert into product_customer (product_id, customer_id, purchase_time) values(2, 1, '2022-08-03 12:14:10');
insert into product_customer (product_id, customer_id, purchase_time) values(3, 1, now());
insert into product_customer (product_id, customer_id) values(3, 2);


commit;



