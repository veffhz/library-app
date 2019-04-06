insert into authors (id, `first_name`, `middle_name`, `last_name`) values (5, 'FirstName', NULL, 'LastName');

insert into genres (id, `genre_name`) values (5, 'Genre');

insert into books (id, author_id, genre_id, book_name, publish_date, `language`, publishing_house, city, isbn)
values (5, 5, 5, 'Best', '1991-01-01', 'Russian', 'Test', 'Moscow', '5-03002745-9');
