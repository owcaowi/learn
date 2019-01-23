package com.example.owi.wallet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateHelper {

    /**
     * Return today date in given format
     * @param format
     * @return String with date
     */

        public static String getTodayDate( String format ){

            String today;

            java.util.Date currentDate = Calendar.getInstance().getTime();
            SimpleDateFormat formatter= new SimpleDateFormat( format );
            today =  formatter.format(currentDate);

            return today;
        }

    /**
     * Return first day of the mounth in given format
     * @param format
     * @return String with date
     */

        public static String getFirstDayOfTheMounth( String format){

            String firstDay;

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format1 = new SimpleDateFormat(format);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Date date = calendar.getTime();
            firstDay = format1.format(date);

            return firstDay;
        }

    /**
     * Change date format
     *
     * @param data
     * @param newFormat
     * @param oldFormat
     * @return
     * @throws ParseException
     */

    public static String changeDataFormat( String data, String newFormat, String oldFormat ) throws ParseException {

        String newDate;

        SimpleDateFormat oldFormatPattern = new SimpleDateFormat(oldFormat);
        SimpleDateFormat newFormatPattern = new SimpleDateFormat(newFormat);

        Date date = oldFormatPattern.parse(data);
        newDate = newFormatPattern.format(date);

        return newDate;
    }
}
