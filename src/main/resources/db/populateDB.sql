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
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('us', 'us@gmail.com', '{noop}userlol');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001),
       ('ROLE_ADMIN', 100002);

-- INSERT INTO restaurants (name)
-- VALUES ('Час-Пик'),
--        ('Ташир');

INSERT INTO restaurants (name)
VALUES ('Rush-Hour'),
       ('Tashir');

INSERT INTO menus(date, restaurant_id)
VALUES ('2020-05-14', 100003),
       ('2020-05-14', 100004),
       ('2020-05-08', 100004);

-- INSERT INTO meals (name, price, menu_id)
-- VALUES ('Свинина фламбе', 1000, 100004),
--        ('Стейк Везувий', 350, 100004),
--        ('Пицца Валентинка', 362, 100004);
--
-- INSERT INTO meals (name, price, menu_id)
-- VALUES ('Салат Цезарь', 39, 100005),
--        ('Пицца Маргарита', 190, 100005),
--        ('Пицца Венеция', 362, 100005);
--
-- INSERT INTO meals (name, price, menu_id)
-- VALUES ('Закрытая пицца', 390, 100006),
--        ('Роллы', 220, 100006),
--        ('Апельсиновый сок', 30, 100006);

INSERT INTO meals (name, price, menu_id)
VALUES ('Pork', 1000, 100005),
       ('Steak Vesuvius', 350, 100005),
       ('Pizza Valentine', 362, 100005);

INSERT INTO meals (name, price, menu_id)
VALUES ('Salad Caesar', 39, 100006),
       ('Pizza Margarita', 190, 100006),
       ('Pizza Venice', 362, 100006);

INSERT INTO meals (name, price, menu_id)
VALUES ('Stuff pizza', 390, 100007),
       ('Sushi', 220, 100007),
       ('Orange Juice', 30, 100007);

-- INSERT INTO votes (date, user_id, restaurant_id)
-- VALUES ('2020-05-07', 100000, 100003),
--        ('2020-05-13', 100000, 100004),
--        ('2020-05-07', 100002, 100003),
--        ('2020-05-13', 100002, 100004);

-- INSERT INTO votes (date, user_id, restaurant_id)
-- VALUES ('2020-05-08', 100000, 100004),
--        ('2020-05-13', 100000, 100004),
--        ('2020-05-08', 100002, 100004),
--        ('2020-05-13', 100002, 100004);



