package com.bruno.parser.unl;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TestUnlParser {

	public static void main(String[] args) {
		
		//LocalDateTime l = LocalDateTime.parse("2015-12-08T19:00:00");
		Timestamp t = Timestamp.valueOf(LocalDateTime.parse("2015-12-08T19:00:00"));
		System.out.println(t);
		
		
		//String url = "https://events.unl.edu/upcoming/?format=json&limit=100";
		//UnlParser parser = new UnlParser(url);
		//parser.parse();
		
	}
	
}
