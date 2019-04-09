package ru.otus.libraryapp.dao.impl;

import org.springframework.stereotype.Repository;

import ru.otus.libraryapp.dao.BookRepository;
import ru.otus.libraryapp.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import java.util.List;

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
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
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

}
