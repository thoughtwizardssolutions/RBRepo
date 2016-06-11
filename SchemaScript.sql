DROP SCHEMA IF EXISTS rbdbdev;
CREATE SCHEMA IF NOT EXISTS rbdbdev DEFAULT CHARACTER SET utf8;
USE rbdbdev;

CREATE TABLE IF NOT EXISTS rbdbdev.app_address (
  id INT NOT NULL AUTO_INCREMENT,  
  phone VARCHAR(40),
  city VARCHAR(40) NOT NULL,
  state VARCHAR(40) NOT NULL,
  country VARCHAR(40) NOT NULL,
  postcode VARCHAR(10) NOT NULL,
  address1 VARCHAR(40),
  address2 VARCHAR(40),
  email VARCHAR(40),
  PRIMARY KEY (id));
  
  CREATE TABLE IF NOT EXISTS rbdbdev.app_user (
  id INT NOT NULL AUTO_INCREMENT,  
  username VARCHAR(40) NOT NULL UNIQUE,
  password VARCHAR(40) NOT NULL,
  role VARCHAR(40) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS rbdbdev.app_distributor (
  id INT NOT NULL AUTO_INCREMENT,  
  name VARCHAR(40) NOT NULL,
  address_id INT NOT NULL,
  user_id INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_ADDRESS_ID
    FOREIGN KEY (address_id)
    REFERENCES rbdbdev.app_address (id),
  CONSTRAINT FK_USER_ID
    FOREIGN KEY (user_id)
    REFERENCES rbdbdev.app_user (id));
  
CREATE TABLE IF NOT EXISTS rbdbdev.app_retailer (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  tin VARCHAR(25),
  address_id INT NOT NULL,
  created_by INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_RETAILER_ADDRESS_ID
    FOREIGN KEY (address_id)
    REFERENCES rbdbdev.app_address (id),
  CONSTRAINT FK_DISTRIBUTOR_ID
    FOREIGN KEY (created_by)
    REFERENCES rbdbdev.app_distributor (id));      
