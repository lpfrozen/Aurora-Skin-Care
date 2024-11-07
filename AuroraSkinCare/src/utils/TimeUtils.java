package utils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

    // Method to format LocalDateTime to a readable string
    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }

    // Method to check if a given time falls within a specified time range
    public static boolean isWithinTimeRange(LocalTime timeToCheck, LocalTime startTime, LocalTime endTime) {
        return !timeToCheck.isBefore(startTime) && !timeToCheck.isAfter(endTime);
    }

    // Method to check if a given LocalDateTime falls within consultation hours
    // Monday: 10:00am - 01:00pm, Wednesday: 02:00pm - 05:00pm, Friday: 04:00pm - 08:00pm, Saturday: 09:00am - 01:00pm
    public static boolean isWithinConsultationHours(LocalDateTime dateTime) {
        LocalTime time = dateTime.toLocalTime();

        switch (dateTime.getDayOfWeek()) {
            case MONDAY:
                return isWithinTimeRange(time, LocalTime.of(10, 0), LocalTime.of(13, 0));
            case WEDNESDAY:
                return isWithinTimeRange(time, LocalTime.of(14, 0), LocalTime.of(17, 0));
            case FRIDAY:
                return isWithinTimeRange(time, LocalTime.of(16, 0), LocalTime.of(20, 0));
            case SATURDAY:
                return isWithinTimeRange(time, LocalTime.of(9, 0), LocalTime.of(13, 0));
            default:
                return false; // Outside consultation days
        }
    }
}
