package com.bruno.modelporra;



import java.util.Calendar;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


public class DateUtils {
	
	private final static int DAYS_TO_ADD_MAKE_WEEK = 7;
	private final static int DAYS_TO_MAKE_YESTERDAY = -1;
	private final static int DAYS_TO_MAKE_TOMORROW = 1;
	private final static int ADJUST_MONTH = 1;

	//private final static TimeZone timeZone = TimeZone.getTimeZone("America/Chicago"); 

	public static void print(){
		
		Calendar c = Calendar.getInstance();
		System.out.println(c.getTime());
	}
	
	public static Date getNow(){
		return new Date();
	}
	
	public static Date getNextWeekFromToday(Date today) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DATE, DAYS_TO_ADD_MAKE_WEEK);
        cal.add(Calendar.DATE, DAYS_TO_MAKE_TOMORROW);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
	}

	public static Date getYesterday() {
		//timeZone();
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, DAYS_TO_MAKE_YESTERDAY);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
	}
	
	public static Date getHoursBeforeNow() {
		//timeZone();
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) - 2);
		return cal.getTime();
	}
	
	public static void timeZone(){
		TimeZone.setDefault(TimeZone.getTimeZone("America/Chicago"));
	}

	public static Date getEarlyToday() {
		//timeZone();
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	
	public static Date getTomorrow() {
		//timeZone();
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, DAYS_TO_MAKE_TOMORROW);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
}
