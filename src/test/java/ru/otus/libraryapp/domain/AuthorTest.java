package ru.otus.libraryapp.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class AuthorTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void saveAndGet() {
        Author author = new Author("FirstName", "MiddleName", "LastName");
        mongoTemplate.insert(author, "authors");

        Author authorFromDb = mongoTemplate.findById(author.getId(), Author.class,"authors");

        assertEquals(author, authorFromDb);
    }
}