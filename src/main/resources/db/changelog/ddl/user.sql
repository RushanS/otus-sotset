--liquibase formatted sql

--changeset rushan:init-db
CREATE TABLE users(
    id UUID NOT NULL,
    first_name VARCHAR(255),
    second_name VARCHAR(255),
    age SMALLINT,
    birthdate DATE,
    biography TEXT,
    city VARCHAR(255),
    password BYTEA NOT NULL,
    PRIMARY KEY (id)
)

--rollback DROP TABLE users;


--changeset rushan:create-index-names
CREATE INDEX ix_first_second_names ON users (first_name, second_name);

--rollback DROP INDEX ix_first_second_names;
