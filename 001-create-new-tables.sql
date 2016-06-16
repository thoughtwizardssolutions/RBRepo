--<ScriptOptions statementTerminator=";"/>

ALTER TABLE jhi_user DROP PRIMARY KEY;

ALTER TABLE invoice_item DROP PRIMARY KEY;

ALTER TABLE dealer DROP PRIMARY KEY;

ALTER TABLE jhi_persistent_audit_event DROP PRIMARY KEY;

ALTER TABLE jhi_persistent_audit_evt_data DROP PRIMARY KEY;

ALTER TABLE address DROP PRIMARY KEY;

ALTER TABLE app_retailer DROP PRIMARY KEY;

ALTER TABLE app_user DROP PRIMARY KEY;

ALTER TABLE jhi_user_authority DROP PRIMARY KEY;

ALTER TABLE tax DROP PRIMARY KEY;

ALTER TABLE app_distributor DROP PRIMARY KEY;

ALTER TABLE app_address DROP PRIMARY KEY;

ALTER TABLE DATABASECHANGELOGLOCK DROP PRIMARY KEY;

ALTER TABLE jhi_persistent_token DROP PRIMARY KEY;

ALTER TABLE imei DROP PRIMARY KEY;

ALTER TABLE distributor DROP PRIMARY KEY;

ALTER TABLE jhi_persistent_event DROP PRIMARY KEY;

ALTER TABLE product DROP PRIMARY KEY;

ALTER TABLE jhi_authority DROP PRIMARY KEY;

ALTER TABLE invoice DROP PRIMARY KEY;

ALTER TABLE jhi_user DROP INDEX UK_bycanyquvi09q7fh5pgxrqnku;

ALTER TABLE jhi_persistent_token DROP INDEX FK_c2yetr6vr7nrqhjvi1rl24b3l;

ALTER TABLE app_distributor DROP INDEX FK_ADDRESS_ID;

ALTER TABLE dealer DROP INDEX UK_5sug7cpflg3q5djc3n1x337g3;

ALTER TABLE jhi_user_authority DROP INDEX FK_ik5r332jlvpolfr7e5n7oi42l;

ALTER TABLE distributor DROP INDEX UK_2ild9utg6mgoqy0qbu2a3u6gv;

ALTER TABLE app_distributor DROP INDEX FK_USER_ID;

ALTER TABLE address DROP INDEX FK_jt0rwqqnrulxlnraurcmklavn;

ALTER TABLE app_retailer DROP INDEX FK_DISTRIBUTOR_ID;

ALTER TABLE invoice_item DROP INDEX UK_90smyivuaimfr10shwrtii69f;

ALTER TABLE distributor DROP INDEX UK_drufnjl3exs1rl3mt4ql1o6i2;

ALTER TABLE jhi_user DROP INDEX UK_9y0frpqnmqe7y6mk109vw3246;

ALTER TABLE app_retailer DROP INDEX FK_RETAILER_ADDRESS_ID;

ALTER TABLE distributor DROP INDEX UK_f7lqjwt11v5v3fax1w8ph0r1x;

ALTER TABLE app_user DROP INDEX username;

DROP TABLE jhi_persistent_audit_event;

DROP TABLE app_distributor;

DROP TABLE DATABASECHANGELOG;

DROP TABLE app_retailer;

DROP TABLE jhi_user;

DROP TABLE jhi_persistent_audit_evt_data;

DROP TABLE dealer;

DROP TABLE tax;

DROP TABLE invoice_item;

DROP TABLE DATABASECHANGELOGLOCK;

DROP TABLE product;

DROP TABLE address;

DROP TABLE jhi_authority;

DROP TABLE jhi_persistent_token;

DROP TABLE imei;

DROP TABLE app_address;

DROP TABLE distributor;

DROP TABLE app_user;

DROP TABLE invoice;

DROP TABLE jhi_persistent_event;

DROP TABLE jhi_user_authority;

CREATE TABLE jhi_persistent_audit_event (
	event_id BIGINT NOT NULL,
	event_date DATETIME,
	event_type VARCHAR(255),
	principal VARCHAR(255) NOT NULL,
	PRIMARY KEY (event_id)
) ENGINE=InnoDB;

