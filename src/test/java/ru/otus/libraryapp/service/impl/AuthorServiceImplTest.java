package ru.otus.libraryapp.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentMatchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.libraryapp.dao.AuthorRepository;
import ru.otus.libraryapp.domain.Author;
import ru.otus.libraryapp.service.AuthorService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Test for Author Service")
@SpringBootTest
class AuthorServiceImplTest {

    @Autowired
    private AuthorService authorService;

    @MockBean
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("Test invoke get author by id")
    void shouldGetAuthorById() {
        Author authorMock = new Author("test", "test", "test");
        when(authorRepository.findById(any(Long.class))).thenReturn(Optional.of(authorMock));

        Author author = authorService.getById(1L).get();

        verify(authorRepository, times(1)).findById(1L);
        assertEquals(authorMock, author);
    }

    @Test
    @DisplayName("Test invoke get all authors")
    void shouldGetAllAuthors() {
        authorService.getAll();
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test invoke delete author by id")
    void shouldDeleteAuthorById() {
        authorService.deleteById(1);
        verify(authorRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Test invoke insert new author")
    void shouldInsertNewAuthor() {
        when(authorRepository.save(any(Author.class))).thenReturn(ArgumentMatchers.any());
        authorService.insert("test", "test", "test");
        verify(authorRepository, times(1)).save(ArgumentMatchers.any());
    }
}