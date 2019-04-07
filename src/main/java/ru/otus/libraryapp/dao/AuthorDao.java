package ru.otus.libraryapp.dao;

import ru.otus.libraryapp.domain.Author;

import java.util.List;

public interface AuthorDao {
    int count();
    long insert(Author author);
    Author getById(long id);
    List<Author> getByLastName(String name);
    List<Author> getAll();
    void deleteById(long id);
}
