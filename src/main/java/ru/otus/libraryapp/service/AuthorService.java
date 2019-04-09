package ru.otus.libraryapp.service;

import ru.otus.libraryapp.domain.Author;

import java.util.List;

public interface AuthorService {
    long count();
    Author getById(long id);
    List<Author> getByLastName(String name);
    List<Author> getAll();
    void deleteById(long id);
    long insert(String firstName, String middleName, String lastName);
}
