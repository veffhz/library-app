package ru.otus.libraryapp.service.impl;

import lombok.extern.java.Log;

import ru.otus.libraryapp.exception.BookDateParseException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log
public class Utils {

    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    static Date toDate(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            log.warning(e.getMessage());
            throw new BookDateParseException(e);
        }
    }
}
