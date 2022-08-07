BEGIN TRANSACTION;

DROP TABLE IF EXISTS product_customer, product, customer;

CREATE TABLE product (
                         product_id serial,
                         name varchar(40) UNIQUE NOT NULL,
                         url varchar(500),
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

insert into product (name, url, inventory, price) values('Red Velvet', 'https://www.instacart.com/image-server/466x466/filters:fill(FFF,true):format(webp)/www.instacart.com/assets/domains/product-image/file/large_6d04edb3-73f1-40f0-ad59-dba1d63ad8ff.jpg', 50, 3.50);
insert into product (name, url, inventory, price) values('Lemon', 'https://sweetfreedombakeshop.com/wp-content/uploads/IMG_2230.jpg', 50, 5.00);
insert into product (name, url, inventory, price) values('FunFetti', 'https://sweetfreedombakeshop.com/wp-content/uploads/Funfetti-Cupcake-Natural.png', 50, 3.00);
insert into product (name, url, inventory, price) values('Chocolate', 'https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcS3qR-haGF60BZWu9wR5AbTZrZU3YlXMrHdp2t2een5P_Ha0N2xQ-xB_BL2_QSZw_lmq7HY70FsmSXTg8spQlDE-ddny9GyNnN6dPKd-Wq6mzNnQ6ZtfiUjCQ&usqp=CAE', 50, 3.50);

insert into customer (fname, lname, birthday, username, password) values('Jonathan','Ward','1994-05-23','jward','password');
insert into customer (fname, lname, birthday, username, password) values('Tyler','Bettilyon','1994-05-23','tbettilyon','password');
insert into customer (fname, lname, birthday, username, password) values('Brian','Steffes','1994-05-23','bsteffes','password');

insert into product_customer (product_id, customer_id, purchase_time) values(1, 1, '2022-08-03 12:14:10');
insert into product_customer (product_id, customer_id, purchase_time) values(2, 1, '2022-08-03 12:14:10');
insert into product_customer (product_id, customer_id, purchase_time) values(3, 1, now());
insert into product_customer (product_id, customer_id) values(3, 2);


commit;



