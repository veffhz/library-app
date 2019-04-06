package ru.otus.libraryapp.dao;

import ru.otus.libraryapp.domain.Book;

import java.util.List;

public interface BookDao {
    int count();
    int insert(Book book);
    Book getById(int id);
    List<Book> getByBookName(String bookName);
    List<Book> getByBookPartName(String bookName);
    List<Book> getAll();
    void deleteById(int id);
}
