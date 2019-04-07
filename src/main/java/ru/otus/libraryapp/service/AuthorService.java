package ru.otus.libraryapp.service;

import ru.otus.libraryapp.domain.Author;

import java.util.List;

public interface AuthorService {
    int count();
    Author getById(int id);
    List<Author> getByLastName(String name);
    List<Author> getAll();
    void deleteById(int id);
    int insert(String firstName, String middleName, String lastName);
}
