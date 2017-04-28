package com.patrick.developer.babymonitoring.tools.date_manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by developer on 4/27/17.
 */

public class DateManager {

    public static String dateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(date);
    }

    public static Date stringToDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date mydate = null;
        mydate = format.parse(date);
        return mydate;
    }
}
