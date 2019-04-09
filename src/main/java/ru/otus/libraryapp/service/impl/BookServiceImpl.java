package ru.otus.libraryapp.service.impl;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.otus.libraryapp.dao.BookDao;
import ru.otus.libraryapp.domain.Author;
import ru.otus.libraryapp.domain.Book;
import ru.otus.libraryapp.domain.Genre;
import ru.otus.libraryapp.exception.BookDateParseException;
import ru.otus.libraryapp.service.AuthorService;
import ru.otus.libraryapp.service.BookService;
import ru.otus.libraryapp.service.GenreService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Log
@Service
public class BookServiceImpl implements BookService {

    private final BookDao dao;

    private final AuthorService authorService;
    private final GenreService genreService;

    private static final String DATE_FORMAT = "dd-MM-yyyy";

    private final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    @Autowired
    public BookServiceImpl(BookDao dao, AuthorService authorService, GenreService genreService) {
        this.dao = dao;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Override
    public int count() {
        return dao.count();
    }

    public Book getById(long id) {
        return dao.getById(id);
    }

    @Override
    public List<Book> getByBookName(String bookName) {
        return dao.getByBookName(bookName);
    }

    @Override
    public List<Book> getByBookPartName(String bookName) {
        return dao.getByBookPartName(bookName);
    }

    @Override
    public List<Book> getAll() {
        return dao.getAll();
    }

    @Override
    public void deleteById(long id) {
        dao.deleteById(id);
    }

    @Override
    public long insert(long authorId, long genreId, String bookName, String publishDate, String language,
                      String publishingHouse, String city, String isbn) {
        Author author = authorService.getById(authorId);
        Genre genre = genreService.getById(genreId);
        Book book = new Book(author, genre, bookName, toDate(publishDate), language, publishingHouse, city, isbn);
        return dao.insert(book);
    }

    private Date toDate(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            log.warning(e.getMessage());
            throw new BookDateParseException(e);
        }
    }

}
