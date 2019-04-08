package ru.otus.libraryapp.dao.impl;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;

import ru.otus.libraryapp.dao.BookDao;
import ru.otus.libraryapp.domain.Author;
import ru.otus.libraryapp.domain.Book;
import ru.otus.libraryapp.domain.Genre;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@ComponentScan
class BookDaoJdbcTest {

    @Autowired
    private BookDao bookDao;

    @Test
    void count() {
        int count = bookDao.count();
        assertEquals(count, 2);
    }

    @Test
    void insert() {
        bookDao.insert(
                new Book(new Author(5, "", null, ""),
                        new Genre(5, ""), "Book",
                        new Date(), "russian",
                        "Test", "Test", "555-555"));
        assertEquals(bookDao.count(), 3);
    }

    @Test
    void getById() {
        Book book = bookDao.getById(5);
        assertEquals(book.getBookName(), "Best");
    }

    @Test
    void getByBookName() {
        List<Book> books = bookDao.getByBookName("Best");
        assertEquals(books.size(), 1);

        Book book = books.get(0);
        assertEquals(book.getBookName(), "Best");
    }

    @Test
    void getByBookPartName() {
        List<Book> books = bookDao.getByBookPartName("est");
        assertEquals(books.size(), 2);
    }

    @Test
    void getAll() {
        List<Book> books = bookDao.getAll();
        Book book = books.get(0);

        assertEquals(book.getBookName(), "Best");

        book = books.get(1);

        assertEquals(book.getBookName(), "Best7");
    }

    @Test
    void deleteById() {
        bookDao.deleteById(7);
        assertEquals(bookDao.count(), 1);
    }
}