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
			
			for(int i = 0; i < links.size(); i++){
				String link = URL + links.get(i).attr("href");
				Document eventPage = Jsoup.connect(link).get();

				Element titleDiv = eventPage.select("div.title-block").first();
				System.out.println(titleDiv.childNodes().get(1).childNode(0));
				System.out.println(titleDiv.childNodes().get(5).childNode(0).toString().trim());
				Element descriptionDiv = eventPage.select("div.event-description").first();
				System.out.println(descriptionDiv.select("p").text());
				
				Element venueDiv = eventPage.select("div.venue").first();
				System.out.println(venueDiv.select("div.title").text());
				
				//Elements description = eventPage.select("div.asset-body");
				
				//System.out.println("pow");
				
			}
			
		} catch (IOException e) {}
		
	}
	
}
