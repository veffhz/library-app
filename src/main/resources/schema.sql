DROP TABLE IF EXISTS AUTHORS;
CREATE TABLE AUTHORS(ID BIGINT IDENTITY PRIMARY KEY, FIRST_NAME VARCHAR(255), MIDDLE_NAME VARCHAR(255), LAST_NAME VARCHAR(255));

DROP TABLE IF EXISTS GENRES;
CREATE TABLE GENRES(ID BIGINT IDENTITY PRIMARY KEY, GENRE_NAME VARCHAR(255));

DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS(ID BIGINT IDENTITY PRIMARY KEY, AUTHOR_ID BIGINT NOT NULL, GENRE_ID BIGINT NOT NULL, BOOK_NAME VARCHAR(255), PUBLISH_DATE DATE, LANGUAGE VARCHAR(255), PUBLISHING_HOUSE VARCHAR(255), CITY VARCHAR(255), ISBN VARCHAR(255),
FOREIGN KEY(AUTHOR_ID) REFERENCES AUTHORS(ID),
FOREIGN KEY(GENRE_ID) REFERENCES GENRES(ID));

DROP TABLE IF EXISTS COMMENTS;
CREATE TABLE COMMENTS(ID BIGINT IDENTITY PRIMARY KEY, AUTHOR VARCHAR(255), BOOK_ID BIGINT NOT NULL, `DATE` DATE, CONTENT VARCHAR(255),
FOREIGN KEY(BOOK_ID) REFERENCES BOOKS(ID));