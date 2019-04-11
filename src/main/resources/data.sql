insert into author (id, `first_name`, `middle_name`, `last_name`) values (1, 'Роберт', NULL, 'Шекли');
insert into author (id, `first_name`, `middle_name`, `last_name`) values (2, 'Агата', NULL, 'Кристи');

insert into genre (id, `genre_name`) values (1, 'Фантастика');
insert into genre (id, `genre_name`) values (2, 'Детектив');

insert into book (id, author_id, genre_id, book_name, publish_date, `language`, publishing_house, city, isbn)
values (1, 1, 1, 'Избранное', '1991-01-01', 'Русский', 'Мир', 'Москва', '5-03002745-9');

insert into book (id, author_id, genre_id, book_name, publish_date, `language`, publishing_house, city, isbn)
values (2, 2, 2, 'Десять негритят', '2017-01-01', 'Русский', 'Эксмо-Пресс', 'Москва', ' 978-5-699-83193-7');


insert into `comment` (id, book_id, author, `date`, content) values (1, 1, 'Me', '2018-01-01', 'Очень');
insert into `comment` (id, book_id, author, `date`, content) values (2, 1, 'Anonymous', '2019-01-01', 'Cool!');

insert into `comment` (id, book_id, author, `date`, content) values (3, 2, 'Me', '2018-01-01', 'Хорошо');
insert into `comment` (id, book_id, author, `date`, content) values (4, 2, 'Anonymous', '2019-01-01', 'Nice!');