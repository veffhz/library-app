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

@DisplayName("Test for GenreRepository")
@DataJpaTest
@ComponentScan
class GenreRepositoryTest {

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
        genreRepository.save(new Genre("test"));
        assertEquals(genreRepository.count(), 4);
    }

    @Test
    @DisplayName("Test get genre by id")
    void shouldGetGenreById() {
        Genre genre = genreRepository.findAll().get(0);
        assertEquals(genre.getGenreName(), "Genre");
    }

    @Test
    @DisplayName("Test get all genre")
    void shouldGetAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        Genre genre = genres.get(0);

        assertEquals(genre.getGenreName(), "Genre");

        genre = genres.get(1);

        assertEquals(genre.getGenreName(), "Genre7");
    }

    @Test
    @DisplayName("Test delete genre by id")
    void shouldDeleteGenreById() {
        Genre genre = genreRepository.findAll().get(0);
        genreRepository.deleteById(genre.getId());
        assertEquals(genreRepository.count(), 2);
    }

    @Test
    @DisplayName("Test delete genre")
    void shouldDeleteGenre() {
        Genre genre = genreRepository.findAll().get(0);
        genreRepository.delete(genre);
        assertEquals(genreRepository.count(), 2);
    }
}