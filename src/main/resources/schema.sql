CREATE TABLE INSTITUTION (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code NUMERIC(5) NOT NULL,
    name VARCHAR(50) NOT NULL,
    status BOOLEAN NOT NULL
);

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);
