package br.com.givailson.popularmoviesapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Util {

    public static String datoToBRString(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        return sf.format(date);
    }

    public static Date strToDate(String strDate) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            return sf.parse(strDate);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return null;
    }
}
