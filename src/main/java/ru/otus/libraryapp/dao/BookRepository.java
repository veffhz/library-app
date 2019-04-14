package ru.otus.libraryapp.dao;

import org.springframework.data.repository.CrudRepository;

import ru.otus.libraryapp.domain.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, String> {
    List<Book> findByBookName(String bookName);
    List<Book> findByBookNameContaining(String bookName);
    List<Book> findAll();
}
