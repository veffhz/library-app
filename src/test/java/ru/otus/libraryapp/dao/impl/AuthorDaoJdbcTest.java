package ru.otus.libraryapp.dao.impl;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;

import ru.otus.libraryapp.dao.AuthorDao;
import ru.otus.libraryapp.domain.Author;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@ComponentScan
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDao authorDao;

    @Test
    void count() {
        int count = authorDao.count();
        assertEquals(count, 3);
    }

    @Test
    void insert() {
        authorDao.insert(new Author("test", "test", "test"));
        assertEquals(authorDao.count(), 4);
    }

    @Test
    void getById() {
        Author author = authorDao.getById(5);
        assertEquals(author.getFirstName(), "FirstName");
        assertEquals(author.getLastName(), "LastName");
    }

    @Test
    void getByLastName() {
        List<Author> authors = authorDao.getByLastName("LastName");

        assertEquals(authors.size(), 1);

        Author author = authors.get(0);

        assertEquals(author.getFirstName(), "FirstName");
        assertEquals(author.getLastName(), "LastName");
    }

    @Test
    void getAll() {
        List<Author> authors = authorDao.getAll();
        Author author = authors.get(0);

        assertEquals(author.getFirstName(), "FirstName");
        assertEquals(author.getLastName(), "LastName");

        author = authors.get(1);

        assertEquals(author.getFirstName(), "FirstName7");
        assertEquals(author.getLastName(), "LastName7");
    }

    @Test
    void deleteById() {
        authorDao.deleteById(9);
        assertEquals(authorDao.count(), 2);
    }
}