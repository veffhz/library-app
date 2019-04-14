package ru.otus.libraryapp.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.otus.libraryapp.domain.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByBookName(String bookName);
    List<Book> findByBookNameContaining(String bookName);
    @EntityGraph("bookGraph")
    List<Book> findAll();
}
