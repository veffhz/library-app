insert into authors (id, `first_name`, `middle_name`, `last_name`) values (1, 'Роберт', NULL, 'Шекли');

insert into genres (id, `genre_name`) values (1, 'Фантастика');

insert into books (id, author_id, genre_id, book_name, publish_date, `language`, publishing_house, city, isbn)
values (1, 1, 1, 'Избранное', '1991-01-01', 'Русский', 'Мир', 'Москва', '5-03002745-9');
