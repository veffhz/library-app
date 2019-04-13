package ru.otus.libraryapp.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.otus.libraryapp.domain.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByBookId(long bookId);
    List<Comment> deleteByBookId(long bookId);
    @EntityGraph("commentGraph")
    List<Comment> findAll();
}
