package ru.otus.libraryapp.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;

import ru.otus.libraryapp.dao.AuthorDao;
import ru.otus.libraryapp.domain.Author;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test for AuthorDaoJdbc")
@JdbcTest
@ComponentScan
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDao authorDao;

    @Test
    @DisplayName("Test return count authors")
    void shouldReturnCorrectCount() {
        int count = authorDao.count();
        assertEquals(count, 3);
    }

    @Test
    @DisplayName("Test insert new author")
    void shouldInsertNewAuthor() {
        authorDao.insert(new Author("test", "test", "test"));
        assertEquals(authorDao.count(), 4);
    }

    @Test
    @DisplayName("Test get author by id")
    void shouldGetAuthorById() {
        Author author = authorDao.getById(5);
        assertEquals(author.getFirstName(), "FirstName");
        assertEquals(author.getLastName(), "LastName");
    }

    @Test
    @DisplayName("Test get author by last name")
    void shouldGetAuthorsByLastName() {
        List<Author> authors = authorDao.getByLastName("LastName");

        assertEquals(authors.size(), 1);

        Author author = authors.get(0);

        assertEquals(author.getFirstName(), "FirstName");
        assertEquals(author.getLastName(), "LastName");
    }

    @Test
    @DisplayName("Test get all authors")
    void shouldGetAllAuthors() {
        List<Author> authors = authorDao.getAll();
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
        authorDao.deleteById(9);
        assertEquals(authorDao.count(), 2);
    }
}