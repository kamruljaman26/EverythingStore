-- Product Table
-- private int ID;
-- private String category;
-- private String artist;
-- private String album;
-- private String genre;
-- private String SKU;
-- private double price;
-- private int quantity;
create table Product
(
    id       int primary key autoincrement,
    category varchar(30),
    artist   varchar(50),
    album    varchar(50),
    genre    varchar(50),
    sku      varchar(50),
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
    id             int primary key autoincrement,
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
    customer_id        int primary key autoincrement,
    customer_fore_name varchar(100),
    customer_sur_name  varchar(100),
    customer_address   int,
    foreign key (customer_address) references address (id),
    customer_tel_no    varchar(100)
);

