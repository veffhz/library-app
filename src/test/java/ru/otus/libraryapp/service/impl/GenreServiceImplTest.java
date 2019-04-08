package ru.otus.libraryapp.service.impl;

import org.junit.jupiter.api.Test;

import org.mockito.ArgumentMatchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.libraryapp.dao.GenreDao;
import ru.otus.libraryapp.service.GenreService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class GenreServiceImplTest {

    @Autowired
    private GenreService genreService;

    @MockBean
    private GenreDao genreDao;

    @Test
    void getById() {
        genreService.getById(1);
        verify(genreDao, times(1)).getById(1);
    }

    @Test
    void getAll() {
        genreService.getAll();
        verify(genreDao, times(1)).getAll();
    }

    @Test
    void deleteById() {
        genreService.deleteById(1);
        verify(genreDao, times(1)).deleteById(1);
    }

    @Test
    void insert() {
        genreService.insert("test");
        verify(genreDao, times(1)).insert(ArgumentMatchers.any());
    }
}