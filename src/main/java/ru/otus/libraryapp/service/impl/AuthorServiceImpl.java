package ru.otus.libraryapp.service.impl;

import org.apache.logging.log4j.util.Strings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.otus.libraryapp.dao.AuthorRepository;
import ru.otus.libraryapp.domain.Author;
import ru.otus.libraryapp.service.AuthorService;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Optional<Author> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Author> getByLastName(String lastName) {
        return repository.findByLastName(lastName);
    }

    @Override
    public List<Author> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public String insert(String firstName, String middleName, String lastName) {
        Author author = new Author(firstName, Strings.isBlank(middleName) ? null : middleName, lastName);
        Author authorDb = repository.save(author);
        return authorDb.getId();
    }

}
