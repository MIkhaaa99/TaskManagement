--liquibase formatted sql

--changeset msarks:1
CREATE TABLE IF NOT EXISTS employees
(
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE CHECK (LENGTH(username) BETWEEN 4 AND 20),
    password VARCHAR(64) NOT NULL,
    role VARCHAR(20) NOT NULL
)

--changeset msarks:2
CREATE TABLE IF NOT EXISTS tasks
(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(80) NOT NULL,
    description TEXT,
    status VARCHAR(20) NOT NULL,
    priority VARCHAR(20),
    admin_id BIGINT REFERENCES employees NOT NULL,
    performer_id BIGINT REFERENCES employees NOT NULL
)

--changeset msarks:3
CREATE TABLE IF NOT EXISTS comments
(
    id BIGSERIAL PRIMARY KEY,
    text TEXT NOT NULL,
    employee_id BIGINT REFERENCES employees NOT NULL,
    task_id BIGINT REFERENCES tasks NOT NULL
)