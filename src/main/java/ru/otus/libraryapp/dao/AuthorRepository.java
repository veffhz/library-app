package ru.otus.libraryapp.dao;

import org.springframework.data.repository.CrudRepository;

import ru.otus.libraryapp.domain.Author;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, String> {
    List<Author> findAll();
    List<Author> findByLastName(String lastName);
}
