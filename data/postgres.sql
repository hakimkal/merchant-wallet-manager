create database merchant_wallet_manaager_db;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table if not exists merchant_profile
(
    id         uuid not null unique primary key,
    name       varchar default 100,
    email      varchar default 100,
    secret     varchar default 100,
    is_active  boolean default false,
    created_at timestamp,
    updated_at timestamp
);

create table if not exists merchant_wallet
(
    id                  uuid not null unique
        primary key,
    merchant_profile_id uuid,
    previous_balance    varchar default 100,
    current_balance     varchar default 100,
    created_at          timestamp,
    FOREIGN KEY (merchant_profile_id)
        REFERENCES merchant_profile (id)
);
