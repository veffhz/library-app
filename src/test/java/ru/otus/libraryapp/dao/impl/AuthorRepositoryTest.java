package ru.otus.libraryapp.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import ru.otus.libraryapp.dao.AuthorRepository;
import ru.otus.libraryapp.domain.Author;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test for AuthorRepository")
@DataJpaTest
@ComponentScan
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("Test return count authors")
    void shouldReturnCorrectCount() {
        long count = authorRepository.count();
        assertEquals(count, 3);
    }

    @Test
    @DisplayName("Test insert new author")
    void shouldInsertNewAuthor() {
        authorRepository.save(new Author("test", "test", "test"));
        assertEquals(authorRepository.count(), 4);
    }

    @Test
    @DisplayName("Test get author by id")
    void shouldGetAuthorById() {
        Author author = authorRepository.findById(5L).get();
        assertEquals(author.getFirstName(), "FirstName");
        assertEquals(author.getLastName(), "LastName");
    }

    @Test
    @DisplayName("Test get author by last name")
    void shouldGetAuthorsByLastName() {
        List<Author> authors = authorRepository.findByLastName("LastName");

        assertEquals(authors.size(), 1);

        Author author = authors.get(0);

        assertEquals(author.getFirstName(), "FirstName");
        assertEquals(author.getLastName(), "LastName");
    }

    @Test
    @DisplayName("Test get all authors")
    void shouldGetAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        Author author = authors.get(0);

        assertEquals(author.getFirstName(), "FirstName");
        assertEquals(author.getLastName(), "LastName");

        author = authors.get(1);

        assertEquals(author.getFirstName(), "FirstName7");
        assertEquals(author.getLastName(), "LastName7");
    }

    @Test
    @DisplayName("Test delete author by id")
    void shouldDeleteAuthorById() {
        authorRepository.deleteById(9L);
        assertEquals(authorRepository.count(), 2);
    }

    @Test
    @DisplayName("Test delete author")
    void shouldDeleteAuthor() {
        authorRepository.delete(authorRepository.findById(9L).get());
        assertEquals(authorRepository.count(), 2);
    }
}