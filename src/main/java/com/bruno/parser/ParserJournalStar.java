package com.bruno.parser;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.bruno.model.Event;
import com.bruno.model.Venue;

import scala.annotation.meta.getter;

public class ParserJournalStar {

	public static void buildEvent(String link){
	
		Document eventPage;
		try {
			eventPage = Jsoup.connect(link).get();
			
			Event event = new Event();
			Venue venue = new Venue();
			
			Element titleDiv = eventPage.select("div.title-block").first();
			System.out.println("Title: " + titleDiv.childNodes().get(1).childNode(0));
			System.out.println("startDate: " + titleDiv.childNodes().get(5).childNode(0).toString().trim());
			String name = titleDiv.childNodes().get(1).childNode(0).toString();
			String startDate = titleDiv.childNodes().get(5).childNode(0).toString().trim();
			
			//event.setStartDate(parseStartDate(startDate));
			
			event.setName(name);
			
			Element descriptionDiv = eventPage.select("div.event-description").first();
			System.out.println("Description: " + descriptionDiv.select("p").text());
			String description = descriptionDiv.select("p").text();

			event.setDescription(description);
			
			Element endEvent = eventPage.select("div.schedule").first();
			String dateEnd = endEvent.childNodes().get(0).toString().trim();
			
			if(!dateEnd.isEmpty()){
				System.out.println("DateEnd: " + dateEnd);
			}
			
			try {
				Element priceDiv = eventPage.select("div.cost").first();
				System.out.println("preco: " + priceDiv.select("div.cost").text());
				String price = priceDiv.select("div.cost").text();
				event.setPrice(price);
			} catch (NullPointerException e) {}
			
			
			Element venueDiv = eventPage.select("div.venue").first();
			System.out.println("venue: " + venueDiv.select("div.title").text());
			String venueName = venueDiv.select("div.title").text();
			
			venue.setName(venueName);
			
			try {
				String address = venueDiv.childNodes().get(3).childNode(3).childNode(0).toString().trim();
				System.out.println("Address: " + address);
				event.setAddress(address);
				venue.setAddress(address);
			} catch (IndexOutOfBoundsException e) {}
			
			
			try {
				String city = venueDiv.childNodes().get(3).childNode(5).childNode(0).toString().trim();
				System.out.println("City: " + city);
				venue.setCity(city);
			} catch (IndexOutOfBoundsException e) {}
			
			event.setVenue(venue);
			
		} catch (IOException e) {
			System.out.println("deu merlim");
			buildEvent(link);
		}
		
	}

}