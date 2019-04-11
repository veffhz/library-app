insert into author (id, first_name, middle_name, last_name) values (5, 'FirstName', NULL, 'LastName');
insert into author (id, first_name, middle_name, last_name) values (7, 'FirstName7', NULL, 'LastName7');
insert into author (id, first_name, middle_name, last_name) values (9, 'FirstName7', NULL, 'LastName7');

insert into genre (id, genre_name) values (5, 'Genre');
insert into genre (id, genre_name) values (7, 'Genre7');
insert into genre (id, genre_name) values (9, 'Genre7');

insert into book (id, author_id, genre_id, book_name, publish_date, `language`, publishing_house, city, isbn)
values (5, 5, 5, 'Best', '1991-01-01', 'Russian', 'Test', 'Moscow', '5-03002745-9');

insert into book (id, author_id, genre_id, book_name, publish_date, `language`, publishing_house, city, isbn)
values (7, 7, 7, 'Best7', '1991-01-01', 'Russian', 'Test', 'Moscow', '5-03002745-9');