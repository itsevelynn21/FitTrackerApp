package com.gymapp.main.exceptions;

public class IllegalDayTrackModificationException extends Exception {

    public IllegalDayTrackModificationException(String message) {
        super(message);
    }

    public IllegalDayTrackModificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
