package ru.otus.libraryapp.dao;

import ru.otus.libraryapp.domain.Author;

import java.util.List;

public interface AuthorDao {
    int count();
    int insert(Author author);
    Author getById(int id);
    List<Author> getByLastName(String name);
    List<Author> getAll();
    void deleteById(int id);
}
