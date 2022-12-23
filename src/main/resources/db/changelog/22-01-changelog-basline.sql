-- liquibase formatted sql

-- changeset taachra0:1671686732259-1
CREATE TABLE student
(
    id         INT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(45) NULL,
    last_name  VARCHAR(45) NULL,
    email      VARCHAR(45) NULL,
    CONSTRAINT PK_STUDENT PRIMARY KEY (id)
);

