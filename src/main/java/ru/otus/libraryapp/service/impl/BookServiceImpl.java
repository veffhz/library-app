package ru.otus.libraryapp.service.impl;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.otus.libraryapp.dao.BookDao;
import ru.otus.libraryapp.domain.Book;
import ru.otus.libraryapp.exception.BookDateParseException;
import ru.otus.libraryapp.service.BookService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Log
@Service
public class BookServiceImpl implements BookService {

    private final BookDao dao;

    private static final String DATE_FORMAT = "dd-MM-yyyy";

    private final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    @Autowired
    public BookServiceImpl(BookDao dao) {
        this.dao = dao;
    }

    public Book getById(int id) {
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
    public void deleteById(int id) {
        dao.deleteById(id);
    }

    @Override
    public int insert(int authorId, int genreId, String bookName, String publishDate, String language,
                      String publishingHouse, String city, String isbn) {
        Book book = new Book(authorId, genreId, bookName, toDate(publishDate), language, publishingHouse, city, isbn);
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
