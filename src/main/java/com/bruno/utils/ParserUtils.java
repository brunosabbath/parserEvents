package com.bruno.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;

import com.bruno.parser.Constant;

public class ParserUtils {

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
		
		if(monthStr.contains("Jan"))
			month = Month.JANUARY;
		else if(monthStr.contains("Feb"))
			month = Month.FEBRUARY;
		else if(monthStr.contains("Mar"))
			month = Month.MARCH;
		else if(monthStr.contains("Apr"))
			month = Month.APRIL;
		else if(monthStr.contains("May"))
			month = Month.MAY;
		else if(monthStr.contains("Jun"))
			month = Month.JUNE;
		else if(monthStr.contains("Jul"))
			month = Month.JULY;
		else if(monthStr.contains("Aug"))
			month = Month.AUGUST;
		else if(monthStr.contains("Sep"))
			month = Month.SEPTEMBER;
		else if(monthStr.contains("Oct"))
			month = Month.OCTOBER;
		else if(monthStr.contains("Nov"))
			month = Month.NOVEMBER;
		else
			month = Month.DECEMBER;
		
		return month;
	}
	
	public static Timestamp parseDate(String sDate, int startOrEnd) {
		String date[] = sDate.split(",");
		Month month = null;
		int length = date.length, hour=0, minute=0, year=0, day=0;
		
		if(date[length-1].trim().equals("All Day")){
			month = getMonth(date[length-2]);
			hour = minute = 0;
			year = getYear(date[length-2]);
			day = getDay(date[length-2]);
		}
		else if(startOrEnd == Constant.END_DATE && sDate.contains("Repeats")){
			day = getDayEndDate(date[length-2]);
			month = getMonthEndDate(date[length-2]);
			year = getYearEndDate(date[length-1]);
		}
		else{
			month = getMonth(date[1]);
			year = getYear(date[1]);
			day = getDay(date[1]);
			hour = getHour(date[length-1], startOrEnd);
			minute = getMinute(date[length-1], startOrEnd);
		}
		
		LocalDateTime ldt = LocalDateTime.of(year, month, day, hour, minute);
		
		return Timestamp.valueOf(ldt);
	}

	private static Month getMonthEndDate(String string) {
		String date[] = string.trim().split(" ");
		return getMonth(date[0]);
	}

	private static int getDayEndDate(String string) {
		String date[] = string.trim().split(" ");
		return Integer.parseInt(date[1]);
	}

	private static int getYearEndDate(String string) {
		return Integer.parseInt(string.trim());
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
				int hour = 12 + Integer.parseInt(hourVet[index].trim().substring(0,2));
				
				if(hour == 24)
					hour--;
				
				return hour;
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