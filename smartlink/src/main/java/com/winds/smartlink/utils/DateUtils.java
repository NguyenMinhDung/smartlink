package com.winds.smartlink.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public static String YYYYMMDD = "yyyy-MM-dd";
	
	public static Date now() {
		Calendar cal = Calendar.getInstance();
		
		return cal.getTime();
	}
	
	public static String format(Date d, String format) {
		SimpleDateFormat dt = new SimpleDateFormat(format);
		return dt.format(d);
	}
}
