package com.bruno.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;

public class ParserUtils {

	private static final int START_DATE = 0;

	private static int getDay(String date) {
		String dateVet[] = date.trim().split(" ");
		return Integer.parseInt(dateVet[1]);
	}

	private static int getYear(String date) {
		String dateVet[] = date.trim().split(" ");
		return Integer.parseInt(dateVet[2]);
	}

	private static Month getMonth(String date) {
		
		Month month;
		date = date.trim();
		String monthStr = date.substring(0, 3);
		
		if(monthStr.equalsIgnoreCase("Jan"))
			month = Month.JANUARY;
		else if(monthStr.equalsIgnoreCase("Feb"))
			month = Month.FEBRUARY;
		else if(monthStr.equalsIgnoreCase("Mar"))
			month = Month.MARCH;
		else if(monthStr.equalsIgnoreCase("Apr"))
			month = Month.APRIL;
		else if(monthStr.equalsIgnoreCase("May"))
			month = Month.MAY;
		else if(monthStr.equalsIgnoreCase("Jun"))
			month = Month.JUNE;
		else if(monthStr.equalsIgnoreCase("Jul"))
			month = Month.JULY;
		else if(monthStr.equalsIgnoreCase("Aug"))
			month = Month.AUGUST;
		else if(monthStr.equalsIgnoreCase("Sep"))
			month = Month.SEPTEMBER;
		else if(monthStr.equalsIgnoreCase("Oct"))
			month = Month.OCTOBER;
		else if(monthStr.equalsIgnoreCase("Nov"))
			month = Month.NOVEMBER;
		else
			month = Month.DECEMBER;
		
		return month;
	}
	
	public static Timestamp parseStartDate(String startDate, int startOrEnd) {
		String date[] = startDate.split(",");
		Month month = null;
		int length = date.length, hour=0, minute=0, year=0, day=0;
		
		if(date[length-1].trim().equals("All Day")){
			month = getMonth(date[length-2]);
			hour = minute = 0;
			year = getYear(date[length-2]);
			day = getDay(date[length-2]);
		}
		else{
			month = getMonth(date[1]);
			year = getYear(date[1]);
			day = getDay(date[1]);
			hour = getHour(date[length-1], startOrEnd);
			minute = getMinute(date[length-1], startOrEnd);
			//hour = getHour(date[length-1], 3);
			//minute = getMinute(date[length-1], 3);
		}
		
		LocalDateTime ldt = LocalDateTime.of(year, month, day, hour, minute);
		
		return Timestamp.valueOf(ldt);
	}

	private static int getMinute(String hourStr, int index) {
		
		String hourVet[] = hourStr.trim().split(" ");
		int length = hourVet[index].length();
		
		return Integer.parseInt(hourVet[index].substring(length-2, length));
	}

	private static int getHour(String hourStr, int index) {
		String hourVet[] = hourStr.trim().split(" ");
		
		if("pm".equals(hourVet[index+1])){
			try {
				return 12 + Integer.parseInt(hourVet[index].trim().substring(0,2));
			} catch (NumberFormatException e) {
				return 12 + Integer.parseInt(hourVet[index].trim().substring(0,1));
			}
		}
		else{
			try {
				return Integer.parseInt(hourVet[index].substring(0,2));
			} catch (NumberFormatException e) {
				return Integer.parseInt(hourVet[index].substring(0,1));
			}
			
		}
	}
}