package ru.otus.libraryapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@ToString
public class Book {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Author author;
    @ManyToOne
    private Genre genre;

    private String bookName;
    private Date publishDate;
    private String language;
    private String publishingHouse;
    private String city;
    private String isbn;

    public Book(Author author, Genre genre, String bookName,
                Date publishDate, String language,
                String publishingHouse, String city, String isbn) {
        this.author = author;
        this.genre = genre;
        this.bookName = bookName;
        this.publishDate = publishDate;
        this.language = language;
        this.publishingHouse = publishingHouse;
        this.city = city;
        this.isbn = isbn;
    }

    public Book(long id, Author author, Genre genre, String bookName,
                Date publishDate, String language,
                String publishingHouse, String city, String isbn) {
        this.id = id;
        this.author = author;
        this.genre = genre;
        this.bookName = bookName;
        this.publishDate = publishDate;
        this.language = language;
        this.publishingHouse = publishingHouse;
        this.city = city;
        this.isbn = isbn;
    }
}
