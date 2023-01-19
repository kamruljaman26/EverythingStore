-- Product Table
-- private int ID;
-- private String category;
-- private String artist;
-- private String album;
-- private String genre;
-- private String SKU;
-- private double price;
-- private int quantity;
create table product
(
    id       integer primary key autoincrement,
    category varchar(30),
    artist   varchar(50),
    album    varchar(50),
    genre    varchar(50),
    sku      varchar(50) unique not null,
    price    int,
    quantity int
);

-- Address Table
-- private int id;
-- private String houseNo;
-- private String addressLine1;
-- private String addressLine2;
-- private String country;
-- private String postcode;
create table address
(
    id             integer primary key autoincrement,
    house_no       varchar(20),
    address_line_1 varchar(255),
    address_line_2 varchar(255),
    country        varchar(100),
    post_code      varchar(100)
);

-- Customer Table
-- private int customerID;
-- private String customerForename;
-- private String customerSurname;
-- private Address customerAddress;
-- private String customerTelNo;
create table customer
(
    customer_id        integer primary key autoincrement,
    customer_fore_name varchar(100),
    customer_sur_name  varchar(100),
    customer_address   int,
    customer_tel_no    varchar(100),
    foreign key (customer_address) references address (id)
);

-- Cart Table
-- private int cartProductID;
-- private Product product;
-- private int quantity;
create table cart
(
    cart_id    integer primary key autoincrement,
    product_id int unique not null,
    quantity   int        not null,
    foreign key (quantity) references product (id)
);

-- Order Table
-- private int order_id;
-- private Customer customer;
-- private ArrayList<OrderProduct> products;
create table order_
(
    order_id    integer primary key autoincrement,
    customer_id int not null,
    foreign key (customer_id) references customer (customer_id)
);

-- Order Products Table
-- private int orderProductID;
-- private int orderID;
-- private Product product;
-- private int quantity;
create table order_product
(
    order_product_id integer primary key autoincrement,
    order_id         int not null,
    product_id       int not null,
    quantity         int,
    foreign key (order_id) references order_ (order_id),
    foreign key (product_id) references product (id)
);