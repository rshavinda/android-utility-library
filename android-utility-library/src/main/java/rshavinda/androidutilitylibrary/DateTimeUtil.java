package rshavinda.androidutilitylibrary;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@SuppressLint("SimpleDateFormat")
public class DateTimeUtil {
    private static final String TAG = DateTimeUtil.class.getSimpleName();
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FORMAT_1 = "yyyy-MM-dd hh:mma";
    public static final String DATE_TIME_FORMAT_2 = "dd MMM. yyyy / HH:mm";
    public static final String DATE_TIME_FORMAT_3 = "dd-MM-yyyy\nhh:mma";
    public static final String DATE_TIME_FORMAT_4 = "hh:mma\ndd-MM-yyyy";
    public static final String DATE_FORMAT_COMMON = "dd MMM yyyy";
    public static final String DATE_FORMAT_1 = "dd/MM/yyyy";
    public static final String DATE_FORMAT_2 = "dd MMMM yyyy";

    /*- Common pattern letters-
     *   'a' - Am/Pm of day [ Ex:  PM ]  [return: String/Text ] [Valid format: a]
     *   'h' - Clock hour of am pm (1-12)  [return: int/Number ]  [Valid format: hh]
     *   'H' - Hour of day (0-23)  [return: int/Number ]  [Valid format: HH]
     *   'm' - Minute of hour (0-59)  [return: int/Number ]  [Valid format: mm]
     *   's' - Second of minute (0-59)   [return: int/Number ]  [Valid format: ss]
     *   'S' - Milliseconds of minute (0-999)  [return: int/Number ] [Valid format: SSS]
     *
     *   'y' - To display Year. Valid formats:  yy, yyyy
     *   'M' - To display Month. Valid formats: MM, MMM or MMMMM
     *   'd' - To display day of month. Valid formats: d, dd
     *   'E' - To display Day in week. Valid formats: EE, EEEE
     *
     *   'z' - Time-zone name. [ Ex:  PST , IST ]  Valid formats: z, zzzz
     *   'Z' - Time-zone-offset.  [ Ex:  -0800 ]
     *
     *   'k' - To display Hour in day (1-24)
     *   'K' - To display Hour in day, AM / PM (0-11)
     */

    /**
     * Return current date time as 'yyyy-MM-dd HH:mm:ss' (Ex : 2020-11-13 17:46:23)
     * @return - [String] current date time
     */
    public static String getDateTimeNow() {
        DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
        return dateFormat.format(Calendar.getInstance().getTime()).toString();
    }

    /**
     * @param format - date time format (Ex : "yyyy-MM-dd hh:mma")
     * @return - [String] current date time (Ex: 2020-11-13 05:48PM)
     */
    public static String formatDateTime(String format) {
        if (format == null || format.isEmpty()) {
            format = DEFAULT_DATE_TIME_FORMAT;
        }
        return new SimpleDateFormat(format, Locale.getDefault()).format(Calendar.getInstance().getTime());
    }

    /**
     * @param format - date time format (Ex : "yyyy-MM-dd hh:mma")
     * @param date - date object
     * @return- [String] formatted date time
     */
    public static String formatDateTime(String format, Date date) {
        if (format == null || format.isEmpty()) {
            format = DEFAULT_DATE_TIME_FORMAT;
        }
        if (date == null) {
            date = new Date(getUnixTimestamp());
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * @param format  - date time format (Ex : "yyyy-MM-dd hh:mma")
     * @param calendar - calender object
     * @return [String] formatted date time
     */
    public static String formatDateTime(String format, Calendar calendar) {
        if (format == null || format.isEmpty()) {
            format = DEFAULT_DATE_TIME_FORMAT;
        }
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
        return new SimpleDateFormat(format, Locale.getDefault()).format(calendar.getTime());
    }

    /**
     * @param date  - date in string format
     * @param currentFormat - format in the 'date' parameter
     * @param newFormat - format that want to change
     * @return [String] formatted date time
     */
    public static String changeDateFromString(String date, String currentFormat, String newFormat) {
        if (date == null || date.isEmpty()) {
            date = formatUnixTimestamp(DEFAULT_DATE_TIME_FORMAT);
        }
        if (currentFormat == null || currentFormat.isEmpty()) {
            currentFormat = DEFAULT_DATE_TIME_FORMAT;
        }
        if (newFormat == null || newFormat.isEmpty()) {
            newFormat = DEFAULT_DATE_TIME_FORMAT;
        }
        try {
            DateFormat dateFormat = new SimpleDateFormat(currentFormat, Locale.ENGLISH);
            Date dateObject = dateFormat.parse(date);

            if (dateObject == null) {
                return date;
            }
            return new SimpleDateFormat(newFormat, Locale.ENGLISH).format(dateObject);
        } catch (ParseException e) {
            Log.e(TAG, "convertDateFormat: ", e);
            return date;
        }
    }

    /**
     * @param date   - date in string format
     * @param format - format that want to change
     * @return [String] formatted date time
     */
    public static Date changeDateFromString(String date, String format) {
        if (date == null || date.isEmpty()) {
            Log.e(TAG, "changeDateFromString: Date is empty");
            return null;
        }
        if (format == null || format.isEmpty()) {
            format = DEFAULT_DATE_TIME_FORMAT;
        }
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(date);
        } catch (ParseException ex) {
            Log.e(TAG, "changeDateFromString: ", ex);
            return null;
        }
    }

    /**
     * @return UnixTimestamp (returns milliseconds since Jan 1, 1970)
     */
    private static long getUnixTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * @param format - date time format (Ex : "yyyy-MM-dd hh:mma")
     * @return [String] formatted date time
     */
    public static String formatUnixTimestamp(String format) {
        if (format == null || format.isEmpty()) {
            format = DEFAULT_DATE_TIME_FORMAT;
        }
        return new SimpleDateFormat(format, Locale.getDefault()).format(new Date(getUnixTimestamp()));
    }

    /**
     * @param unix - UnixTimestamp
     * @param format - date time format (Ex : "yyyy-MM-dd hh:mma")
     * @return [String] formatted date time
     */
    public static String formatUnixTimestamp(long unix, String format) {
        if (format == null || format.isEmpty()) {
            format = DEFAULT_DATE_TIME_FORMAT;
        }
        return new SimpleDateFormat(format, Locale.getDefault()).format(unix);
    }


    /**
     * @return current hour( 1-12) (clock-hour-of-am-pm) (ex :  11)
     */
    public static String getHourOfDay12() {
        return formatUnixTimestamp("h");
    }

    /**
     * @return current hour( 0-23) (hour-of-day) (ex :  18)
     */
    public static String getHourOfDay24() {
        return formatUnixTimestamp("H");
    }

    /**
     * @return minute of hour
     */
    public static String getMinute() {
        return formatUnixTimestamp("m");
    }

    /**
     * @return day of month (Ex : 10)
     */
    public static String getDateOfMonth() {
        return formatUnixTimestamp("d");
    }

    /**
     * @return day of week (Ex : Friday)
     */
    public static String getDayNameInWeek() {
        return formatUnixTimestamp("EEEE");
    }

    /**
     * @return Display Time Zone Name (Ex : India Standard Time)
     */
    public static String getTimeZoneName() {
        return formatUnixTimestamp("zzzz");
    }

    /**
     * @return Display Time Zone Offset (Ex : +0530)
     */
    public static String getTimeZone() {
        return formatUnixTimestamp("Z");
    }
}