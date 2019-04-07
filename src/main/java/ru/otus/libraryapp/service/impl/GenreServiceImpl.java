package ru.otus.libraryapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.otus.libraryapp.dao.GenreDao;
import ru.otus.libraryapp.domain.Genre;
import ru.otus.libraryapp.service.GenreService;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao dao;

    @Autowired
    public GenreServiceImpl(GenreDao dao) {
        this.dao = dao;
    }

    @Override
    public int count() {
        return dao.count();
    }

    @Override
    public Genre getById(int id) {
        return dao.getById(id);
    }

    @Override
    public List<Genre> getByGenreName(String genreName) {
        return dao.getByGenreName(genreName);
    }

    @Override
    public List<Genre> getAll() {
        return dao.getAll();
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);
    }

    @Override
    public int insert(String genreName) {
        Genre genre = new Genre(genreName);
        return dao.insert(genre);
    }

}
