package com.example.aprzybylo.learn.Utils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by aprzybylo on 20/03/2018.
 */

public class DateTimeUtils {

    public static final String PATTERN_1 = "dd MMM yyyy";
    public static final String PATTERN_2 = "HH:mm";

    public static String formatDateToPattern(Date date, String pattern){

        String outDate = new String();
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        outDate = format.format(date);

        return outDate;
    }

    public static String formatTimeToPattern(Time time, String pattern){

        String outTime = new String();
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        outTime = format.format(time);

        return outTime;
    }

    public static Date formatPatternToDate(String pattern, String date){

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date dateOut = new Date();

        try {
            dateOut = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateOut;
    }

}
