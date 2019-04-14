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
import java.util.Optional;

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
        Optional<Author> author = authorService.getById(id);
        return author.isPresent() ? author.get().toString() : "author not found.";
    }

    @ShellMethod(value = "Show authors by lastName.", key = "authors-lastName")
    public String author(@ShellOption String lastName) {
        return Arrays.toString(authorService.getByLastName(lastName).toArray());
    }

    @ShellMethod("Show all authors.")
    public String authors() {
        return Arrays.toString(authorService.getAll().toArray());
    }

    @ShellMethod(value = "Delete author by id.", key = "delete-author")
    public String delete(@ShellOption long id) {
        authorService.deleteById(id);
        return String.format("Deleted author {%d}", id);
    }

    @ShellMethod(value = "Add author \"firstName\", \"middleName\", \"lastName\".", key = "add-author")
    public String add(@ShellOption String firstName,
                          @ShellOption(defaultValue="") String middleName,//TODO empty param
                          @ShellOption String lastName) {
        long id = authorService.insert(firstName, middleName, lastName);
        return String.format("Author {%d} created", id);
    }

}
