package ru.otus.libraryapp.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@ToString
@Document(collection = "authors")
@EqualsAndHashCode
public class Author {

    @Id
    private String id;
    private String firstName;
    private String middleName;
    @Indexed
    private String lastName;

    public Author(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

}
