package com.ennea.solutions.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateHelper {
	
	final static String DEFAULT_DATE = "1900-01-01"; // default date value
	public static boolean isValidDate(String d)
    {
        String regex = "^(1[0-2]|0[1-9])/(3[01]"
                       + "|[12][0-9]|0[1-9])/[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher((CharSequence)d);
        return matcher.matches();
    }
	
	public static String validDateFormat(String inputDate){
		if(!isValidDate(inputDate)) return DEFAULT_DATE; 
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
		try {
			date = dt.parse(inputDate);
		} catch (ParseException e) {
			return DEFAULT_DATE;
		}
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        return (String)dt1.format(date);
	}
	
	public static String getTodaysDate() { 
		return java.time.LocalDate.now().toString();
	}
}
