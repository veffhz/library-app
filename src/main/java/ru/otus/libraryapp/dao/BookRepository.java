package ru.otus.libraryapp.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.libraryapp.domain.Book;
import ru.otus.libraryapp.domain.Comment;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByBookName(String bookName);
    List<Book> findByBookNameContaining(String bookName);
    List<Book> findAll();
}
