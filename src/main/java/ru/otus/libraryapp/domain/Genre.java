package ru.otus.libraryapp.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class Genre {
    private int id;
    private final String genreName;

    public Genre(int id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }
}
