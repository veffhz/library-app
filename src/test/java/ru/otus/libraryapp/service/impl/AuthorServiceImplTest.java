package ru.otus.libraryapp.service.impl;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import ru.otus.libraryapp.domain.Author;
import ru.otus.libraryapp.service.AuthorService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
@TestPropertySource("classpath:application-test.yml")
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
        int id = authorService.insert("Test", null, "Test");
        authorService.deleteById(id);
        int newSize = authorService.count();
        assertEquals(newSize, size);
    }

    @Test
    void insert() {
        int id = authorService.insert("Name", null, "Test");
        Author author = authorService.getById(id);
        assertEquals(author.getFirstName(), "Name");
        assertEquals(author.getLastName(), "Test");

        authorService.deleteById(id);
    }
}