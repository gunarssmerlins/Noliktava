-- DROP DATABASE noliktava;

CREATE DATABASE noliktava;

USE noliktava;

CREATE TABLE users (
userID int NOT NULL AUTO_INCREMENT,
user VARCHAR(64),
password VARCHAR(64),
PRIMARY KEY (userID)
);

CREATE TABLE customers (
customerID int NOT NULL AUTO_INCREMENT,
companyName VARCHAR(128),
contactPerson VARCHAR(128),
phone VARCHAR(16),
PRIMARY KEY (customerID)
);

CREATE TABLE products (
productID int NOT NULL AUTO_INCREMENT,
productName VARCHAR(128),
pcsperbox INT,
customerID INT,
FOREIGN KEY (customerID) REFERENCES customers(customerID),
PRIMARY KEY (productID)
);

CREATE TABLE stock (
stockOpID int NOT NULL AUTO_INCREMENT, 
userID int,
productID int,
changes int,
chdate datetime,
FOREIGN KEY (productID) REFERENCES products(productID), 
FOREIGN KEY (userID) REFERENCES users(userID),
PRIMARY KEY (stockOpID)
);

CREATE TABLE querrylog (
querryID int NOT NULL AUTO_INCREMENT, 
querry VARCHAR(512),
userID int,
qdate datetime,
PRIMARY KEY (querryID),
FOREIGN KEY (userID) REFERENCES users(userID)
);

-- DROP TABLE stock;
-- DROP TABLE user;
-- DROP TABLE products;
-- DROP TABLE customers;
-- DROP TABLE projectmanagers;