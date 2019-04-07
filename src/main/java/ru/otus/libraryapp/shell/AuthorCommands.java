package ru.otus.libraryapp.shell;

import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import ru.otus.libraryapp.domain.Author;
import ru.otus.libraryapp.service.AuthorService;

import java.util.Arrays;

@Log
@ShellComponent
@ShellCommandGroup("Author commands")
public class AuthorCommands {

    private final AuthorService authorService;

    @Autowired
    public AuthorCommands(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ShellMethod("Show author by id.")
    public String author(@ShellOption long id) {
        Author author = authorService.getById(id);
        return author.toString();
    }

    @ShellMethod("Show authors by lastName.")
    public String authorsLastName(@ShellOption String lastName) {
        return Arrays.toString(authorService.getByLastName(lastName).toArray());
    }

    @ShellMethod("Show all authors.")
    public String authors() {
        return Arrays.toString(authorService.getAll().toArray());
    }

    @ShellMethod("Show author on id.")
    public String deleteAuthor(@ShellOption long id) {
        authorService.deleteById(id);
        return String.format("Deleted author {%d}", id);
    }

    @ShellMethod("Add author \"firstName\", \"middleName\", \"lastName\".")
    public String addAuthor(@ShellOption String firstName,
                          @ShellOption(defaultValue="") String middleName,//TODO empty param
                          @ShellOption String lastName) {
        long id = authorService.insert(firstName, middleName, lastName);
        return String.format("Author {%d} created", id);
    }

}
