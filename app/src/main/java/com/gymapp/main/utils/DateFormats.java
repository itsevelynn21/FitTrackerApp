package com.gymapp.main.utils;

import android.annotation.SuppressLint;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateFormats {

    private DateFormats(){}

    @SuppressLint("NewApi")
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @SuppressLint("NewApi")
    public static String localDateString(@NotNull LocalDate localDate){
        return localDate.format(FORMATTER);
    }

    @SuppressLint("NewApi")
    public static LocalDate localDateOf(@NotNull String formattedString){
        return LocalDate.parse(formattedString, FORMATTER);
    }
}
