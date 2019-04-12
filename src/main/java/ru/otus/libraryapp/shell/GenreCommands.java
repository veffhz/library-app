package ru.otus.libraryapp.shell;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import ru.otus.libraryapp.domain.Genre;
import ru.otus.libraryapp.service.GenreService;

import java.util.Arrays;
import java.util.Optional;

@Log
@ShellComponent
@ShellCommandGroup("Genre commands")
public class GenreCommands {

    private final GenreService genreService;

    @Autowired
    public GenreCommands(GenreService genreService) {
        this.genreService = genreService;
    }

    @ShellMethod("Show genre on id.")
    public String genre(@ShellOption long id) {
        Optional<Genre> genre = genreService.getById(id);
        return genre.isPresent() ? genre.toString() : "genre not found.";
    }

    @ShellMethod(value = "Show genres by genreName.", key = "genre-name")
    public String genre(@ShellOption String genreName) {
        return Arrays.toString(genreService.getByGenreName(genreName).toArray());
    }

    @ShellMethod("Show all authors.")
    public String genres() {
        return Arrays.toString(genreService.getAll().toArray());
    }

    @ShellMethod(value = "Delete genre by id.", key = "delete-genre")
    public String delete(@ShellOption long id) {
        genreService.deleteById(id);
        return String.format("Deleted genre {%d}", id);
    }

    @ShellMethod(value = "Add genre \"genre_name\".", key = "add-genre")
    public String add(@ShellOption String genreName) {
        long id = genreService.insert(genreName);
        return String.format("Genre {%d} created", id);
    }

}
