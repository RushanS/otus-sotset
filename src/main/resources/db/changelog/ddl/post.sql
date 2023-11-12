--liquibase formatted sql

--changeset rushan:add-posts
CREATE TABLE posts(
    id UUID NOT NULL DEFAULT gen_random_uuid(),
    text TEXT NOT NULL,
    author_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES users(id)
);

CREATE INDEX ix_author_created ON posts (author_id, created_at);

--rollback DROP TABLE posts
