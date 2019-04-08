package ru.otus.libraryapp.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.libraryapp.dao.GenreDao;
import ru.otus.libraryapp.domain.Genre;
import ru.otus.libraryapp.service.GenreService;

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
    private GenreDao genreDao;

    @Test
    @DisplayName("Test invoke get genre by id")
    void shouldGetGenreById() {
        Genre genreMock = new Genre("test");
        when(genreDao.getById(any(Long.class))).thenReturn(genreMock);

        Genre genre = genreService.getById(1);

        verify(genreDao, times(1)).getById(1);
        assertEquals(genreMock, genre);
    }

    @Test
    @DisplayName("Test invoke get all genre")
    void shouldGetAllGenres() {
        genreService.getAll();
        verify(genreDao, times(1)).getAll();
    }

    @Test
    @DisplayName("Test invoke delete genre by id")
    void shouldDeleteGenreById() {
        genreService.deleteById(1);
        verify(genreDao, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Test invoke insert new genre")
    void shouldInsertNewGenre() {
        genreService.insert("test");
        verify(genreDao, times(1)).insert(any());
    }
}