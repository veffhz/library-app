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
@Table(name = "comments")
@NamedEntityGraph(name = "commentGraph", includeAllAttributes = true)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    @Setter
    private Book book;
    @Column
    private String author;
    @Column
    private Date date;
    @Column
    private String content;

    public Comment(String author, Date date, String content) {
        this.author = author;
        this.date = date;
        this.content = content;
    }

}
