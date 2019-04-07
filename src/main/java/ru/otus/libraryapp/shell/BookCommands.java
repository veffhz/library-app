package ru.otus.libraryapp.shell;

import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import ru.otus.libraryapp.domain.Book;
import ru.otus.libraryapp.service.BookService;

import java.util.Arrays;

@Log
@ShellComponent
@ShellCommandGroup("Book commands")
public class BookCommands {

    private final BookService bookService;

    @Autowired
    public BookCommands(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod("Show book by id.")
    public String book(@ShellOption long id) {
        Book book = bookService.getById(id);
        return book.toString();
    }

    @ShellMethod(value = "Show books by bookName.", key = "books-name")
    public String booksName(@ShellOption String bookName) {
        return Arrays.toString(bookService.getByBookName(bookName).toArray());
    }

    @ShellMethod(value = "Show books like book name.", key = "books-likeName")
    public String books(@ShellOption String bookName) {
        return Arrays.toString(bookService.getByBookPartName(bookName).toArray());
    }

    @ShellMethod("Show all books.")
    public String books() {
        return Arrays.toString(bookService.getAll().toArray());
    }

    @ShellMethod(value = "Delete author by id.", key = "delete-book")
    public String delete(@ShellOption long id) {
        bookService.deleteById(id);
        return String.format("Deleted book {%d}", id);
    }

    @ShellMethod(value = "Add book \"authorId\", \"genreId\", \"bookName\", \"publishDate\", " +
            "\"language\", \"publishingHouse\", \"city\", \"isbn\".", key = "add-book")
    public String add(@ShellOption long authorId, @ShellOption long genreId,
                    @ShellOption String bookName, @ShellOption String publishDate,
                    @ShellOption String language, @ShellOption String publishingHouse,
                    @ShellOption String city, @ShellOption String isbn) {
        long id = bookService.insert(authorId, genreId, bookName, publishDate, language, publishingHouse, city, isbn);
        return String.format("Book {%d} created", id);
    }

}
