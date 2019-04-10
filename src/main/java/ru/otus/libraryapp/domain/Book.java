package ru.otus.libraryapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@ToString(exclude = "comments")
public class Book {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Author author;
    @ManyToOne
    private Genre genre;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

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


}
