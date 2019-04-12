package ru.otus.libraryapp.service.impl;

import org.springframework.stereotype.Service;

import ru.otus.libraryapp.dao.CommentRepository;
import ru.otus.libraryapp.domain.Book;
import ru.otus.libraryapp.domain.Comment;
import ru.otus.libraryapp.service.BookService;
import ru.otus.libraryapp.service.CommentService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static ru.otus.libraryapp.service.impl.Utils.toDate;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    private final BookService bookService;

    public CommentServiceImpl(CommentRepository repository, BookService bookService) {
        this.repository = repository;
        this.bookService = bookService;
    }

    @Override
    public Optional<Comment> getById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Comment> getByBookId(long bookId) {
        return repository.findByBookId(bookId);
    }

    @Override
    public long insert(String author, String date, String content, long bookId) {
        Comment comment = new Comment(author, toDate(date), content);
        Book book = bookService.getById(bookId).get(); // TODO get
        comment.setBook(book);
        Comment commentDb = repository.save(comment);
        return Objects.nonNull(commentDb) ? commentDb.getId() : 0L;
    }

    @Override
    public List<Comment> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByBookId(long bookId) {
        repository.deleteByBookId(bookId);
    }
}
