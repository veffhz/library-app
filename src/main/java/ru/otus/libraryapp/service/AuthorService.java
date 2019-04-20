package ru.otus.libraryapp.service;

import ru.otus.libraryapp.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    long count();
    Optional<Author> getById(String id);
    List<Author> getByLastName(String name);
    List<Author> getAll();
    void deleteById(String id);
    String insert(String firstName, String middleName, String lastName);
}
