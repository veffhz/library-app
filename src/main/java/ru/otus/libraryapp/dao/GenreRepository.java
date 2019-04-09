package ru.otus.libraryapp.dao;

import ru.otus.libraryapp.domain.Genre;

import java.util.List;

public interface GenreRepository {
    long count();
    long insert(Genre genre);
    Genre getById(long id);
    List<Genre> getByGenreName(String genreName);
    List<Genre> getAll();
    void delete(Genre genre);
    void deleteById(long id);
}
