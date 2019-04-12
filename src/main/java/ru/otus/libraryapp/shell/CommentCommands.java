package ru.otus.libraryapp.shell;

import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import ru.otus.libraryapp.domain.Comment;
import ru.otus.libraryapp.service.CommentService;

import java.util.Arrays;
import java.util.List;

@Log
@ShellComponent
@ShellCommandGroup("Comment commands")
public class CommentCommands {

    private final CommentService commentService;

    @Autowired
    public CommentCommands(CommentService commentService) {
        this.commentService = commentService;
    }

    @ShellMethod("Show comment by id.")
    public String comment(@ShellOption long id) {
        Comment comment = commentService.getById(id);
        return comment.toString();
    }

    @ShellMethod("Show comments by book id.")
    public String comments(@ShellOption long bookId) {
        List<Comment> comments = commentService.getByBookId(bookId);
        return Arrays.toString(comments.toArray());
    }

    @ShellMethod(value = "Add comment \"author\", \"date\", \"content\", \"bookId\".", key = "add-comment")
    public String add(@ShellOption String author,
                      @ShellOption(defaultValue="") String date,//TODO empty param
                      @ShellOption String content, @ShellOption long bookId) {
        long id = commentService.insert(author, date, content, bookId);
        return String.format("Comment {%d} created", id);
    }

    @ShellMethod(value = "Delete comment by id.", key = "delete-comment")
    public String delete(@ShellOption long id) {
        commentService.deleteCommentById(id);
        return "Deleted comment " + id;
    }

    @ShellMethod(value = "Delete comments by book id.", key = "delete-by-bookId")
    public String deleteByBookId(@ShellOption long bookId) {
        commentService.deleteByBookId(bookId);
        return "Deleted comment by bookId " + bookId;
    }

}
