DELETE FROM user_roles;
DELETE FROM applications;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@gmail.com', '{noop}password'),
       ('User2', 'user2@gmail.com', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Operator', 'operator@gmail.com', '{noop}operator');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('USER', 100001),
       ('ADMIN', 100002),
       ('OPERATOR', 100003);

INSERT INTO applications (user_id, text, status)
VALUES (100000, 'Мне нужна помощь', 'DRAFT'),
       (100000, 'Модем перестал работать', 'SENT'),
       (100000, 'Не работает выгрузка файлов', 'SENT'),
       (100000, 'Помогите настроить почту', 'ACCEPTED'),
       (100000, 'Прошу увеличить скорость интернета', 'SENT'),
       (100000, 'И еще одна заявка на проверку пагинации', 'SENT'),
       (100001, 'ghgfgfchgfjhgfgfj', 'REJECTED'),
       (100001, 'Необходимо подключение к удаленному рабочему столу', 'SENT'),
       (100001, 'Прошу осуществить возврат денег ', 'SENT');
