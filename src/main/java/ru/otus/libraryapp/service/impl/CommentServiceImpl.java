package ru.otus.libraryapp.service.impl;

import org.springframework.stereotype.Service;

import ru.otus.libraryapp.dao.BookRepository;
import ru.otus.libraryapp.domain.Comment;
import ru.otus.libraryapp.service.CommentService;

import java.util.List;

import static ru.otus.libraryapp.service.impl.Utils.toDate;

@Service
public class CommentServiceImpl implements CommentService {

    private final BookRepository bookRepository;

    public CommentServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Comment getById(long id) {
        return bookRepository.getCommentById(id);
    }

    @Override
    public List<Comment> getByBookId(long id) {
        return bookRepository.getByBookId(id);
    }

    @Override
    public long insert(String author, String date, String content, long bookId) {
        Comment comment = new Comment(author, toDate(date), content);
        return bookRepository.insert(comment, bookId);
    }

    @Override
    public void deleteCommentById(long id) {
        bookRepository.deleteCommentById(id);
    }

    @Override
    public void deleteByBookId(long bookId) {
        bookRepository.deleteByBookId(bookId);
    }
}
