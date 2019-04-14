package ru.otus.libraryapp.config;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;

import com.mongodb.*;

import ru.otus.libraryapp.service.impl.Utils;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "addAuthors", author = "veffhz")
    public void insertAuthors(DB db) {
        DBCollection authorsCollection = db.getCollection("authors");

        BasicDBObject author1 = new BasicDBObject();
        author1.append("firstName", "Роберт");
        author1.append("lastName", "Шекли");
        authorsCollection.insert(author1);

        BasicDBObject author2 = new BasicDBObject();
        author2.append("firstName", "Агата");
        author2.append("lastName", "Кристи");
        authorsCollection.insert(author2);
    }

    @ChangeSet(order = "002", id = "addGenres", author = "veffhz")
    public void insertGenres(DB db) {
        DBCollection genresCollection = db.getCollection("genres");

        BasicDBObject genre1 = new BasicDBObject();
        genre1.append("genreName", "Фантастика");
        genresCollection.insert(genre1);

        BasicDBObject genre2 = new BasicDBObject();
        genre2.append("genreName", "Детектив");
        genresCollection.insert(genre2);
    }

    @ChangeSet(order = "003", id = "addBooks", author = "veffhz")
    public void insertBooks(DB db) {
        DBCollection booksCollection = db.getCollection("books");
        BasicDBObject book1 = new BasicDBObject();
        book1.append("bookName", "Избранное");
        book1.append("publishDate", Utils.toDate("1991-01-01"));
        book1.append("language", "Русский");
        book1.append("publishingHouse", "Мир");
        book1.append("city", "Москва");
        book1.append("isbn", "5-03002745-9");

        DBCollection authorsCollection = db.getCollection("authors");
        System.out.println("authorsCollection " + authorsCollection);
        DBObject author1 = authorsCollection.findOne(new BasicDBObject("lastName", "Шекли"));
        System.out.println("author _id " + author1.get("_id"));
        DBRef refAuthor1 = new DBRef("authors", author1.get("_id"));
        book1.append("author", refAuthor1);

        DBCollection genresCollection = db.getCollection("genres");
        DBObject genre1 = genresCollection.findOne(new BasicDBObject("genreName", "Фантастика"));
        DBRef refGenre1 = new DBRef("genres", genre1.get("_id"));
        book1.append("genre", refGenre1);

        booksCollection.insert(book1);

        // ------------------------------------------------------------------------------------------

        BasicDBObject book2 = new BasicDBObject();
        book2.append("bookName", "Десять негритят");
        book2.append("publishDate", Utils.toDate("2017-01-01"));
        book2.append("language", "Русский");
        book2.append("publishingHouse", "Эксмо-Пресс");
        book2.append("city", "Москва");
        book2.append("isbn", "978-5-699-83193-7");

        DBObject author2 = authorsCollection.findOne(new BasicDBObject("lastName", "Кристи"));
        DBRef refAuthor2 = new DBRef("authors", author2.get("_id"));
        book1.append("author", refAuthor2);

        DBObject genre2 = genresCollection.findOne(new BasicDBObject("genreName", "Детектив"));
        DBRef refGenre2 = new DBRef("genres", genre2.get("_id"));
        book1.append("genre", refGenre2);

        booksCollection.insert(book2);
    }
}
