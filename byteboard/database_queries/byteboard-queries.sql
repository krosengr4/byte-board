# ---------------------------------------------------------------------- #
# Target DBMS:           MySQL                                           #
# Project name:          ByteBoard.                                      #
# ---------------------------------------------------------------------- #
DROP DATABASE IF EXISTS byteboard;

CREATE DATABASE IF NOT EXISTS byteboard;

USE byteboard;

# ---------------------------------------------------------------------- #
# Tables                                                                 #
# ---------------------------------------------------------------------- #

-- Creating tables
CREATE TABLE users(
 user_id int NOT NULL auto_increment,
 username VARCHAR(50) NOT NULL,
 hashed_password VARCHAR(255) NOT NULL,
 role VARCHAR(50) NOT NULL,
 PRIMARY KEY(user_id)
 );
 
  CREATE TABLE profiles (
    user_id INT NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(200),
    github_link VARCHAR(75),
    city VARCHAR(50),
    state VARCHAR(50),
    date_registered DATE,
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE posts (
post_id INT NOT NULL AUTO_INCREMENT,
user_id INT NOT NULL,
title varchar(255) NOT NULL,
content TEXT NOT NULL,
author VARCHAR(50) NOT NULL,
date_posted DATETIME NOT NULL,
PRIMARY KEY (post_id),
FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE comments (
comment_id INT AUTO_INCREMENT NOT NULL,
user_id INT NOT NULL,
post_id INT NOT NULL,
content TEXT NOT NULL,
author VARCHAR(50) NOT NULL,
date_posted DATETIME NOT NULL,
PRIMARY KEY (comment_id),
FOREIGN KEY (user_id) REFERENCES users(user_id),
FOREIGN KEY (post_id) REFERENCES posts(post_id)
);