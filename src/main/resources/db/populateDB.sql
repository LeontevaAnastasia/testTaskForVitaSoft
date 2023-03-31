DELETE FROM user_roles;
DELETE FROM applications;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('UserOne', 'userOne@gmail.com', 'password'),
       ('UserTwo', 'userTwo@gmail.com', 'password'),
       ('Admin', 'admin@gmail.com', 'adminPass'),
       ('Operator', 'operator@gmail.com', 'operatorPass');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('USER', 100001),
       ('ADMIN', 100002),
       ('OPERATOR', 100003);

INSERT INTO applications (user_id, text, status)
VALUES (100000, 'Мне нужна помощь', 'DRAFT'),
       (100001, 'Модем перестал работать', 'SENT');