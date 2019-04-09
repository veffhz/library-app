package ru.otus.libraryapp.dao;

import ru.otus.libraryapp.domain.Book;

import java.util.List;

public interface BookRepository {
    long count();
    long insert(Book book);
    Book getById(long id);
    List<Book> getByBookName(String bookName);
    List<Book> getByBookPartName(String bookName);
    List<Book> getAll();
    void delete(Book book);
    void deleteById(long id);
}
