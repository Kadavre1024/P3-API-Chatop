CREATE DATABASE chatop_db;
USE chatop_db;
CREATE TABLE users (
id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
email VARCHAR(255),
name VARCHAR(255),
password VARCHAR(255),
created_at DATETIME(6),
updated_at DATETIME(6)
);	
CREATE TABLE rentals (
id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255),
surface FLOAT,
price FLOAT,
picture VARCHAR(255),
description VARCHAR(2000),
owner_id BIGINT NOT NULL,
created_at DATETIME(6),
updated_at DATETIME(6)
);
CREATE TABLE messages (
id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
rental_id BIGINT NOT NULL,
user_id BIGINT NOT NULL,
message VARCHAR(2000),
created_at DATETIME(6),
updated_at DATETIME(6)
);
CREATE UNIQUE INDEX users_index ON users (email);
ALTER TABLE messages ADD CONSTRAINT fk_rental_message_1 FOREIGN KEY (rental_id) REFERENCES rentals (id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE messages ADD CONSTRAINT fk_user_message_1 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE rentals ADD CONSTRAINT fk_user_rental_1 FOREIGN KEY (owner_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE;
INSERT INTO users (id, email, name, password, created_at, updated_at) VALUES (null, "Test1 TEST", "test1@test.com", "Test31!", "2023-07-22 16:59:03.814506", "2023-07-22 16:59:03.814506");
INSERT INTO users (id, email, name, password, created_at, updated_at) VALUES (null, "Test2 TEST", "test2@test.com", "Test32!", "2023-07-22 16:59:03.814506", "2023-07-22 16:59:03.814506");
INSERT INTO rentals (id, name, surface, price, picture, description, owner_id, created_at, updated_at) VALUES (null, "My House", 120.0, 300.0, "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg", "My tiny house is perfect", 1, "2023-07-22 16:59:03.814506", "2023-07-22 16:59:03.814506");
INSERT INTO messages (id, rental_id, user_id, message, created_at, updated_at) VALUES (null, 1, 2, "This is a beautiful house ! Can i rent it last week ?", "2023-07-22 16:59:03.814506", "2023-07-22 16:59:03.814506");