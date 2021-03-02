CREATE SCHEMA speedyteller;
create table if not exists speedyteller.acquirer
(
    id bigint not null
    constraint acquirer_pkey
    primary key,
    code varchar(255),
    name varchar(255),
    type varchar(255)
    );

create table if not exists agent_info
(
    id bigint not null
    constraint agent_info_pkey
    primary key,
    customer_ip varchar(255),
    customer_user_agent varchar(255),
    merchant_ip varchar(255)
    );

create table if not exists speedyteller.customer
(
    id bigint not null
    constraint customer_pkey
    primary key,
    billing_address_1 varchar(255),
    billing_address_2 varchar(255),
    billing_city varchar(255),
    billing_company varchar(255),
    billing_country varchar(255),
    billing_fax varchar(255),
    billing_first_name varchar(255),
    billing_last_name varchar(255),
    billing_phone varchar(255),
    billing_postcode varchar(255),
    billing_state varchar(255),
    billing_title varchar(255),
    birthday timestamp,
    created_at timestamp,
    deleted_at timestamp,
    email varchar(255),
    expiry_month varchar(255),
    expiry_year varchar(255),
    gender varchar(255),
    issue_number varchar(255),
    number varchar(255),
    shipping_address_1 varchar(255),
    shipping_address_2 varchar(255),
    shipping_city varchar(255),
    shipping_company varchar(255),
    shipping_country varchar(255),
    shipping_fax varchar(255),
    shipping_first_name varchar(255),
    shipping_last_name varchar(255),
    shipping_phone varchar(255),
    shipping_post_code varchar(255),
    shipping_state varchar(255),
    shipping_title varchar(255),
    start_month varchar(255),
    start_year varchar(255),
    updated_at timestamp
    );


create table if not exists speedyteller.fx_transaction
(
    id bigint not null
    constraint fx_transaction_pkey
    primary key,
    original_amount numeric(19,2),
    original_currency varchar(255)
    );

create table if not exists speedyteller.instant_payment_notification
(
    id bigint not null
    constraint instant_payment_notification_pkey
    primary key,
    received boolean,
    transaction_id varchar(255)
    );

create table if not exists speedyteller.merchant
(
    id bigint not null
    constraint merchant_pkey
    primary key,
    name varchar(255)
    );

create table if not exists speedyteller.transaction
(
    id bigint not null
    constraint transaction_pkey
    primary key,
    acquirer_transaction_id bigint,
    agent_info_id bigint,
    chain_id varchar(255),
    channel varchar(255),
    code varchar(255),
    created_at timestamp,
    custom_data varchar(255),
    customer_id bigint,
    fx_transaction_td bigint,
    merchant_id bigint,
    message varchar(255),
    operation varchar(255),
    reference_no varchar(255),
    status varchar(255),
    transaction_id varchar(255),
    updated_at timestamp,
    refundable boolean,
    error_code varchar(255)
    );

create table if not exists speedyteller.merchant_user
(
    id bigint not null
    constraint merchant_user_pkey
    primary key,
    email varchar(255),
    password varchar(255)
    );

INSERT INTO speedyteller.merchant_user (id, email, password) VALUES (1, 'test', '$2a$10$uxvB/iLjxGdimjl.bPQFve2trOioBCXYTV2XDmNcaQ09dYxUvBdym');