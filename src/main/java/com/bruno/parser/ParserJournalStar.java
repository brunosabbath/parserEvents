package com.bruno.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParserJournalStar {

	private static final String URL = "http://journalstar.com/calendar";
	
	public static void main(String[] args) {
		
		try {
			Document html = Jsoup.connect("http://journalstar.com/calendar/").get();
			
			Element events = html.select("ul.events-list").get(0);
			
			Elements links = events.select("a");
			
			System.out.println(links.size());
			
			String link = URL;
			
			for(int i = 0; i < links.size(); i++){
				link += links.get(i).attr("href");
				Document eventPage = Jsoup.connect(link).get();
				
				Elements title = eventPage.select("div.title-block");
				Elements description = eventPage.select("div.asset-body");
				//Elements title = eventPage.select("div.title-block");
				//Elements title = eventPage.select("div.title-block");
				
				
				System.out.println("pow");
				
			}
			
		} catch (IOException e) {}
		
	}
	
}
