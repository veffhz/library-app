package ru.otus.libraryapp.service;

import ru.otus.libraryapp.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> getById(long id);
    List<Comment> getByBookId(long bookId);
    long insert(String author, String date, String content, long bookId);
    List<Comment> getAll();
    void deleteById(long id);
    void deleteByBookId(long bookId);
}
