package ru.otus.libraryapp.shell;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import ru.otus.libraryapp.domain.Genre;
import ru.otus.libraryapp.service.GenreService;

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
    public void genre(@ShellOption long id) {
        Genre genre = genreService.getById(id);
        log.info(genre.toString());
    }

    @ShellMethod("Show genres by genreName.")
    public void genreName(@ShellOption String genreName) {
        genreService.getByGenreName(genreName).forEach(genre -> log.info(genre.toString()));
    }

    @ShellMethod("Show all authors.")
    public void genres() {
        genreService.getAll().forEach(genre -> log.info(genre.toString()));
    }

    @ShellMethod("Show genre on id.")
    public void deleteGenre(@ShellOption long id) {
        genreService.deleteById(id);
        log.info(String.format("Deleted genre {%d}", id));
    }

    @ShellMethod("Add genre \"genre_name\".")
    public void addGenre(@ShellOption String genreName) {
        long id = genreService.insert(genreName);
        log.info(String.format("Genre {%d} created", id));
    }

}
