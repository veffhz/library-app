package ru.otus.libraryapp.service.impl;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import ru.otus.libraryapp.domain.Book;
import ru.otus.libraryapp.service.BookService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
@TestPropertySource("classpath:application-test.yml")
class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Test
    void getById() {
        Book book = bookService.getById(1);
        assertEquals(book.getBookName(), "Избранное");
    }

    @Test
    void getAll() {
        int size = bookService.count();
        assertEquals(size, 1);
    }

    @Test
    void deleteById() {
        int size = bookService.count();
        long id = bookService.insert(1, 1, "Book",
                "1901-01-01", "russian",
                "Test", "Test", "555-555");
        bookService.deleteById(id);
        int newSize = bookService.count();
        assertEquals(newSize, size);
    }

    @Test
    void insert() {
        long id = bookService.insert(1, 1, "Book",
                "1901-01-01", "russian",
                "Test", "Test", "555-555");
        Book book = bookService.getById(id);
        assertEquals(book.getBookName(), "Book");

        bookService.deleteById(id);
    }
}