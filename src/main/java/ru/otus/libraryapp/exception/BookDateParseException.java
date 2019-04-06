package ru.otus.libraryapp.exception;

public class BookDateParseException extends RuntimeException {

    private static final String MESSAGE = "Date parsing error!";

    public BookDateParseException() {
        super(MESSAGE);
    }

    public BookDateParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookDateParseException(Throwable cause) {
        super(MESSAGE, cause);
    }

}
