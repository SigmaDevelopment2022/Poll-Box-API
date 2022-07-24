package com.sigma.pollboxapi.utils;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeFormatter {
    public static LocalDateTime fromDatabase(String localDateTime) {
        DateTimeFormatter databasePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter brazilPattern = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime databaseDateTime = LocalDateTime.parse(localDateTime, databasePattern);
        return LocalDateTime.parse(databaseDateTime.toString(), brazilPattern);
    }

    public static LocalDateTime format(LocalDateTime localDateTime) {
        DateTimeFormatter brazilPattern = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.parse(localDateTime.toString(), brazilPattern);
    }
}
