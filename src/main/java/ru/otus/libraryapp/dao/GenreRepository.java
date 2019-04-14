package ru.otus.libraryapp.dao;

import org.springframework.data.repository.CrudRepository;

import ru.otus.libraryapp.domain.Genre;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, String> {
    List<Genre> findAll();
    List<Genre> findByGenreName(String genreName);
}
