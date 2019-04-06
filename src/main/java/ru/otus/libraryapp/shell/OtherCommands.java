package ru.otus.libraryapp.shell;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.libraryapp.domain.Author;
import ru.otus.libraryapp.domain.Book;
import ru.otus.libraryapp.domain.Genre;
import ru.otus.libraryapp.service.AuthorService;
import ru.otus.libraryapp.service.BookService;
import ru.otus.libraryapp.service.GenreService;

import java.util.List;

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
    public void info() {
        log.info("Total:");
        log.info("");
        List<Author> authors = authorService.getAll();
        log.info("authors: " + authors.size());
        authors.forEach(author -> log.info(author.toString()));
        log.info("");
        List<Genre> genres = genreService.getAll();
        log.info("genres: " + genres.size());
        genres.forEach(genre -> log.info(genre.toString()));
        log.info("");
        List<Book> books = bookService.getAll();
        log.info("books: " + books.size());
        books.forEach(book -> log.info(book.toString()));
    }

}
