package ru.otus.libraryapp.dao;

import ru.otus.libraryapp.domain.Genre;

import java.util.List;

public interface GenreDao {
    int count();
    int insert(Genre genre);
    Genre getById(int id);
    List<Genre> getByGenreName(String genreName);
    List<Genre> getAll();
    void deleteById(int id);
}
