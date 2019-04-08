package ru.otus.libraryapp.service.impl;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.otus.libraryapp.domain.Author;
import ru.otus.libraryapp.service.AuthorService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AuthorServiceImplTest {

    @Autowired
    private AuthorService authorService;

    @Test
    void getById() {
        Author author = authorService.getById(1);
        assertEquals(author.getFirstName(), "Роберт");
        assertEquals(author.getLastName(), "Шекли");
    }

    @Test
    void getAll() {
        int size = authorService.count();
        assertEquals(size, 1);
    }

    @Test
    void deleteById() {
        int size = authorService.count();
        long id = authorService.insert("Test", null, "Test");
        authorService.deleteById(id);
        int newSize = authorService.count();
        assertEquals(newSize, size);
    }

    @Test
    void insert() {
        long id = authorService.insert("Name", null, "Test");
        Author author = authorService.getById(id);
        assertEquals(author.getFirstName(), "Name");
        assertEquals(author.getLastName(), "Test");

        authorService.deleteById(id);
    }
}