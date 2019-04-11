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

@DisplayName("Test for BookRepositoryJpa")
@DataJpaTest
@ComponentScan
class BookRepositoryJdbcTest {

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
        bookRepository.insert(
                new Book(authorRepository.getById(5), genreRepository.getById(5),
                        "Best", new Date(), "russian",
                        "Test", "Test", "555-555"));
        assertEquals(bookRepository.count(), 3);
    }

    @Test
    @DisplayName("Test get book by id")
    void shouldGetBookById() {
        Book book = bookRepository.getById(5);
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
        bookRepository.deleteById(7);
        assertEquals(bookRepository.count(), 1);
    }

    @Test
    @DisplayName("Test delete book")
    void shouldDeleteBook() {
        bookRepository.delete(bookRepository.getById(7));
        assertEquals(bookRepository.count(), 1);
    }

    @Test
    @DisplayName("Test add comment")
    void shouldAddCommentToBook() {
        Comment comment = new Comment("author", new Date(), "content");
        Book book = bookRepository.getById(5);

        comment.setBook(book);
        bookRepository.insert(comment, book.getId());

        List<Comment> comments = bookRepository.getByBookId(5);

        assertEquals(comments.size(), 1);
    }
}