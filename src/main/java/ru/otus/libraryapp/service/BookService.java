package ru.otus.libraryapp.service;

import ru.otus.libraryapp.domain.Book;

import java.util.List;

public interface BookService {
    Book getById(int id);
    List<Book> getByBookName(String bookName);
    List<Book> getByBookPartName(String bookName);
    List<Book> getAll();
    void deleteById(int id);
    int insert(int authorId, int genreId, String bookName, String publishDate, String language,
               String publishingHouse, String city, String isbn);
}
