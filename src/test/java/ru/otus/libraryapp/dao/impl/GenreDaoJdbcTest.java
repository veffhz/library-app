package ru.otus.libraryapp.dao.impl;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;

import ru.otus.libraryapp.dao.GenreDao;
import ru.otus.libraryapp.domain.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@ComponentScan
class GenreDaoJdbcTest {

    @Autowired
    private GenreDao genreDao;

    @Test
    void count() {
        int count = genreDao.count();
        assertEquals(count, 3);
    }

    @Test
    void insert() {
        genreDao.insert(new Genre("test"));
        assertEquals(genreDao.count(), 4);
    }

    @Test
    void getById() {
        Genre genre = genreDao.getById(5);
        assertEquals(genre.getGenreName(), "Genre");
    }

    @Test
    void getAll() {
        List<Genre> genres = genreDao.getAll();
        Genre genre = genres.get(0);

        assertEquals(genre.getGenreName(), "Genre");

        genre = genres.get(1);

        assertEquals(genre.getGenreName(), "Genre7");
    }

    @Test
    void deleteById() {
        genreDao.deleteById(9);
        assertEquals(genreDao.count(), 2);
    }
}