CREATE DATABASE IF NOT EXISTS foods;

USE foods;

DROP TABLE IF EXISTS foods;

CREATE TABLE foods(
	id INT(5) NOT NULL AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	description TINYTEXT NOT NULL,
	PRIMARY KEY(id)
	
);

