package com.license.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtility {
    public String generateTimestamp(String inputDate) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(inputDate);
        long timestamp = date.getTime();
        return Long.toString(timestamp);
    }

    public String generateDate(String timestamp) {
        long longTimestamp = Long.parseLong(timestamp);
        Date date = new Date(longTimestamp);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    public boolean checkExpiry(String inputDate) {
        boolean isExpired = true;
        Date currentDate = new Date();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (currentDate.before(date)) {
            isExpired = false;
        }
        return isExpired;
    }
}
