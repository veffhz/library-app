package ru.otus.libraryapp.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Getter
@RequiredArgsConstructor
@ToString
public class Book {
    private int id;
    private final int authorId;
    private final int genreId;
    private final String bookName;
    private final Date publishDate;
    private final String language;
    private final String publishingHouse;
    private final String city;
    private final String isbn;

    public Book(int id, int authorId, int genreId, String bookName,
                Date publishDate, String language,
                String publishingHouse, String city, String isbn) {
        this.id = id;
        this.authorId = authorId;
        this.genreId = genreId;
        this.bookName = bookName;
        this.publishDate = publishDate;
        this.language = language;
        this.publishingHouse = publishingHouse;
        this.city = city;
        this.isbn = isbn;
    }
}
