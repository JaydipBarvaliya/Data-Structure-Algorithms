package com.stackoverflow;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

    public static void main(String[] args) throws Exception {
        System.out.println(formatDate("12-11-2014"));
        System.out.println(formatDate("12/11/2014"));
    }

    public static String formatDate(String str) throws Exception {
        SimpleDateFormat forwardSlashFormatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hyphenFormatter = new SimpleDateFormat("dd-MM-yyyy");
        Date parsedDate;

        if (str.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
            parsedDate = forwardSlashFormatter.parse(str);
            return hyphenFormatter.format(parsedDate);
        } else if (str.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")) {
            parsedDate = hyphenFormatter.parse(str);
            return forwardSlashFormatter.format(parsedDate);
        }
        return null;
    }
}
