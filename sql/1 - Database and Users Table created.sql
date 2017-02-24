CREATE DATABASE example;

USE example;

CREATE TABLE `user`
(
  id 				INT(11) 		NOT NULL AUTO_INCREMENT,
  email 			VARCHAR(100) 	NOT NULL,
  password_hash 	VARCHAR(255) 	NOT NULL,
  date_creation 	DATETIME(6) 	NOT NULL,
  PRIMARY KEY (id)
);
CREATE UNIQUE INDEX uq_user_email ON  `user` (email);