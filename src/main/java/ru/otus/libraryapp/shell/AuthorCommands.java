package ru.otus.libraryapp.shell;

import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import ru.otus.libraryapp.domain.Author;
import ru.otus.libraryapp.service.AuthorService;

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
    public void author(@ShellOption int id) {
        Author author = authorService.getById(id);
        log.info(author.toString());
    }

    @ShellMethod("Show authors by lastName.")
    public void authorsLastName(@ShellOption String lastName) {
        authorService.getByLastName(lastName).forEach(author -> log.info(author.toString()));
    }

    @ShellMethod("Show all authors.")
    public void authors() {
        authorService.getAll().forEach(author -> log.info(author.toString()));
    }

    @ShellMethod("Show author on id.")
    public void deleteAuthor(@ShellOption int id) {
        authorService.deleteById(id);
        log.info(String.format("Deleted author {%d}", id));
    }

    @ShellMethod("Add author \"firstName\", \"middleName\", \"lastName\".")
    public void addAuthor(@ShellOption String firstName,
                          @ShellOption(defaultValue="") String middleName,//TODO empty param
                          @ShellOption String lastName) {
        int id = authorService.insert(firstName, middleName, lastName);
        log.info(String.format("Author {%d} created", id));
    }

}
