package ru.otus.libraryapp.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import ru.otus.libraryapp.dao.GenreRepository;
import ru.otus.libraryapp.domain.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test for GenreRepositoryJpa")
@DataJpaTest
@ComponentScan
class GenreRepositoryJdbcTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("Test return count genres")
    void shouldReturnCorrectCount() {
        long count = genreRepository.count();
        assertEquals(count, 3);
    }

    @Test
    @DisplayName("Test insert new genre")
    void shouldInsertNewGenre() {
        genreRepository.insert(new Genre("test"));
        assertEquals(genreRepository.count(), 4);
    }

    @Test
    @DisplayName("Test get genre by id")
    void shouldGetGenreById() {
        Genre genre = genreRepository.getById(5);
        assertEquals(genre.getGenreName(), "Genre");
    }

    @Test
    @DisplayName("Test get all genre")
    void shouldGetAllGenres() {
        List<Genre> genres = genreRepository.getAll();
        Genre genre = genres.get(0);

        assertEquals(genre.getGenreName(), "Genre");

        genre = genres.get(1);

        assertEquals(genre.getGenreName(), "Genre7");
    }

    @Test
    @DisplayName("Test delete genre by id")
    void shouldDeleteGenreById() {
        genreRepository.deleteById(9);
        assertEquals(genreRepository.count(), 2);
    }

    @Test
    @DisplayName("Test delete genre")
    void shouldDeleteGenre() {
        genreRepository.delete(genreRepository.getById(9));
        assertEquals(genreRepository.count(), 2);
    }
}