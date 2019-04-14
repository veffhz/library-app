package ru.otus.libraryapp.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentMatchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.libraryapp.dao.BookRepository;
import ru.otus.libraryapp.domain.Author;
import ru.otus.libraryapp.domain.Book;
import ru.otus.libraryapp.domain.Genre;
import ru.otus.libraryapp.service.BookService;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Test for Book Service")
@SpringBootTest
class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Test
    @DisplayName("Test invoke get book by id")
    void shouldGetBookById() {
        Book bookMock = new Book(new Author("", null, ""),
                new Genre(""), "Book",
                new Date(), "russian",
                "Test", "Test", "555-555");
        when(bookRepository.findById(any(String.class))).thenReturn(Optional.of(bookMock));

        Book book = bookService.getById("000").get();

        verify(bookRepository, times(1)).findById("000");
        assertEquals(bookMock, book);
    }

    @Test
    @DisplayName("Test invoke get all books")
    void shouldGetAllBooks() {
        bookService.getAll();
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test invoke delete book by id")
    void shouldDeleteBookById() {
        bookService.deleteById("000");
        verify(bookRepository, times(1)).deleteById("000");
    }

    @Test
    @DisplayName("Test invoke insert new book")
    void shouldInsertNewBook() {
        bookService.insert("000", "000", "Book",
                "1901-01-01", "russian",
                "Test", "Test", "555-555");
        verify(bookRepository, times(1)).save(ArgumentMatchers.any());
    }
}