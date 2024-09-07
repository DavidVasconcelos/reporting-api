CREATE SEQUENCE IF NOT EXISTS speedyteller.acquirer_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS speedyteller.acquirer
(
    id   bigint NOT NULL,
    code varchar(255),
    name varchar(255),
    type varchar(255),
    CONSTRAINT acquirer_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS speedyteller.agent_info_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS speedyteller.agent_info
(
    id                  bigint NOT NULL,
    customer_ip         varchar(255),
    customer_user_agent varchar(255),
    merchant_ip         varchar(255),
    CONSTRAINT agent_info_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS speedyteller.customer_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS speedyteller.customer
(
    id                  bigint NOT NULL,
    billing_address_1   varchar(255),
    billing_address_2   varchar(255),
    billing_city        varchar(255),
    billing_company     varchar(255),
    billing_country     varchar(255),
    billing_fax         varchar(255),
    billing_first_name  varchar(255),
    billing_last_name   varchar(255),
    billing_phone       varchar(255),
    billing_postcode    varchar(255),
    billing_state       varchar(255),
    billing_title       varchar(255),
    birthday            timestamp,
    created_at          timestamp,
    deleted_at          timestamp,
    email               varchar(255),
    expiry_month        varchar(255),
    expiry_year         varchar(255),
    gender              varchar(255),
    issue_number        varchar(255),
    number              varchar(255),
    shipping_address_1  varchar(255),
    shipping_address_2  varchar(255),
    shipping_city       varchar(255),
    shipping_company    varchar(255),
    shipping_country    varchar(255),
    shipping_fax        varchar(255),
    shipping_first_name varchar(255),
    shipping_last_name  varchar(255),
    shipping_phone      varchar(255),
    shipping_post_code  varchar(255),
    shipping_state      varchar(255),
    shipping_title      varchar(255),
    start_month         varchar(255),
    start_year          varchar(255),
    updated_at          timestamp,
    CONSTRAINT customer_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS speedyteller.fx_transaction_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS speedyteller.fx_transaction
(
    id                bigint NOT NULL,
    original_amount   numeric(19, 2),
    original_currency varchar(255),
    CONSTRAINT fx_transaction_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS speedyteller.instant_payment_notification_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS speedyteller.instant_payment_notification
(
    id             bigint NOT NULL,
    received       boolean,
    transaction_id varchar(255),
    CONSTRAINT instant_payment_notification_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS speedyteller.merchant_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS speedyteller.merchant
(
    id   bigint NOT NULL,
    name varchar(255),
    CONSTRAINT merchant_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS speedyteller.transaction_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS speedyteller.transaction
(
    id                      bigint NOT NULL,
    acquirer_transaction_id bigint,
    agent_info_id           bigint,
    chain_id                varchar(255),
    channel                 varchar(255),
    code                    varchar(255),
    created_at              timestamp,
    custom_data             varchar(255),
    customer_id             bigint,
    fx_transaction_td       bigint,
    merchant_id             bigint,
    message                 varchar(255),
    operation               varchar(255),
    reference_no            varchar(255),
    status                  varchar(255),
    transaction_id          varchar(255),
    updated_at              timestamp,
    refundable              boolean,
    error_code              varchar(255),
    CONSTRAINT transaction_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS speedyteller.merchant_user_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS speedyteller.merchant_user
(
    id       bigint NOT NULL,
    email    varchar(255),
    password varchar(255),
    CONSTRAINT merchant_user_pkey PRIMARY KEY (id)
);

-- Merchant User

INSERT INTO speedyteller.merchant_user (id, email, password)
VALUES (nextval('speedyteller.merchant_user_id_seq'), 'test',
        '$2a$10$uxvB/iLjxGdimjl.bPQFve2trOioBCXYTV2XDmNcaQ09dYxUvBdym');

INSERT INTO speedyteller.merchant_user (id, email, password)
VALUES (nextval('speedyteller.merchant_user_id_seq'), 'merchant@test.com',
        '$2a$10$LIKPWzmhA3UFMa6nsNhZ9.HBoH/VVaYvEBU/k7VvZNM.fn5NCWuHy');

-- Transaction

INSERT INTO speedyteller.transaction (id, acquirer_transaction_id, agent_info_id, chain_id, channel,
                                      code, created_at, custom_data, customer_id, fx_transaction_td,
                                      merchant_id, message, operation, reference_no, status,
                                      transaction_id, updated_at, refundable, error_code)
VALUES (nextval('speedyteller.transaction_id_seq'), 2, 1, '5617ae666b4cb', 'API', '00',
        '2015-10-09 12:09:10.000000', null, 1, 1, 1,
        'Waiting', 'DIRECT', 'reference_5617ae66281ee', 'WAITING', '1-1444392550-1',
        '2015-10-09 12:09:12.000000', true, null);

INSERT INTO speedyteller.transaction (id, acquirer_transaction_id, agent_info_id, chain_id, channel,
                                      code, created_at, custom_data, customer_id, fx_transaction_td,
                                      merchant_id, message, operation, reference_no, status,
                                      transaction_id, updated_at, refundable, error_code)
VALUES (nextval('speedyteller.transaction_id_seq'), 1, 1, '5617ae666b4ca', 'API', '00',
        '2015-09-29 08:24:42.000000', null, 2, 2, 3,
        'Auth3D is APPROVED', '3DAUTH', 'api_560a4a9314208', 'APPROVED', '2827-1443515082-3', null,
        true, null);

INSERT INTO speedyteller.transaction (id, acquirer_transaction_id, agent_info_id, chain_id, channel,
                                      code, created_at, custom_data, customer_id, fx_transaction_td,
                                      merchant_id, message, operation, reference_no, status,
                                      transaction_id, updated_at, refundable, error_code)
VALUES (nextval('speedyteller.transaction_id_seq'), 2, null, null, null, null,
        '2015-09-30 16:16:27.000000', null, null, 3, 1, null, null,
        null, null, null, null, null, null);

INSERT INTO speedyteller.transaction (id, acquirer_transaction_id, agent_info_id, chain_id, channel,
                                      code, created_at, custom_data, customer_id, fx_transaction_td,
                                      merchant_id, message, operation, reference_no, status,
                                      transaction_id, updated_at, refundable, error_code)
VALUES (nextval('speedyteller.transaction_id_seq'), 2, null, null, null, null,
        '2015-10-05 16:16:06.000000', null, null, 4, 1, null, null,
        null, null, null, null, null, null);

INSERT INTO speedyteller.transaction (id, acquirer_transaction_id, agent_info_id, chain_id, channel,
                                      code, created_at, custom_data, customer_id, fx_transaction_td,
                                      merchant_id, message, operation, reference_no, status,
                                      transaction_id, updated_at, refundable, error_code)
VALUES (nextval('speedyteller.transaction_id_seq'), null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null,
        null, null, null);

INSERT INTO speedyteller.transaction (id, acquirer_transaction_id, agent_info_id, chain_id, channel,
                                      code, created_at, custom_data, customer_id, fx_transaction_td,
                                      merchant_id, message, operation, reference_no, status,
                                      transaction_id, updated_at, refundable, error_code)
VALUES (nextval('speedyteller.transaction_id_seq'), null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null,
        null, null, 'Invalid Transaction');

INSERT INTO speedyteller.transaction (id, acquirer_transaction_id, agent_info_id, chain_id, channel,
                                      code, created_at, custom_data, customer_id, fx_transaction_td,
                                      merchant_id, message, operation, reference_no, status,
                                      transaction_id, updated_at, refundable, error_code)
VALUES (nextval('speedyteller.transaction_id_seq'), null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null,
        null, null, null);

INSERT INTO speedyteller.transaction (id, acquirer_transaction_id, agent_info_id, chain_id, channel,
                                      code, created_at, custom_data, customer_id, fx_transaction_td,
                                      merchant_id, message, operation, reference_no, status,
                                      transaction_id, updated_at, refundable, error_code)
VALUES (nextval('speedyteller.transaction_id_seq'), null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null,
        null, null, null);

INSERT INTO speedyteller.transaction (id, acquirer_transaction_id, agent_info_id, chain_id, channel,
                                      code, created_at, custom_data, customer_id, fx_transaction_td,
                                      merchant_id, message, operation, reference_no, status,
                                      transaction_id, updated_at, refundable, error_code)
VALUES (nextval('speedyteller.transaction_id_seq'), null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null,
        null, null, null);

INSERT INTO speedyteller.transaction (id, acquirer_transaction_id, agent_info_id, chain_id, channel,
                                      code, created_at, custom_data, customer_id, fx_transaction_td,
                                      merchant_id, message, operation, reference_no, status,
                                      transaction_id, updated_at, refundable, error_code)
VALUES (nextval('speedyteller.transaction_id_seq'), null, null, null, null, null, null, null, null,
        null, null, null, null, null, null,
        null, null, null, null);




-- Customer

INSERT INTO speedyteller.customer (id, billing_address_1, billing_address_2, billing_city,
                                   billing_company, billing_country, billing_fax,
                                   billing_first_name, billing_last_name, billing_phone,
                                   billing_postcode, billing_state, billing_title, birthday,
                                   created_at, deleted_at, email, expiry_month, expiry_year, gender,
                                   issue_number, number, shipping_address_1, shipping_address_2,
                                   shipping_city, shipping_company, shipping_country, shipping_fax,
                                   shipping_first_name, shipping_last_name, shipping_phone,
                                   shipping_post_code, shipping_state, shipping_title, start_month,
                                   start_year, updated_at)
VALUES (nextval('speedyteller.customer_id_seq'), 'test address', null, 'Antalya', null, 'TR', null,
        'Michael', 'Kara', null, '07070',
        null, null, '1986-03-20 12:09:10.000000', '2015-10-09 12:09:10.000000', null,
        'michael@gmail.com', '6', '2017', null, null, '401288XXXXXX1881', 'test address', null,
        'Antalya', null, 'TR', null, 'Michael', 'Kara', null, '07070', null, null, null, null,
        '2015-10-09 12:09:10.000000');

INSERT INTO speedyteller.customer (id, billing_address_1, billing_address_2, billing_city,
                                   billing_company, billing_country, billing_fax,
                                   billing_first_name, billing_last_name, billing_phone,
                                   billing_postcode, billing_state, billing_title, birthday,
                                   created_at, deleted_at, email, expiry_month, expiry_year, gender,
                                   issue_number, number, shipping_address_1, shipping_address_2,
                                   shipping_city, shipping_company, shipping_country, shipping_fax,
                                   shipping_first_name, shipping_last_name, shipping_phone,
                                   shipping_post_code, shipping_state, shipping_title, start_month,
                                   start_year, updated_at)
VALUES (nextval('speedyteller.customer_id_seq'), 'test address', null, 'Antalya', null, 'TR', null,
        'Aykut', 'Aras', null, '08080', null,
        null, '1987-04-21 13:10:11.000000', '2015-09-29 08:25:49.000000', null,
        'aykut.aras@bumin.com.tr', '2', '2018', null, null, '448574XXXXXX3395', 'test address',
        null, 'Antalya', null, 'TR', null, 'Aykut', 'Aras', null, '08080', null, null, null, null,
        '2015-09-29 08:26:54.000000');

-- FX Transaction

INSERT INTO speedyteller.fx_transaction (id, original_amount, original_currency)
VALUES (nextval('speedyteller.fx_transaction_id_seq'), 100.00, 'EUR');

INSERT INTO speedyteller.fx_transaction (id, original_amount, original_currency)
VALUES (nextval('speedyteller.fx_transaction_id_seq'), 5.00, 'EUR');

INSERT INTO speedyteller.fx_transaction (id, original_amount, original_currency)
VALUES (nextval('speedyteller.fx_transaction_id_seq'), 250.00, 'USD');

INSERT INTO speedyteller.fx_transaction (id, original_amount, original_currency)
VALUES (nextval('speedyteller.fx_transaction_id_seq'), 125.00, 'USD');

INSERT INTO speedyteller.fx_transaction (id, original_amount, original_currency)
VALUES (nextval('speedyteller.fx_transaction_id_seq'), 399.00, 'EUR');

--Merchant

INSERT INTO speedyteller.merchant (id, name)
VALUES (nextval('speedyteller.merchant_id_seq'), 'Dev-Merchant');

INSERT INTO speedyteller.merchant (id, name)
VALUES (nextval('speedyteller.merchant_id_seq'), 'Dev-Merchant');

INSERT INTO speedyteller.merchant (id, name)
VALUES (nextval('speedyteller.merchant_id_seq'), 'Dev-Merchant');

INSERT INTO speedyteller.merchant (id, name)
VALUES (nextval('speedyteller.merchant_id_seq'), 'Dev-Merchant');

INSERT INTO speedyteller.merchant (id, name)
VALUES (nextval('speedyteller.merchant_id_seq'), 'Dev-Merchant');

-- Agent Info

INSERT INTO speedyteller.agent_info (id, customer_ip, customer_user_agent, merchant_ip)
VALUES (nextval('speedyteller.agent_info_id_seq'), '192.168.1.2', 'Agent', '127.0.0.1');

-- Acquirer

INSERT INTO speedyteller.acquirer (id, code, name, type)
VALUES (nextval('speedyteller.acquirer_id_seq'), 'MB', 'Mergen Bank', 'CREDITCARD');

INSERT INTO speedyteller.acquirer (id, code, name, type)
VALUES (nextval('speedyteller.acquirer_id_seq'), 'CB', 'Comitten Bank', 'PAYTOCARD');

-- Instant Payment Notification

INSERT INTO speedyteller.instant_payment_notification (id, received, transaction_id)
VALUES (nextval('speedyteller.instant_payment_notification_id_seq'), true, '1-1444392550-1');

INSERT INTO speedyteller.instant_payment_notification (id, received, transaction_id)
VALUES (nextval('speedyteller.instant_payment_notification_id_seq'), true, '2827-1443515082-3');

