CREATE TABLE IF NOT EXISTS shop (
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(50) NOT NULL,  
     category_id INT NOT NULL,
     image_name VARCHAR(255),
     description VARCHAR(255) NOT NULL,
     price INT NOT NULL,
     holiday VARCHAR(225) NOT NULL,
     postal_code VARCHAR(50) NOT NULL,
     address VARCHAR(255) NOT NULL,
     phone_number VARCHAR(50) NOT NULL,
     opening_time TIME NOT NULL,
     closing_time TIME NOT NULL,
     created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
 );
 
 CREATE TABLE IF NOT EXISTS category (
 	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	name VARCHAR(50) NOT NULL,
 	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
 );
 
 CREATE TABLE IF NOT EXISTS roles (
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(50) NOT NULL
 );
 
 CREATE TABLE IF NOT EXISTS users (
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(50) NOT NULL,
     furigana VARCHAR(50) NOT NULL,
     postal_code VARCHAR(50) NOT NULL,
     address VARCHAR(255) NOT NULL,
     phone_number VARCHAR(50) NOT NULL,
     email VARCHAR(255) NOT NULL UNIQUE,
     password VARCHAR(255) NOT NULL,    
     role_id INT NOT NULL, 
     enabled BOOLEAN NOT NULL,
     created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,    
     FOREIGN KEY (role_id) REFERENCES roles (id)
 );
 
 CREATE TABLE IF NOT EXISTS verification_tokens (
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     user_id INT NOT NULL UNIQUE,
     token VARCHAR(255) NOT NULL,        
     created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     FOREIGN KEY (user_id) REFERENCES users (id) 
 );
 
 CREATE TABLE IF NOT EXISTS reservations (
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     shop_id INT NOT NULL,
     user_id INT NOT NULL,
     checkin_date DATE NOT NULL,
     checkin_time TIME NOT NULL,
     number_of_people INT NOT NULL,
     created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     FOREIGN KEY (shop_id) REFERENCES shop (id),
     FOREIGN KEY (user_id) REFERENCES users (id)
 );
 
 CREATE TABLE IF NOT EXISTS favorites (
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     shop_id INT NOT NULL,
     user_id INT NOT NULL,    
     created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     UNIQUE (shop_id, user_id),
     FOREIGN KEY (shop_id) REFERENCES shop (id),
     FOREIGN KEY (user_id) REFERENCES users (id)  
 );