package ru.otus.libraryapp.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class BookTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void saveAndGet() {

        Author author = new Author("FirstName", "MiddleName", "LastName");
        mongoTemplate.insert(author, "authors");

        Genre genre = new Genre("Genre");
        mongoTemplate.insert(genre, "genres");

        Book book = new Book(author, genre, "Best", new Date(), "russian",
                "Test", "Test", "555-555");
        mongoTemplate.insert(book, "books");
        Book bookFromDb = mongoTemplate.findById(book.getId(), Book.class,"books");

        assertEquals(book, bookFromDb);
    }
}