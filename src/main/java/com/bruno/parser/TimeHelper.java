package com.bruno.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeHelper {

	public static String extractTime(String time) {
		//time = time.toLowerCase();
		//time = time.replaceAll(" ", "");
		//time = time.replaceAll(".", "");
		//time = time.replaceAll("-", "");
		
		Pattern p = Pattern.compile(".*XX~ (\\d{3,4}).*(\\d{1,2}:\\d{2}).*(\\d{1,2}:\\d{2})");
		Matcher matcher = p.matcher(time);
		String firstHour = matcher.group(0);
		String secondHour = matcher.group(1);
		
		return time;
	}

	public static int toPm(int hour){
		return hour + 12;
	}
	
	private int getHour(String time){
		return 0;
	}
	
	private int getMinute(String time){
		return 0;
	}
	
}
