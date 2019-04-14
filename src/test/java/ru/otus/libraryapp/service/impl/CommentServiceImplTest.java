package ru.otus.libraryapp.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.libraryapp.dao.CommentRepository;
import ru.otus.libraryapp.domain.Comment;
import ru.otus.libraryapp.service.CommentService;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Test for Comment Service")
@SpringBootTest
class CommentServiceImplTest {

    @Autowired
    private CommentService commentService;

    @MockBean
    private CommentRepository commentRepository;

    @Test
    @DisplayName("Test invoke get comment by id")
    void shouldGetGenreById() {
        Comment commentMock = new Comment("test", new Date(), "test");
        when(commentRepository.findById(any(String.class))).thenReturn(Optional.of(commentMock));

        Comment comment = commentService.getById("000").get();

        verify(commentRepository, times(1)).findById("000");
        assertEquals(commentMock, comment);
    }

    @Test
    @DisplayName("Test invoke get all comments")
    void shouldGetAllGenres() {
        commentService.getAll();
        verify(commentRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test invoke delete comment by id")
    void shouldDeleteGenreById() {
        commentService.deleteById("000");
        verify(commentRepository, times(1)).deleteById("000");
    }

    @Test
    @DisplayName("Test invoke insert new comment")
    void shouldInsertNewGenre() {
        commentService.insert("test", "1991-01-01", "test", "000");
        verify(commentRepository, times(1)).save(any());
    }
}