--liquibase formatted sql

--changeset rushan:init-db
CREATE TABLE user(
    id VARCHAR(36) NOT NULL,
    first_name VARCHAR(255),
    second_name VARCHAR(255),
    age SMALLINT,
    birthdate DATE,
    biography TEXT,
    city VARCHAR(255),
    password BLOB NOT NULL,
    PRIMARY KEY (id)
)

--rollback DROP TABLE user;


--changeset rushan:create-index-names
CREATE INDEX ix_first_second_names ON user (first_name, second_name);

--rollback DROP INDEX ix_first_second_names ON user;
