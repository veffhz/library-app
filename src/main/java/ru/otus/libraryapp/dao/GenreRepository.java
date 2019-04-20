package ru.otus.libraryapp.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import ru.otus.libraryapp.domain.Genre;

import java.util.List;

public interface GenreRepository extends MongoRepository<Genre, String> {
    List<Genre> findByGenreName(String genreName);
}
