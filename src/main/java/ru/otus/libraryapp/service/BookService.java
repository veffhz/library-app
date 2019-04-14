package ru.otus.libraryapp.service;

import ru.otus.libraryapp.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    long count();
    Optional<Book> getById(String id);
    List<Book> getByBookName(String bookName);
    List<Book> getByBookPartName(String bookName);
    List<Book> getAll();
    void deleteById(String id);
    String insert(String authorId, String genreId, String bookName, String publishDate, String language,
               String publishingHouse, String city, String isbn);
}
