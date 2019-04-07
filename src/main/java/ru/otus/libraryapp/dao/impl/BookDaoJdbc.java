package ru.otus.libraryapp.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import ru.otus.libraryapp.dao.AuthorDao;
import ru.otus.libraryapp.dao.BookDao;
import ru.otus.libraryapp.dao.GenreDao;
import ru.otus.libraryapp.domain.Author;
import ru.otus.libraryapp.domain.Book;
import ru.otus.libraryapp.domain.Genre;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Autowired
    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations, AuthorDao authorDao, GenreDao genreDao) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public int count() {
        return namedParameterJdbcOperations.queryForObject(
                "select count(*) from books", Collections.emptyMap(), Integer.class);
    }

    @Override
    public long insert(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("author_id", book.getAuthor().getId());
        params.addValue("genre_id", book.getGenre().getId());
        params.addValue("book_name", book.getBookName());
        params.addValue("publish_date", book.getPublishDate());
        params.addValue("language", book.getLanguage());
        params.addValue("publishing_house", book.getPublishingHouse());
        params.addValue("city", book.getCity());
        params.addValue("isbn", book.getIsbn());
        namedParameterJdbcOperations.update(
                "insert into books (author_id, genre_id, book_name, publish_date, language, publishing_house, city, isbn) " +
                        "values (:author_id, :genre_id, :book_name, :publish_date, :language, :publishing_house, :city, :isbn)",
                params, keyHolder, new String[]{"ID"});
        return keyHolder.getKey().longValue();
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select * from books where id = :id", params, new BookMapper()
        );
    }

    @Override
    public List<Book> getByBookName(String bookName) {
        Map<String, Object> params = Collections.singletonMap("book_name", bookName);
        return namedParameterJdbcOperations.query(
                "select * from books where book_name = :book_name", params, new BookMapper()
        );
    }

    @Override
    public List<Book> getByBookPartName(String bookName) {
        Map<String, Object> params = Collections.singletonMap("book_name", bookName);
        return namedParameterJdbcOperations.query(
                "select * from books where instr(book_name, :book_name) <> 0", params, new BookMapper() // like not work!
        );
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query("select * from books", new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from books where id = :id", params
        );
    }

    private class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            long authorId = resultSet.getLong("author_id");
            Author author = BookDaoJdbc.this.authorDao.getById(authorId);

            long genreId = resultSet.getLong("genre_id");
            Genre genre = BookDaoJdbc.this.genreDao.getById(genreId);

            String bookName = resultSet.getString("book_name");
            Date publishDate = resultSet.getDate("publish_date");
            String language = resultSet.getString("language");
            String publishingHouse = resultSet.getString("publishing_house");
            String city = resultSet.getString("city");
            String isbn = resultSet.getString("isbn");
            return new Book(id, author, genre, bookName, publishDate, language, publishingHouse, city, isbn);
        }
    }
}
