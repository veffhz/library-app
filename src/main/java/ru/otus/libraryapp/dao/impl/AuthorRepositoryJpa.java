package ru.otus.libraryapp.dao.impl;

import org.springframework.stereotype.Repository;

import ru.otus.libraryapp.dao.AuthorRepository;
import ru.otus.libraryapp.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import java.util.List;

@Repository
@Transactional
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long count() {
        Query query = em.createQuery("select count(a) from Author a", Long.class);
        return (Long) query.getSingleResult();
    }

    @Override
    public long insert(Author author) {
        em.persist(author);
        return author.getId();
    }

    @Override
    public Author getById(long id) {
        return em.find(Author.class, id);
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Author a where a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void delete(Author author) {
        em.remove(author);
    }

    @Override
    public List<Author> getByLastName(String name) {
        TypedQuery<Author> query = em.createQuery("select a from Author a where a.lastName = :lastName",
                Author.class);
        query.setParameter("lastName", name);
        return query.getResultList();
    }

}
