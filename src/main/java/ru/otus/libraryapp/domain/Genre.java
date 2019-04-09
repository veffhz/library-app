package ru.otus.libraryapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
@ToString
public class Genre {

    @Id
    @GeneratedValue
    private long id;
    private String genreName;

    public Genre(String genreName) {
        this.genreName = genreName;
    }

}
