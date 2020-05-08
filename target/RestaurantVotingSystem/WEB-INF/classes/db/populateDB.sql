DELETE
FROM user_roles;
DELETE
FROM votes;
DELETE
FROM meals;
DELETE
FROM menus;
DELETE
FROM restaurants;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001);

INSERT INTO restaurants (name, user_id)
VALUES ('Час-Пик', 100001),
       ('Ташир', 100001);

INSERT INTO menus(date, restaurant_id)
VALUES ('2020-05-07', 100002),
       ('2020-05-07', 100003),
       ('2020-05-08', 100003);

INSERT INTO meals (name, price, menu_id)
VALUES ('Свинина фламбе', 1000, 100004),
       ('Стейк Везувий', 350, 100004),
       ('Пицца Валентинка', 362, 100004);

INSERT INTO meals (name, price, menu_id)
VALUES ('Салат Цезарь', 39, 100005),
       ('Пицца Маргарита', 190, 100005),
       ('Пицца Венеция', 362, 100005);

INSERT INTO meals (name, price, menu_id)
VALUES ('Закрытая пицца', 390, 100006),
       ('Роллы', 220, 100006),
       ('Апельсиновый сок', 30, 100006);

-- INSERT INTO meals (name, price, menu_id)
-- VALUES ('Pork', 1000, 100004),
--        ('Steak Vesuvius', 350, 100004),
--        ('Pizza Valentine', 362, 100004);
--
-- INSERT INTO meals (name, price, menu_id)
-- VALUES ('Salad Caesar', 39, 100005),
--        ('Pizza Margarita', 190, 100005),
--        ('Pizza Venice', 362, 100005);

INSERT INTO votes (date, time, user_id, restaurant_id)
VALUES ('2020-05-07', '10:30:00', 100000, 100002);
--        ('2020-05-07', '10:30:00', 100000, 100003);



