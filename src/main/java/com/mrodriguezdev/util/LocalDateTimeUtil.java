package com.mrodriguezdev.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtil {
    private static final String DEFAULT_PATTERN = "dd/MM/yyyy HH:mm:ss";
    public static final String FILE_PATTERN = "yyyy-MM-dd_HH-mm-ss";
    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_PATTERN);

    public static String format(LocalDateTime date, String pattern) {
        DateTimeFormatter formatter;

        if (pattern == null || pattern.isEmpty()) {
            formatter = DEFAULT_FORMATTER;
        } else {
            formatter = DateTimeFormatter.ofPattern(pattern);
        }

        return date.format(formatter);
    }

    public static String format(LocalDateTime date) {
        return format(date, null);
    }

    public static LocalDateTime parse(String dateStr, String pattern) {
        DateTimeFormatter formatter;

        if (pattern == null || pattern.isEmpty()) {
            formatter = DEFAULT_FORMATTER;
        } else {
            formatter = DateTimeFormatter.ofPattern(pattern);
        }

        return LocalDateTime.parse(dateStr, formatter);
    }

    public static LocalDateTime parse(String dateStr) {
        return parse(dateStr, null);
    }
}
