package ru.otus.libraryapp.dao;

import ru.otus.libraryapp.domain.Genre;

import java.util.List;

public interface GenreDao {
    int count();
    long insert(Genre genre);
    Genre getById(long id);
    List<Genre> getByGenreName(String genreName);
    List<Genre> getAll();
    void deleteById(long id);
}
