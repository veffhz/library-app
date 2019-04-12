package ru.otus.libraryapp.dao;

import ru.otus.libraryapp.domain.Author;

import java.util.List;

public interface AuthorRepository {
    long count();
    long insert(Author author);
    Author getById(long id);
    List<Author> getByLastName(String name);
    List<Author> getAll();
    void delete(Author author);
    void deleteById(long id);
}
