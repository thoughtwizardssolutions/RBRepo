DROP SCHEMA IF EXISTS rbdb;
CREATE SCHEMA IF NOT EXISTS rbdb DEFAULT CHARACTER SET utf8;
USE rbdb;

CREATE TABLE IF NOT EXISTS rbdb.app_distributors (
  id INT NOT NULL AUTO_INCREMENT,  
  username VARCHAR(40) NOT NULL,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(40),
  state VARCHAR(40) NOT NULL,
  city VARCHAR(40) NOT NULL,
  zip VARCHAR(10) NOT NULL,
  address1 VARCHAR(40),
  address2 VARCHAR(40),
  phone VARCHAR(12),
  password VARCHAR(15) NOT NULL,
  role VARCHAR(8) NOT NULL,
  PRIMARY KEY (id));
  
CREATE TABLE IF NOT EXISTS rbdb.app_retailers (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  tin VARCHAR(25),
  email VARCHAR(40),
  state VARCHAR(40) NOT NULL,
  city VARCHAR(40) NOT NULL,
  zip VARCHAR(10) NOT NULL,
  address1 VARCHAR(40),
  address2 VARCHAR(40),
  phone VARCHAR(12),
  PRIMARY KEY (id)),
  CONSTRAINT FK_DISTRIBUTOR_ID
    FOREIGN KEY (created_by_distributor)
    REFERENCES rbdb.app_distributors (id);  
  
CREATE TABLE IF NOT EXISTS rbdb.app_invoices (
  id INT NOT NULL AUTO_INCREMENT,
  
  PRIMARY KEY (id),
  CONSTRAINT FK_ADDRESS_ID
    FOREIGN KEY (distributor_id)
    REFERENCES rbdb.app_distributors (id),
  CONSTRAINT FK_CUSTOMER_IMAGE_ID
    FOREIGN KEY (retailer_id)
    REFERENCES rbdb.app_retailers (id));    