CREATE TABLE app_distributor (
	id INT NOT NULL,
	name VARCHAR(40) NOT NULL,
	address_id INT NOT NULL,
	user_id INT NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE DATABASECHANGELOG (
	ID VARCHAR(255) NOT NULL,
	AUTHOR VARCHAR(255) NOT NULL,
	FILENAME VARCHAR(255) NOT NULL,
	DATEEXECUTED DATETIME NOT NULL,
	ORDEREXECUTED INT NOT NULL,
	EXECTYPE VARCHAR(10) NOT NULL,
	MD5SUM VARCHAR(35),
	DESCRIPTION VARCHAR(255),
	COMMENTS VARCHAR(255),
	TAG VARCHAR(255),
	LIQUIBASE VARCHAR(20),
	CONTEXTS VARCHAR(255),
	LABELS VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE app_retailer (
	id INT NOT NULL,
	name VARCHAR(100) NOT NULL,
	tin VARCHAR(25),
	address_id INT NOT NULL,
	created_by INT NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE jhi_user (
	id BIGINT NOT NULL,
	created_by VARCHAR(50) NOT NULL,
	created_date DATETIME NOT NULL,
	last_modified_by VARCHAR(50),
	last_modified_date DATETIME,
	activated BIT NOT NULL,
	activation_key VARCHAR(20),
	email VARCHAR(100),
	first_name VARCHAR(50),
	lang_key VARCHAR(5),
	last_name VARCHAR(50),
	login VARCHAR(50) NOT NULL,
	password_hash VARCHAR(60) NOT NULL,
	reset_date DATETIME,
	reset_key VARCHAR(20),
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE jhi_persistent_audit_evt_data (
	event_id BIGINT NOT NULL,
	value VARCHAR(255),
	name VARCHAR(255) NOT NULL,
	PRIMARY KEY (event_id,name)
) ENGINE=InnoDB;

CREATE TABLE dealer (
	id BIGINT NOT NULL,
	creation_date DATE NOT NULL,
	current_balance DECIMAL(10 , 2),
	firm_name VARCHAR(255) NOT NULL,
	modification_date DATE,
	opening_balance DECIMAL(10 , 2),
	owner_name VARCHAR(255),
	terms_and_conditions VARCHAR(255),
	tin VARCHAR(255) NOT NULL,
	address_id BIGINT,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE tax (
	id BIGINT NOT NULL,
	creation_date DATE NOT NULL,
	modification_date DATE,
	name VARCHAR(255) NOT NULL,
	rate DECIMAL(10 , 2) NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE invoice_item (
	id BIGINT NOT NULL,
	amount DECIMAL(10 , 2) NOT NULL,
	discount DECIMAL(10 , 2),
	mrp DECIMAL(10 , 2) NOT NULL,
	quantity INT NOT NULL,
	product_id BIGINT,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE DATABASECHANGELOGLOCK (
	ID INT NOT NULL,
	LOCKED BIT NOT NULL,
	LOCKGRANTED DATETIME,
	LOCKEDBY VARCHAR(255),
	PRIMARY KEY (ID)
) ENGINE=InnoDB;

CREATE TABLE product (
	id BIGINT NOT NULL,
	color VARCHAR(255),
	creation_date DATE NOT NULL,
	description VARCHAR(255),
	mrp DECIMAL(10 , 2) NOT NULL,
	product_name VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE address (
	id BIGINT NOT NULL,
	address_1 VARCHAR(255),
	address_2 VARCHAR(255),
	city VARCHAR(255),
	country VARCHAR(255),
	email VARCHAR(255),
	phone VARCHAR(255),
	post_code VARCHAR(255),
	state VARCHAR(255),
	distributor_id BIGINT,
	pincode INT NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE jhi_authority (
	name VARCHAR(50) NOT NULL,
	PRIMARY KEY (name)
) ENGINE=InnoDB;

CREATE TABLE jhi_persistent_token (
	series VARCHAR(255) NOT NULL,
	ip_address VARCHAR(39),
	token_date DATE,
	token_value VARCHAR(255) NOT NULL,
	user_agent VARCHAR(255),
	user_id BIGINT,
	PRIMARY KEY (series)
) ENGINE=InnoDB;

CREATE TABLE imei (
	id BIGINT NOT NULL,
	imei_1 VARCHAR(255) NOT NULL,
	imei_2 VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE app_address (
	id INT NOT NULL,
	phone VARCHAR(40),
	city VARCHAR(40) NOT NULL,
	state VARCHAR(40) NOT NULL,
	country VARCHAR(40) NOT NULL,
	postcode VARCHAR(10) NOT NULL,
	address1 VARCHAR(40),
	address2 VARCHAR(40),
	email VARCHAR(40),
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE distributor (
	id BIGINT NOT NULL,
	name VARCHAR(255),
	billing_address_id BIGINT,
	primary_address_id BIGINT,
	shipping_address_id BIGINT,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE app_user (
	id INT NOT NULL,
	username VARCHAR(40) NOT NULL,
	password VARCHAR(40) NOT NULL,
	role VARCHAR(40) NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE invoice (
	id BIGINT NOT NULL,
	adjustments DECIMAL(10 , 2),
	creation_date DATE NOT NULL,
	invoice_number VARCHAR(255) NOT NULL,
	modfication_date DATE,
	order_number VARCHAR(255),
	sales_person_name VARCHAR(255),
	shipping_charges DECIMAL(10 , 2),
	subtotal DECIMAL(10 , 2) NOT NULL,
	taxes DECIMAL(10 , 2),
	total_amount DECIMAL(10 , 2) NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE jhi_persistent_event (
	EVENT_ID INT NOT NULL,
	PRINCIPAL VARCHAR(255) NOT NULL,
	EVENT_DATE TIMESTAMP,
	EVENT_TYPE VARCHAR(255),
	PRIMARY KEY (EVENT_ID)
) ENGINE=InnoDB;

CREATE TABLE jhi_user_authority (
	user_id BIGINT NOT NULL,
	authority_name VARCHAR(50) NOT NULL,
	PRIMARY KEY (user_id,authority_name)
) ENGINE=InnoDB;

CREATE UNIQUE INDEX UK_bycanyquvi09q7fh5pgxrqnku ON jhi_user (email ASC);

CREATE INDEX FK_c2yetr6vr7nrqhjvi1rl24b3l ON jhi_persistent_token (user_id ASC);

CREATE INDEX FK_ADDRESS_ID ON app_distributor (address_id ASC);

CREATE UNIQUE INDEX UK_5sug7cpflg3q5djc3n1x337g3 ON dealer (address_id ASC);

CREATE INDEX FK_ik5r332jlvpolfr7e5n7oi42l ON jhi_user_authority (authority_name ASC);

CREATE UNIQUE INDEX UK_2ild9utg6mgoqy0qbu2a3u6gv ON distributor (billing_address_id ASC);

CREATE INDEX FK_USER_ID ON app_distributor (user_id ASC);

CREATE INDEX FK_jt0rwqqnrulxlnraurcmklavn ON address (distributor_id ASC);

CREATE INDEX FK_DISTRIBUTOR_ID ON app_retailer (created_by ASC);

CREATE UNIQUE INDEX UK_90smyivuaimfr10shwrtii69f ON invoice_item (product_id ASC);

CREATE UNIQUE INDEX UK_drufnjl3exs1rl3mt4ql1o6i2 ON distributor (shipping_address_id ASC);

CREATE UNIQUE INDEX UK_9y0frpqnmqe7y6mk109vw3246 ON jhi_user (login ASC);

CREATE INDEX FK_RETAILER_ADDRESS_ID ON app_retailer (address_id ASC);

CREATE UNIQUE INDEX UK_f7lqjwt11v5v3fax1w8ph0r1x ON distributor (primary_address_id ASC);

CREATE UNIQUE INDEX username ON app_user (username ASC);

ALTER TABLE jhi_user ADD PRIMARY KEY (id);

