package ru.otus.libraryapp.service;

import ru.otus.libraryapp.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    long count();
    Optional<Genre> getById(long id);
    List<Genre> getByGenreName(String genreName);
    List<Genre> getAll();
    void deleteById(long id);
    long insert(String genreName);
}
