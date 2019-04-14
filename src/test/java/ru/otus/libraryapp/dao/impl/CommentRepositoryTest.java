package ru.otus.libraryapp.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import ru.otus.libraryapp.dao.BookRepository;
import ru.otus.libraryapp.dao.CommentRepository;
import ru.otus.libraryapp.domain.Book;
import ru.otus.libraryapp.domain.Comment;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test for CommentRepository")
@DataJpaTest
@ComponentScan
class CommentRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("Test add comment")
    void shouldAddCommentToBook() {
        Comment comment = new Comment("author", new Date(), "content");
        Book book = bookRepository.findById(5L).get();

        comment.setBook(book);
        commentRepository.save(comment);

        List<Comment> comments = commentRepository.findByBookId(5);

        assertEquals(comments.size(), 1);
    }
}