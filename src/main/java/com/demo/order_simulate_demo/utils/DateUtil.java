package com.demo.order_simulate_demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class DateUtil {

    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static LocalDateTime stringToLocalDateTime(String dateTimeStr, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            log.error("Invalid date-time format: {}. Expected format: {}", dateTimeStr, format, e);
            return null;
        }
    }

    public static String localDateTimeToString(LocalDateTime dateTime, String format) {
        if (dateTime == null) {
            log.error("LocalDateTime cannot be null");
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return dateTime.format(formatter);
    }

    public static LocalDateTime stringToLocalDateTime(String dateTimeStr) {
        return stringToLocalDateTime(dateTimeStr, DATE_TIME_FORMAT);
    }

    public static String localDateTimeToString(LocalDateTime dateTime) {
        return localDateTimeToString(dateTime, DATE_TIME_FORMAT);
    }
}
