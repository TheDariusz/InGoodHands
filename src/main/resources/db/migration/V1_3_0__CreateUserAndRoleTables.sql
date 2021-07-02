CREATE TABLE role
(
    id   int4 PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE "user"
(
    id       int8 PRIMARY KEY,
    enabled  INTEGER NOT NULL,
    password TEXT    NOT NULL,
    email TEXT    NOT NULL,

    CONSTRAINT uk_email UNIQUE (email)
);

CREATE TABLE user_role
(
    user_id int8  NOT NULL,
    role_id int4 NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_userrole_user FOREIGN KEY (user_id) references "user" (id),
    CONSTRAINT fk_userrole_role FOREIGN KEY (role_id) references role (id)
);

INSERT INTO role (id, name) VALUES (101, 'ROLE_USER');
INSERT INTO role (id, name) VALUES ( 102, 'ROLE_ADMIN');