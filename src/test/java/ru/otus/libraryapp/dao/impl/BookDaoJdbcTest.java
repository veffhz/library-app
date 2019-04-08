package ru.otus.libraryapp.dao.impl;

import org.junit.jupiter.api.DisplayName;
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

@DisplayName("Test for BookDaoJdbcTest")
@JdbcTest
@ComponentScan
class BookDaoJdbcTest {

    @Autowired
    private BookDao bookDao;

    @Test
    @DisplayName("Test return count books")
    void shouldReturnCorrectCount() {
        int count = bookDao.count();
        assertEquals(count, 2);
    }

    @Test
    @DisplayName("Test insert new book")
    void shouldInsertNewBook() {
        bookDao.insert(
                new Book(new Author(5, "", null, ""),
                        new Genre(5, ""), "Book",
                        new Date(), "russian",
                        "Test", "Test", "555-555"));
        assertEquals(bookDao.count(), 3);
    }

    @Test
    @DisplayName("Test get book by id")
    void shouldGetBookById() {
        Book book = bookDao.getById(5);
        assertEquals(book.getBookName(), "Best");
    }

    @Test
    @DisplayName("Test get book by name")
    void shouldGetBookByName() {
        List<Book> books = bookDao.getByBookName("Best");
        assertEquals(books.size(), 1);

        Book book = books.get(0);
        assertEquals(book.getBookName(), "Best");
    }

    @Test
    @DisplayName("Test get books by part name")
    void shouldGetBooksByPartName() {
        List<Book> books = bookDao.getByBookPartName("est");
        assertEquals(books.size(), 2);
    }

    @Test
    @DisplayName("Test get all books")
    void shouldGetAllBooks() {
        List<Book> books = bookDao.getAll();
        Book book = books.get(0);

        assertEquals(book.getBookName(), "Best");

        book = books.get(1);

        assertEquals(book.getBookName(), "Best7");
    }

    @Test
    @DisplayName("Test delete book by id")
    void shouldDeleteBookById() {
        bookDao.deleteById(7);
        assertEquals(bookDao.count(), 1);
    }
}