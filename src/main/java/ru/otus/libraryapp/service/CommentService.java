package ru.otus.libraryapp.service;

import ru.otus.libraryapp.domain.Comment;

import java.util.List;

public interface CommentService {
    Comment getById(long id);
    List<Comment> getByBookId(long id);
    long insert(String author, String date, String content, long bookId);
    void deleteCommentById(long bookId);
    void deleteByBookId(long bookId);
}
