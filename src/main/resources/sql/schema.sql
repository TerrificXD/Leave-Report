
CREATE DATABASE leave_db;
\c leave_db;


CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL

);


CREATE TABLE leaves (
    id SERIAL PRIMARY KEY,
    leave_name VARCHAR(100) NOT NULL UNIQUE
);


CREATE TABLE user_leave_reports (
    id SERIAL PRIMARY KEY,

    user_id INTEGER NOT NULL,
    leave_id INTEGER NOT NULL,

    apply_date DATE NOT NULL,
    from_date DATE NOT NULL,
    to_date DATE NOT NULL,

    description TEXT,

    CONSTRAINT fk_user
        FOREIGN KEY(user_id)
            REFERENCES users(user_id)
            ON DELETE CASCADE,

    CONSTRAINT fk_leave
        FOREIGN KEY(leave_id)
            REFERENCES leaves(id)
            ON DELETE CASCADE
);


