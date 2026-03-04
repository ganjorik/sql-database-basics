
CREATE DATABASE peopledenis;
USE peopledenis;

CREATE TABLE person
(
    id             int auto_increment
        primary key,
    age            int           null,
    salary         decimal(6, 2) null,
    passport       char(10)      null,
    adress         varchar(200)  not null,
    dateOfBirthday date          not null,
    dateTimeCreate timestamp     not null,
    timeToLunch    time          null,
    letter         text          null
);


INSERT INTO peopledenis.person (id, age, salary, passport, adress, dateOfBirthday, dateTimeCreate, timeToLunch, letter) VALUES (1, 25, 1500.50, 'MP47896523', 'Минск, Голубева, 25', '2000-02-08', '2021-08-19 09:10:43', '11:45:00', 'Здесь текст большого объема номер 1');
INSERT INTO peopledenis.person (id, age, salary, passport, adress, dateOfBirthday, dateTimeCreate, timeToLunch, letter) VALUES (2, 44, 3200.00, 'DE84569721', 'Витебск, Пушкина, 41', '1982-09-09', '2010-01-25 10:15:25', '12:00:00', 'Здесь текст большого объема номер 2');
INSERT INTO peopledenis.person (id, age, salary, passport, adress, dateOfBirthday, dateTimeCreate, timeToLunch, letter) VALUES (3, 37, 2980.30, 'NR64589741', 'Мозырь, Кеника, 17', '1989-07-13', '2014-03-30 08:21:42', '12:30:00', 'Здесь текст большого объема номер 3');
INSERT INTO peopledenis.person (id, age, salary, passport, adress, dateOfBirthday, dateTimeCreate, timeToLunch, letter) VALUES (4, 18, 980.00, 'GD58796321', 'Брест, Фрунзе, 15', '2008-01-07', '2014-11-13 09:21:27', '13:00:00', 'Здесь текст большого объема номер 4');
INSERT INTO peopledenis.person (id, age, salary, passport, adress, dateOfBirthday, dateTimeCreate, timeToLunch, letter) VALUES (5, 20, 1360.80, 'TA69877218', 'Могилев, Куйбышева, 7', '2005-11-30', '2022-05-23 12:22:35', '13:30:00', 'Здесь текст большого объема номер 5');


SELECT * FROM person WHERE age > 21 ORDER BY dateTimeCreate;
