package ru.otus.libraryapp.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import ru.otus.libraryapp.domain.Author;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {
    List<Author> findByLastName(String lastName);
}
