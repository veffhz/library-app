package ru.otus.libraryapp.service.impl;

import org.junit.jupiter.api.Test;

import org.mockito.ArgumentMatchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.libraryapp.dao.BookDao;
import ru.otus.libraryapp.service.BookService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookDao bookDao;

    @Test
    void getById() {
        bookService.getById(1);
        verify(bookDao, times(1)).getById(1);
    }

    @Test
    void getAll() {
        bookService.getAll();
        verify(bookDao, times(1)).getAll();
    }

    @Test
    void deleteById() {
        bookService.deleteById(1);
        verify(bookDao, times(1)).deleteById(1);
    }

    @Test
    void insert() {
        bookService.insert(1, 1, "Book",
                "1901-01-01", "russian",
                "Test", "Test", "555-555");
        verify(bookDao, times(1)).insert(ArgumentMatchers.any());
    }
}