package com.sigma.pollboxapi.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeFormatter {
    public static String brazilianFormat(String localDateTime) {
        DateTimeFormatter databasePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter brazilianPattern = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.parse(localDateTime, databasePattern).format(brazilianPattern);
    }

    public static LocalDateTime fromDatabase(String localDateTime) {
        return LocalDateTime.parse(localDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


    public static String format(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
}
