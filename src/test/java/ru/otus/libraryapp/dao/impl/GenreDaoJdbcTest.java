package ru.otus.libraryapp.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;

import ru.otus.libraryapp.dao.GenreDao;
import ru.otus.libraryapp.domain.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test for GenreDaoJdbcTest")
@JdbcTest
@ComponentScan
class GenreDaoJdbcTest {

    @Autowired
    private GenreDao genreDao;

    @Test
    @DisplayName("Test return count genres")
    void shouldReturnCorrectCount() {
        int count = genreDao.count();
        assertEquals(count, 3);
    }

    @Test
    @DisplayName("Test insert new genre")
    void shouldInsertNewGenre() {
        genreDao.insert(new Genre("test"));
        assertEquals(genreDao.count(), 4);
    }

    @Test
    @DisplayName("Test get genre by id")
    void shouldGetGenreById() {
        Genre genre = genreDao.getById(5);
        assertEquals(genre.getGenreName(), "Genre");
    }

    @Test
    @DisplayName("Test get all genre")
    void shouldGetAllGenres() {
        List<Genre> genres = genreDao.getAll();
        Genre genre = genres.get(0);

        assertEquals(genre.getGenreName(), "Genre");

        genre = genres.get(1);

        assertEquals(genre.getGenreName(), "Genre7");
    }

    @Test
    @DisplayName("Test delete genre by id")
    void shouldDeleteGenreById() {
        genreDao.deleteById(9);
        assertEquals(genreDao.count(), 2);
    }
}