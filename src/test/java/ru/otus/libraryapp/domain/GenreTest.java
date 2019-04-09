package ru.otus.libraryapp.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class GenreTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void saveAndGet() {
        Genre genre = new Genre("Genre");
        Long id = entityManager.persistAndGetId(genre, Long.class);
        Genre genreFromDb = entityManager.find(Genre.class, id);
        assertEquals(genre, genreFromDb);
    }
}