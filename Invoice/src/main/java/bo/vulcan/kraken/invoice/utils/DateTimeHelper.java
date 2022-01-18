package bo.vulcan.kraken.invoice.utils;

import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeHelper {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(AppConstants.INSTANT_FORMAT);

    /*public static Date toDate(String value) {

        if (value != null) {
            try {
                return formatter.parse(value);
            } catch (ParseException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String fromDate(Date date) {
        return date == null ? null : formatter.format(date);
    }*/

    public static LocalDate toLocalDate(String value) {
        return value == null ? null : new DateTime(value).toLocalDate();
    }

    public static String fromLocalDate(LocalDate value) {
        return value == null ? null : value.toString(AppConstants.LOCALTIME_FORMAT);
    }

    public static DateTime toDateTime(String value) {
        return value == null ? null : new DateTime(value);
    }

    public static String fromDateTime(DateTime value) {
        return value == null ? null : value.toString(AppConstants.DATETIME_FORMAT);
    }

    public static Instant toInstant(String value) {
        return value == null ? null : new Instant(value);
    }

    public static String fromInstant(Instant value) {
        return value == null ? null : value.toString(dateTimeFormatter);
    }

    public static DateTime getCurrentLocalTime() {
        return new DateTime();
    }

    public static DateTime getCurrentUtcTime() {
        return new DateTime(DateTimeZone.UTC);
    }
}
