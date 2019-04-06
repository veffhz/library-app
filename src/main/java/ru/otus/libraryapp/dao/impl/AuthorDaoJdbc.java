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
import java.util.List;
import java.util.Map;

import ru.otus.libraryapp.dao.AuthorDao;
import ru.otus.libraryapp.domain.Author;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        return namedParameterJdbcOperations.queryForObject(
                "select count(*) from authors", Collections.emptyMap(), Integer.class);
    }

    @Override
    public int insert(Author author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("first_name", author.getFirstName());
        params.addValue("middle_name", author.getMiddleName());
        params.addValue("last_name", author.getLastName());
        namedParameterJdbcOperations.update(
                "insert into authors (first_name, middle_name, last_name) " +
                        "values (:first_name, :middle_name, :last_name)", params, keyHolder, new String[]{"ID"});
        return keyHolder.getKey().intValue();
    }

    @Override
    public Author getById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select * from authors where id = :id", params, new AuthorMapper()
        );
    }

    @Override
    public List<Author> getByLastName(String lastName) {
        Map<String, Object> params = Collections.singletonMap("last_name", lastName);
        return namedParameterJdbcOperations.query(
                "select * from authors where last_name = :last_name", params, new AuthorMapper()
        );
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcOperations.query("select * from authors", new AuthorMapper());
    }

    @Override
    public void deleteById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from authors where id = :id", params
        );
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("first_name");
            String middleName = resultSet.getString("middle_name");
            String lastName = resultSet.getString("last_name");
            return new Author(id, firstName, middleName, lastName);
        }
    }
}
