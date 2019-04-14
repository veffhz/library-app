package ru.otus.libraryapp.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import ru.otus.libraryapp.dao.AuthorRepository;
import ru.otus.libraryapp.dao.BookRepository;
import ru.otus.libraryapp.dao.GenreRepository;
import ru.otus.libraryapp.domain.Book;
import ru.otus.libraryapp.domain.Comment;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test for BookRepository")
@DataJpaTest
@ComponentScan
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("Test return count books")
    void shouldReturnCorrectCount() {
        long count = bookRepository.count();
        assertEquals(count, 2);
    }

    @Test
    @DisplayName("Test insert new book")
    void shouldInsertNewBook() {
        bookRepository.save(
                new Book(authorRepository.findById(5L).get(), genreRepository.findById(5L).get(),
                        "Best", new Date(), "russian",
                        "Test", "Test", "555-555"));
        assertEquals(bookRepository.count(), 3);
    }

    @Test
    @DisplayName("Test get book by id")
    void shouldGetBookById() {
        Book book = bookRepository.findById(5L).get();
        assertEquals(book.getBookName(), "Best");
    }

    @Test
    @DisplayName("Test get book by name")
    void shouldGetBookByName() {
        List<Book> books = bookRepository.findByBookName("Best");
        assertEquals(books.size(), 1);

        Book book = books.get(0);
        assertEquals(book.getBookName(), "Best");
    }

    @Test
    @DisplayName("Test get books by part name")
    void shouldGetBooksByPartName() {
        List<Book> books = bookRepository.findByBookNameContaining("est");
        assertEquals(books.size(), 2);
    }

    @Test
    @DisplayName("Test get all books")
    void shouldGetAllBooks() {
        List<Book> books = bookRepository.findAll();
        Book book = books.get(0);

        assertEquals(book.getBookName(), "Best");

        book = books.get(1);

        assertEquals(book.getBookName(), "Best7");
    }

    @Test
    @DisplayName("Test delete book by id")
    void shouldDeleteBookById() {
        bookRepository.deleteById(7L);
        assertEquals(bookRepository.count(), 1);
    }

    @Test
    @DisplayName("Test delete book")
    void shouldDeleteBook() {
        bookRepository.delete(bookRepository.findById(7L).get());
        assertEquals(bookRepository.count(), 1);
    }
}