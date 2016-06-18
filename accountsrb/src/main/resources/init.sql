CREATE TABLE IF NOT EXISTS jhi_authority (
  name varchar(50) NOT NULL,
  PRIMARY KEY (name)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS jhi_user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  created_by varchar(50) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(50) DEFAULT NULL,
  last_modified_date datetime DEFAULT NULL,
  activated bit(1) NOT NULL,
  activation_key varchar(20) DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  first_name varchar(50) DEFAULT NULL,
  lang_key varchar(5) DEFAULT NULL,
  last_name varchar(50) DEFAULT NULL,
  login varchar(50) NOT NULL,
  password_hash varchar(60) NOT NULL,
  reset_date datetime DEFAULT NULL,
  reset_key varchar(20) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_login_name (login),
  UNIQUE KEY UK_login_email (email)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS jhi_user_authority (
  user_id bigint(20) NOT NULL,
  authority_name varchar(50) NOT NULL,
  PRIMARY KEY (user_id,authority_name),
  CONSTRAINT FK_ik5r332jlvpolfr7e5n7oi42l FOREIGN KEY (authority_name) REFERENCES jhi_authority (name),
  CONSTRAINT FK_pg0oaw6mr9pt3ibeihdc1jwof FOREIGN KEY (user_id) REFERENCES jhi_user (id)
) ENGINE=InnoDB;


CREATE TABLE IF NOT EXISTS jhi_persistent_token (
  series varchar(255) NOT NULL,
  ip_address varchar(39) DEFAULT NULL,
  token_date date DEFAULT NULL,
  token_value varchar(255) NOT NULL,
  user_agent varchar(255) DEFAULT NULL,
  user_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (series),
  CONSTRAINT FK_c2yetr6vr7nrqhjvi1rl24b3l FOREIGN KEY (user_id) REFERENCES jhi_user (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS jhi_persistent_audit_event (
  event_id bigint(20) NOT NULL AUTO_INCREMENT,
  event_date datetime DEFAULT NULL,
  event_type varchar(255) DEFAULT NULL,
  principal varchar(255) NOT NULL,
  PRIMARY KEY (event_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS jhi_persistent_audit_evt_data (
  event_id bigint(20) NOT NULL,
  value varchar(255) DEFAULT NULL,
  name varchar(255) NOT NULL,
  PRIMARY KEY (event_id,name),
  CONSTRAINT FK_evb970jo5bi8aon8s65c0lyyn FOREIGN KEY (event_id) REFERENCES jhi_persistent_audit_event (event_id)
) ENGINE=InnoDB;


CREATE TABLE IF NOT EXISTS jhi_persistent_event (
  EVENT_ID int(11) NOT NULL,
  PRINCIPAL varchar(255) NOT NULL,
  EVENT_DATE timestamp NULL DEFAULT NULL,
  EVENT_TYPE varchar(255) DEFAULT NULL,
  PRIMARY KEY (EVENT_ID)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS address (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  address_1 varchar(255) DEFAULT NULL,
  address_2 varchar(255) DEFAULT NULL,
  city varchar(255) DEFAULT NULL,
  country varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  phone varchar(255) DEFAULT NULL,
  post_code varchar(255) DEFAULT NULL,
  state varchar(255) DEFAULT NULL,
  dealer_id bigint(20) DEFAULT NULL,
  pincode int(11) NOT NULL,
  PRIMARY KEY (id),
) ENGINE=InnoDB;


CREATE TABLE IF NOT EXISTS tax (
	id BIGINT NOT NULL AUTO_INCREMENT,
	creation_date DATE NOT NULL ,
	modification_date DATE,
	name VARCHAR(255) NOT NULL,
	rate DECIMAL(10 , 2) NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS product (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  color varchar(255) DEFAULT NULL,
  creation_date date NOT NULL,
  description varchar(255) DEFAULT NULL,
  mrp decimal(10,2) NOT NULL,
  product_name varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB;


CREATE TABLE IF NOT EXISTS dealer (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  creation_date date NOT NULL,
  current_balance decimal(10,2) DEFAULT NULL,
  firm_name varchar(255) NOT NULL UNIQUE,
  modification_date date DEFAULT NULL,
  opening_balance decimal(10,2) DEFAULT NULL,
  owner_name varchar(255) DEFAULT NULL,
  terms_and_conditions varchar(255) DEFAULT NULL,
  tin varchar(255),
  address_id bigint(20) DEFAULT NULL,
  created_by varchar(255) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_dealer_address_id FOREIGN KEY (address_id) REFERENCES address (id)
) ENGINE=InnoDB;



 CREATE TABLE IF NOT EXISTS invoice (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  adjustments decimal(10,2) DEFAULT NULL,
  creation_date date NOT NULL,
  invoice_number varchar(255) NOT NULL UNIQUE,
  modfication_date date DEFAULT NULL,
  order_number varchar(255) DEFAULT NULL,
  sales_person_name varchar(255) DEFAULT NULL,
  shipping_charges decimal(10,2) DEFAULT NULL,
  subtotal decimal(10,2) NOT NULL,
  dealer_id decimal(10,2) NOT NULL,
  taxes decimal(10,2) DEFAULT NULL,
  total_amount decimal(10,2) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_invoice_dealer FOREIGN KEY (dealer_id) REFERENCES dealer (id)
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS invoice_item (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  amount decimal(10,2) NOT NULL,
  discount decimal(10,2) DEFAULT NULL,
  mrp decimal(10,2) NOT NULL,
  quantity int(11) NOT NULL,
  tax_type varchar(12) DEFAULT NULL,
  tax_rate decimal(3,2) DEFAULT NULL,
  item_description varchar(255) DEFAULT NULL,
  product_id bigint(20) DEFAULT NULL,
  invoice_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_invoice_item_invoice FOREIGN KEY (invoice_id) REFERENCES invoice (id),
  CONSTRAINT FK_invoice_item_product FOREIGN KEY (product_id) REFERENCES product (id)
) ENGINE=InnoDB;



CREATE TABLE IF NOT EXISTS imei (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  imei_1 varchar(255) NOT NULL,
  imei_2 varchar(255) NOT NULL,
  invoice_item_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_imei_invoice_item FOREIGN KEY (invoice_item_id) REFERENCES invoice_item (id)
) ENGINE=InnoDB;


insert into jhi_authority values('ROLE_ADMIN');
insert into jhi_authority values('ROLE_ORG_ADMIN');
insert into jhi_authority values('ROLE_USER');

insert into jhi_user values(1,'system','2016-06-13 10:45:27.337',null,null,TRUE,null,'admin@localhost','admin','en','admin','admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC',null,null);
insert into jhi_user values(2,'system','2016-06-13 10:45:27.337',null,null,TRUE,null,'user@localhost','user','en','user','user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K',null,null);
insert into jhi_user values(3,'system','2016-06-13 10:45:27.337',null,null,TRUE,null,'orgadmin@localhost','orgadmin','en','orgadmin','orgadmin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC',null,null);


insert into jhi_user_authority values(1, 'ROLE_ADMIN');
insert into jhi_user_authority values(1, 'ROLE_ORG_ADMIN');
insert into jhi_user_authority values(1, 'ROLE_USER');
insert into jhi_user_authority values(2, 'ROLE_USER');
insert into jhi_user_authority values(3, 'ROLE_ORG_ADMIN');