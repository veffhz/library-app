package ru.otus.libraryapp.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AuthorTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void saveAndGet() {
        Author author = new Author("FirstName", "MiddleName", "LastName");
        Long id = entityManager.persistAndGetId(author, Long.class);
        Author authorFromDb = entityManager.find(Author.class, id);
        assertEquals(author, authorFromDb);
    }
}