package ru.otus.libraryapp.service.impl;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import ru.otus.libraryapp.domain.Book;
import ru.otus.libraryapp.service.BookService;

import java.util.List;

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
        List<Book> authors = bookService.getAll();
        assertEquals(authors.size(), 1);
    }

    @Test
    void deleteById() {
        List<Book> books = bookService.getAll();
        int size = books.size();
        int id = bookService.insert(1, 1, "Book",
                "1901-01-01", "russian",
                "Test", "Test", "555-555");
        bookService.deleteById(id);
        books = bookService.getAll();
        assertEquals(books.size(), size);
    }

    @Test
    void insert() {
        int id = bookService.insert(1, 1, "Book",
                "1901-01-01", "russian",
                "Test", "Test", "555-555");
        Book book = bookService.getById(id);
        assertEquals(book.getBookName(), "Book");

        bookService.deleteById(id);
    }
}