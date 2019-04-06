package ru.otus.libraryapp.service;

import ru.otus.libraryapp.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre getById(int id);
    List<Genre> getByGenreName(String genreName);
    List<Genre> getAll();
    void deleteById(int id);
    int insert(String genreName);
}
