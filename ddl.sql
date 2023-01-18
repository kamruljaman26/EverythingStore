CREATE TABLE Persons
(
    PersonID  int,
    LastName  varchar(255),
    FirstName varchar(255),
    Address   varchar(255),
    City      varchar(255)
);
--         System.out.println("about to get data");
--         int ID = Integer.parseInt(parms.get("id"));
--         String category = parms.get("category");
--         String artist = parms.get("artist");
--         String album = parms.get("album");
--         String genre = parms.get("genre");
--         String sku = parms.get("sku");
--         int price = Integer.parseInt(parms.get("price"));
--         int quantity = Integer.parseInt(parms.get("quantity"));

create table Product(
    id int not null unique,
    category varchar(30),
    artist varchar(50),
    album varchar(50),
    genre varchar(50),
    sku varchar(50),
    price int,
    quantity int,
    primary key (id)
);

