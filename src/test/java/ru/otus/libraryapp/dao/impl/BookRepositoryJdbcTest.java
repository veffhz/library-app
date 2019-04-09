package ru.otus.libraryapp.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import ru.otus.libraryapp.dao.AuthorRepository;
import ru.otus.libraryapp.dao.BookRepository;
import ru.otus.libraryapp.dao.GenreRepository;
import ru.otus.libraryapp.domain.Author;
import ru.otus.libraryapp.domain.Book;
import ru.otus.libraryapp.domain.Genre;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test for BookRepositoryJpa")
@DataJpaTest
@ComponentScan
class BookRepositoryJdbcTest {

    private long[] ids = new long[3];

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @BeforeEach
    void setUp() {
        Author author = new Author("First", null, "Last");
        authorRepository.insert(author);
        Genre genre = new Genre("Test");
        genreRepository.insert(genre);

        ids[0] = bookRepository.insert(new Book(author, genre, "Best", new Date(), "russian",
                "Test", "Test", "555-555"));
        ids[1] = bookRepository.insert(new Book(author, genre, "Best7", new Date(), "russian",
                "Test", "Test", "555-555"));
    }

    @Test
    @DisplayName("Test return count books")
    void shouldReturnCorrectCount() {
        long count = bookRepository.count();
        assertEquals(count, 2);
    }

    @Test
    @DisplayName("Test insert new book")
    void shouldInsertNewBook() {
        bookRepository.insert(
                new Book(authorRepository.getByLastName("Last").get(0),
                        genreRepository.getByGenreName("Test").get(0),
                        "Best", new Date(), "russian",
                        "Test", "Test", "555-555"));
        assertEquals(bookRepository.count(), 3);
    }

    @Test
    @DisplayName("Test get book by id")
    void shouldGetBookById() {
        Book book = bookRepository.getById(ids[0]);
        assertEquals(book.getBookName(), "Best");
    }

    @Test
    @DisplayName("Test get book by name")
    void shouldGetBookByName() {
        List<Book> books = bookRepository.getByBookName("Best");
        assertEquals(books.size(), 1);

        Book book = books.get(0);
        assertEquals(book.getBookName(), "Best");
    }

    @Test
    @DisplayName("Test get books by part name")
    void shouldGetBooksByPartName() {
        List<Book> books = bookRepository.getByBookPartName("est");
        assertEquals(books.size(), 2);
    }

    @Test
    @DisplayName("Test get all books")
    void shouldGetAllBooks() {
        List<Book> books = bookRepository.getAll();
        Book book = books.get(0);

        assertEquals(book.getBookName(), "Best");

        book = books.get(1);

        assertEquals(book.getBookName(), "Best7");
    }

    @Test
    @DisplayName("Test delete book by id")
    void shouldDeleteBookById() {
        bookRepository.deleteById(ids[1]);
        assertEquals(bookRepository.count(), 1);
    }

    @Test
    @DisplayName("Test delete book")
    void shouldDeleteBook() {
        bookRepository.delete(bookRepository.getById(ids[0]));
        assertEquals(bookRepository.count(), 1);
    }
}