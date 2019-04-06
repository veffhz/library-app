package ru.otus.libraryapp.shell;

import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import ru.otus.libraryapp.domain.Book;
import ru.otus.libraryapp.service.BookService;

@Log
@ShellComponent
@ShellCommandGroup("Book commands")
public class BookCommands {

    private final BookService bookService;

    @Autowired
    public BookCommands(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod("Show book on id.")
    public void book(@ShellOption int id) {
        Book book = bookService.getById(id);
        log.info(book.toString());
    }

    @ShellMethod("Show books by bookName.")
    public void booksName(@ShellOption String bookName) {
        bookService.getByBookName(bookName).forEach(book -> log.info(book.toString()));
    }

    @ShellMethod("Show books like book name.")
    public void booksLikeName(@ShellOption String bookName) {
        bookService.getByBookPartName(bookName).forEach(book -> log.info(book.toString()));
    }

    @ShellMethod("Show all books.")
    public void books() {
        bookService.getAll().forEach(book -> log.info(book.toString()));
    }

    @ShellMethod("Show author on id.")
    public void deleteBook(@ShellOption int id) {
        bookService.deleteById(id);
        log.info(String.format("Deleted book {%d}", id));
    }

    @ShellMethod("Add book \"authorId\", \"genreId\", \"bookName\", \"publishDate\", " +
            "\"language\", \"publishingHouse\", \"city\", \"isbn\".")
    public void addBook(@ShellOption int authorId, @ShellOption int genreId,
                    @ShellOption String bookName, @ShellOption String publishDate,
                    @ShellOption String language, @ShellOption String publishingHouse,
                    @ShellOption String city, @ShellOption String isbn) {
        int id = bookService.insert(authorId, genreId, bookName, publishDate, language, publishingHouse, city, isbn);
        log.info(String.format("Book {%d} created", id));
    }

}
