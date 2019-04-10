package ru.otus.libraryapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@ToString(exclude = "book")
public class Comment {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @Setter
    private Book book;

    private String author;
    private Date date;
    private String content;

    public Comment(String author, Date date, String content) {
        this.author = author;
        this.date = date;
        this.content = content;
    }

}
