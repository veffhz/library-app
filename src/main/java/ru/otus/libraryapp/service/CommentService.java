package ru.otus.libraryapp.service;

import ru.otus.libraryapp.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> getById(String id);
    List<Comment> getByBookId(String bookId);
    String insert(String author, String date, String content, String bookId);
    List<Comment> getAll();
    void deleteById(String id);
    List<Comment> deleteByBookId(String bookId);
}
