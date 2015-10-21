package com.bruno.parser;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class Test {
	
	private static final int DAY_POSITION = 3;
	private static final Map<String,Integer> months;
	
	static{
		months = new HashMap<String,Integer>();
		months.put("Jan", 0);
		months.put("Feb", 1);
		months.put("Mar", 2);
		months.put("Apr", 3);
		months.put("May", 4);
		months.put("Jun", 5);
		months.put("Jul", 6);
		months.put("Aug", 7);
		months.put("Sep", 8);
		months.put("Oct", 9);
		months.put("Nov", 10);
		months.put("Dec", 11);
	}
	
	public static void main(String[] args) {
		
		//String s = "Oct 9 - Oct 22";
		String s = "Oct 9";
		s = s.replaceAll(" ", "");
		String split[] = s.split("-");
		
		int startMonth = 0, startDay = 0, endMonth = 0, endDay = 0;
		startMonth = months.get(split[0].substring(0, 3));
		startDay = Integer.parseInt(split[0].substring(3, split[0].length()));

		if(split.length == 2){
			endMonth = months.get(split[1].substring(0, 3));
			endDay = Integer.parseInt(split[1].substring(3, split[1].length()));
		}
		
		System.out.println("endMonth: " + endMonth + " endDay: " + endDay);
		/*Date d = new GregorianCalendar(2015, oct, 21, 15, 19).getTime();
		System.out.println(d);
		System.out.println(new Timestamp(d.getTime()));*/
		
		//System.out.println(new Timestamp(dt.getTime()));
		
	}
	
}
