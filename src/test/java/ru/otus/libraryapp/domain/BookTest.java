package ru.otus.libraryapp.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BookTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void saveAndGet() {
        Book book = new Book(new Author(), new Genre(), "Best", new Date(), "russian",
                "Test", "Test", "555-555");
        Long id = entityManager.persistAndGetId(book, Long.class);
        Book bookFromDb = entityManager.find(Book.class, id);
        assertEquals(book, bookFromDb);
    }
}