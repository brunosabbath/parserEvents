package com.bruno.utils;

import java.sql.Timestamp;

public class TestUtils {

	public static final int START = 0;
	public static final int END = 3;
	
	public static void main(String[] args) {
		
		String date = "Thu, Dec 3 2015, All Day";
		//String date = "Thu, Dec 3 2015, 10:25 am - 8:10 pm CST";
		Timestamp newDate = ParserUtils.parseStartDate(date,END);
		System.out.println(newDate);
		
	}
	
}
