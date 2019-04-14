package ru.otus.libraryapp.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.libraryapp.dao.GenreRepository;
import ru.otus.libraryapp.domain.Genre;
import ru.otus.libraryapp.service.GenreService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Test for Genre Service")
@SpringBootTest
class GenreServiceImplTest {

    @Autowired
    private GenreService genreService;

    @MockBean
    private GenreRepository genreRepository;

    @Test
    @DisplayName("Test invoke get genre by id")
    void shouldGetGenreById() {
        Genre genreMock = new Genre("test");
        when(genreRepository.findById(any(String.class))).thenReturn(Optional.of(genreMock));

        Genre genre = genreService.getById("000").get();

        verify(genreRepository, times(1)).findById("000");
        assertEquals(genreMock, genre);
    }

    @Test
    @DisplayName("Test invoke get all genres")
    void shouldGetAllGenres() {
        genreService.getAll();
        verify(genreRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test invoke delete genre by id")
    void shouldDeleteGenreById() {
        genreService.deleteById("000");
        verify(genreRepository, times(1)).deleteById("000");
    }

    @Test
    @DisplayName("Test invoke insert new genre")
    void shouldInsertNewGenre() {
        genreService.insert("test");
        verify(genreRepository, times(1)).save(any());
    }
}