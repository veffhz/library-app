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

public class BookRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    
    public long count() {
        Query query = em.createQuery("select count(b) from Book b", Long.class);
        return (Long) query.getSingleResult();
    }

    
    public long insert(Book book) {
        em.persist(book);
        return book.getId();
    }

    
    public Book getById(long id) {
        return em.find(Book.class, id);
    }

    
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    
    public void delete(Book book) {
        em.remove(book);
    }

    
    public List<Book> getByBookName(String bookName) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.bookName = :bookName",
                Book.class);
        query.setParameter("bookName", bookName);
        return query.getResultList();
    }

    
    public List<Book> getByBookPartName(String bookName) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where locate(:bookName, b.bookName) <> 0",
                Book.class);
        query.setParameter("bookName", bookName);
        return query.getResultList();
    }

    
    public Comment getCommentById(long id) {
        return em.find(Comment.class, id);
    }

    
    public List<Comment> getByBookId(long bookId) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.book.id = :bookId",
                Comment.class);
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }

    
    public long insert(Comment comment, long bookId) {
        Book book = getById(bookId);
        if (Objects.isNull(book)) {
            throw new BookNotFoundException();
        }
        comment.setBook(book);
        em.persist(comment);
        return comment.getId();
    }

    
    public void deleteCommentById(long id) {
        Query query = em.createQuery("delete from Comment c where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    
    public void deleteByBookId(long bookId) {
        Query query = em.createQuery("delete from Comment c where c.book.id = :bookId");
        query.setParameter("bookId", bookId);
        query.executeUpdate();
    }

}
