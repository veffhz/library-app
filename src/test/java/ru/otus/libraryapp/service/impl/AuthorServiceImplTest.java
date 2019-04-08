package ru.otus.libraryapp.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentMatchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.libraryapp.dao.AuthorDao;
import ru.otus.libraryapp.service.AuthorService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Test for Author Service")
@SpringBootTest
class AuthorServiceImplTest {

    @Autowired
    private AuthorService authorService;

    @MockBean
    private AuthorDao authorDao;

    @Test
    @DisplayName("Test invoke get author by id")
    void shouldGetAuthorById() {
        authorService.getById(1);
        verify(authorDao, times(1)).getById(1);
    }

    @Test
    @DisplayName("Test invoke get all authors")
    void shouldGetAllAuthors() {
        authorService.getAll();
        verify(authorDao, times(1)).getAll();
    }

    @Test
    @DisplayName("Test invoke delete author by id")
    void shouldDeleteAuthorById() {
        authorService.deleteById(1);
        verify(authorDao, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Test invoke insert new author")
    void shouldInsertNewAuthor() {
        authorService.insert("test", "test", "test");
        verify(authorDao, times(1)).insert(ArgumentMatchers.any());
    }
}