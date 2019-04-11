package ru.otus.libraryapp.dao.impl;

import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Repository;

import ru.otus.libraryapp.dao.BookRepository;
import ru.otus.libraryapp.domain.Book;
import ru.otus.libraryapp.domain.Comment;
import ru.otus.libraryapp.exception.BookNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long count() {
        Query query = em.createQuery("select count(b) from Book b", Long.class);
        return (Long) query.getSingleResult();
    }

    @Override
    public long insert(Book book) {
        em.persist(book);
        return book.getId();
    }

    @Override
    public Book getById(long id) {
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery("select distinct b from Book b left join fetch b.comments", Book.class);
        query.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void delete(Book book) {
        em.remove(book);
    }

    @Override
    public List<Book> getByBookName(String bookName) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.bookName = :bookName",
                Book.class);
        query.setParameter("bookName", bookName);
        return query.getResultList();
    }

    @Override
    public List<Book> getByBookPartName(String bookName) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where locate(:bookName, b.bookName) <> 0",
                Book.class);
        query.setParameter("bookName", bookName);
        return query.getResultList();
    }

    @Override
    public Comment getCommentById(long id) {
        return em.find(Comment.class, id);
    }

    @Override
    public List<Comment> getByBookId(long id) {
        Book book = getById(id);
        return Objects.isNull(book) ? Collections.emptyList() : book.getComments();
    }

    @Override
    public long insert(Comment comment, long bookId) {
        Book book = getById(bookId);
        comment.setBook(book);
        book.getComments().add(comment);
        Book merge = em.merge(book);
        Comment saveComment = merge.getComments().get(book.getComments().size() - 1);
        return saveComment.getId();
    }

    @Override
    public void deleteCommentById(long id) {
        Query query = em.createQuery("delete from Comment c where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteByBookId(long bookId) {
        Book book = getById(bookId);
        if (Objects.isNull(book)) {
            throw new BookNotFoundException();
        }
        book.getComments().clear();
        em.merge(book);
    }

}
