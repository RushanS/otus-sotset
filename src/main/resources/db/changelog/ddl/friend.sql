--liquibase formatted sql

--changeset rushan:add-friends
CREATE TABLE friends(
    owner_id UUID NOT NULL,
    friend_id UUID NOT NULL,
    PRIMARY KEY (owner_id, friend_id)
)

--rollback DROP TABLE friends
