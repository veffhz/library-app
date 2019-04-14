package ru.otus.libraryapp.dao;

import org.springframework.data.repository.CrudRepository;

import ru.otus.libraryapp.domain.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, String> {
    List<Comment> findByBookId(String bookId);
    List<Comment> deleteByBookId(String bookId);
    List<Comment> findAll();
}
