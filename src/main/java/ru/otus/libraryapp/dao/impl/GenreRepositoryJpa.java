package ru.otus.libraryapp.dao.impl;

import org.springframework.stereotype.Repository;

import ru.otus.libraryapp.dao.GenreRepository;
import ru.otus.libraryapp.domain.Author;
import ru.otus.libraryapp.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import java.util.List;

@Repository
@Transactional
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long count() {
        Query query = em.createQuery("select count(g) from Genre g", Long.class);
        return (Long) query.getSingleResult();
    }

    @Override
    public long insert(Genre genre) {
        em.persist(genre);
        return genre.getId();
    }

    @Override
    public Genre getById(long id) {
        return em.find(Genre.class, id);
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Genre g where g.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void delete(Genre genre) {
        em.remove(genre);
    }

    @Override
    public List<Genre> getByGenreName(String genreName) {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g where g.genreName = :genreName",
                Genre.class);
        query.setParameter("genreName", genreName);
        return query.getResultList();
    }

}
