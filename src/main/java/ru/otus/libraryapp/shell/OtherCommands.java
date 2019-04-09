package ru.otus.libraryapp.shell;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import ru.otus.libraryapp.service.AuthorService;
import ru.otus.libraryapp.service.BookService;
import ru.otus.libraryapp.service.GenreService;

@Log
@ShellComponent
@ShellCommandGroup("Other commands")
public class OtherCommands {

    private final AuthorService authorService;
    private final BookService bookService;
    private final GenreService genreService;

    @Autowired
    public OtherCommands(AuthorService authorService, BookService bookService, GenreService genreService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.genreService = genreService;
    }

    @ShellMethod("Show all info.")
    public String info() {
        StringBuilder sb = new StringBuilder();
        sb.append("Total:");
        sb.append("\n");
        sb.append("\nauthors: ").append(authorService.count());
        sb.append("\n");
        authorService.getAll().forEach(author -> sb.append(author.toString()));
        sb.append("\n");
        sb.append("\ngenres: ").append(genreService.count());
        sb.append("\n");
        genreService.getAll().forEach(genre -> sb.append(genre.toString()));
        sb.append("\n");
        sb.append("\nbooks: ").append(bookService.count());
        sb.append("\n");
        bookService.getAll().forEach(book -> sb.append(book.toString()));
        sb.append("\n");
        return sb.toString();
    }

}
