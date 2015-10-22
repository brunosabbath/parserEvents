package com.bruno.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParserJournalStar {

	public static void main(String[] args) {
		
		try {
			Document html = Jsoup.connect("http://journalstar.com/calendar/").get();
			
			Elements events = html.select("div.events-list");
			
			Elements ev = events.select("href");
			System.out.println("pow");
			
			System.out.println(ev.get(0).attr("href"));
			System.out.println(ev.get(1).attr("href"));
			
			
					
		} catch (IOException e) {}
		
	}
	
}
