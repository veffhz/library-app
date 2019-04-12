package ru.otus.libraryapp.domain;

import lombok.Getter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
@ToString
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Author author;
    @ManyToOne
    private Genre genre;

    @Column
    private String bookName;
    @Column
    private Date publishDate;
    @Column
    private String language;
    @Column
    private String publishingHouse;
    @Column
    private String city;
    @Column
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


}
