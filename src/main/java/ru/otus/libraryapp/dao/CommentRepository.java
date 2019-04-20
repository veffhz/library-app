package ru.otus.libraryapp.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import ru.otus.libraryapp.domain.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByBookId(String bookId);
    List<Comment> deleteByBookId(String bookId);
}
