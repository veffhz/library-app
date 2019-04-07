package ru.otus.libraryapp.service.impl;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.otus.libraryapp.dao.AuthorDao;
import ru.otus.libraryapp.domain.Author;
import ru.otus.libraryapp.service.AuthorService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao dao;

    @Autowired
    public AuthorServiceImpl(AuthorDao dao) {
        this.dao = dao;
    }

    @Override
    public int count() {
        return dao.count();
    }

    @Override
    public Author getById(long id) {
        return dao.getById(id);
    }

    @Override
    public List<Author> getByLastName(String lastName) {
        return dao.getByLastName(lastName);
    }

    @Override
    public List<Author> getAll() {
        return dao.getAll();
    }

    @Override
    public void deleteById(long id) {
        dao.deleteById(id);
    }

    @Override
    public long insert(String firstName, String middleName, String lastName) {
        Author author = new Author(firstName, Strings.isBlank(middleName) ? null : middleName, lastName);
        return dao.insert(author);
    }

}
