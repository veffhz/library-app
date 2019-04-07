package ru.otus.libraryapp.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import ru.otus.libraryapp.domain.Genre;
import ru.otus.libraryapp.service.GenreService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
@TestPropertySource("classpath:application-test.yml")
class GenreServiceImplTest {

    @Autowired
    private GenreService genreService;

    @Test
    void getById() {
        Genre genre = genreService.getById(1);
        assertEquals(genre.getGenreName(), "Фантастика");
    }

    @Test
    void getAll() {
        int size = genreService.count();
        assertEquals(size, 1);
    }

    @Test
    void deleteById() {
        int size = genreService.count();
        int id = genreService.insert("Test");
        genreService.deleteById(id);
        int newSize = genreService.count();
        assertEquals(newSize, size);
    }

    @Test
    void insert() {
        int id = genreService.insert("Name");
        Genre author = genreService.getById(id);
        assertEquals(author.getGenreName(), "Name");

        genreService.deleteById(id);
    }
}