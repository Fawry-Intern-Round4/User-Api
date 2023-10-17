drop table if exists users;
CREATE TABLE users
(
    id       INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    role     VARCHAR(255)        NOT NULL,
    enable   BOOLEAN             NOT NULL
);