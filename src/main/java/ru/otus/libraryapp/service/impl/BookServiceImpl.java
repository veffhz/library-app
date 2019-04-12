package ru.otus.libraryapp.service.impl;

import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.otus.libraryapp.dao.BookRepository;
import ru.otus.libraryapp.domain.Author;
import ru.otus.libraryapp.domain.Book;
import ru.otus.libraryapp.domain.Genre;
import ru.otus.libraryapp.service.AuthorService;
import ru.otus.libraryapp.service.BookService;
import ru.otus.libraryapp.service.GenreService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static ru.otus.libraryapp.service.impl.Utils.toDate;

@Log
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    private final AuthorService authorService;
    private final GenreService genreService;

    @Autowired
    public BookServiceImpl(BookRepository repository, AuthorService authorService, GenreService genreService) {
        this.repository = repository;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Override
    public long count() {
        return repository.count();
    }

    public Optional<Book> getById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Book> getByBookName(String bookName) {
        return repository.findByBookName(bookName);
    }

    @Override
    public List<Book> getByBookPartName(String bookName) {
        return repository.findByBookNameContaining(bookName);
    }

    @Override
    public List<Book> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public long insert(long authorId, long genreId, String bookName, String publishDate, String language,
                      String publishingHouse, String city, String isbn) {
        Optional<Author> author = authorService.getById(authorId);
        Optional<Genre> genre = genreService.getById(genreId);
        Book book = new Book(author.orElse(new Author()), genre.orElse(new Genre()), bookName, toDate(publishDate), language, publishingHouse, city, isbn);
        Book bookDb = repository.save(book);
        return Objects.nonNull(bookDb) ? bookDb.getId() : 0L;
    }

}
