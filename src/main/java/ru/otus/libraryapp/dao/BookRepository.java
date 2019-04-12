package ru.otus.libraryapp.dao;

import ru.otus.libraryapp.domain.Book;
import ru.otus.libraryapp.domain.Comment;

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

    Comment getCommentById(long id);
    List<Comment> getByBookId(long bookId);
    long insert(Comment comment, long bookId);
    void deleteCommentById(long id);
    void deleteByBookId(long bookId);
}
