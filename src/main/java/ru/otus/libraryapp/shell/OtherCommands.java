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
        sb.append("\n");
        sb.append("Total:");
        sb.append("\nauthors: ").append(authorService.count());
        sb.append("\n");
        authorService.getAll().forEach(author -> sb.append(author.toString()).append("\n"));
        sb.append("\ngenres: ").append(genreService.count());
        sb.append("\n");
        genreService.getAll().forEach(genre -> sb.append(genre.toString()).append("\n"));
        sb.append("\nbooks: ").append(bookService.count());
        sb.append("\n");
        bookService.getAll().forEach(book -> sb.append(book.toString()).append("\n"));
        sb.append("\n");
        return sb.toString();
    }

    @ShellMethod(value = "Create test data.", key = "create-data")
    public String test() {
        StringBuilder sb = new StringBuilder();
        long authorId = authorService.insert("Роберт", null, "Шекли");
        sb.append("\ncreated authorId: ").append(authorId);
        long genreId = genreService.insert("Фантастика");
        sb.append("\ncreated genreId: ").append(genreId);
        long bookId = bookService.insert(authorId, genreId, "Избранное", "1991-01-01",
                "Русский", "Мир", "Москва", "5-03002745-9");
        sb.append("\ncreated bookId: ").append(bookId);
        sb.append("\n");
        return sb.toString();
    }

}
