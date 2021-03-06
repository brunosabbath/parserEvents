package com.bruno.parser.journal;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.dao.DataIntegrityViolationException;

import com.bruno.dao.EventRepository;
import com.bruno.dao.VenueRepository;
import com.bruno.model.Event;
import com.bruno.model.User;
import com.bruno.model.Venue;
import com.bruno.parser.Constant;
import com.bruno.utils.ParserUtils;

public class ParserJournalStar {
	
	private static final int HAS_END_DATE = 30;
	private static final Long OWNER_ID = (long) 1;
	private EventRepository eventRepo;
	private VenueRepository venueRepo;

	public ParserJournalStar(EventRepository dao, VenueRepository venueRepo) {
		this.eventRepo = dao;
		this.venueRepo = venueRepo;
	}

	public void buildEvent(String link) {
	
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
			
			event.setStartDate(ParserUtils.parseDate(startDate,Constant.START_DATE));
			event.updateDescription("Time: " + startDate);
			
			event.setName(name);
			
			Element endEvent = eventPage.select("div.schedule").first();
			String dateEnd = endEvent.childNodes().get(0).toString().trim();
			
			if(hasEndTime(startDate) && hasEndDate(dateEnd)){
				Timestamp date = ParserUtils.parseDate(dateEnd, Constant.END_DATE);
				Timestamp time = ParserUtils.parseDate(startDate, Constant.END_DATE);
				event.updateDescription(dateEnd);
				event.setEndDate(buildDateWithDateAndTime(date,time));
			}
			else if(hasEndTime(startDate)){
				event.setEndDate(ParserUtils.parseDate(startDate, Constant.END_DATE));
			}
			else if(hasEndDate(dateEnd)){
				event.setEndDate(ParserUtils.parseDate(dateEnd, Constant.END_DATE));
			}
			else if(!hasEndTime(startDate)){
				Timestamp time = ParserUtils.parseDate(startDate, Constant.START_DATE);
				event.setEndDate(buildDateNoHourNoMinute(time));
			}
			
			/*if(!dateEnd.isEmpty()){
				System.out.println("DateEnd: " + dateEnd);
				event.setEndDate(ParserUtils.parseDate(dateEnd, Constant.END_DATE));
				event.updateDescription(dateEnd);
			}
			else if(startDate.length() > HAS_END_DATE){
				event.setEndDate(ParserUtils.parseDate(startDate, Constant.END_DATE));
			}*/
			
			Element descriptionDiv = eventPage.select("div.event-description").first();
			System.out.println("Description: " + descriptionDiv.select("p").text());
			String description = descriptionDiv.select("p").text();

			event.updateDescription(description);
			
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
			
			String address = "";
			try {
				address = venueDiv.childNodes().get(3).childNode(3).childNode(0).toString().trim();
				
				System.out.println("Address: " + address);
				event.setAddress(address);
				venue.setAddress(address);
			} catch (IndexOutOfBoundsException e) {}
			
			if(address.isEmpty()){
				try {
					address = venueDiv.childNodes().get(1).childNode(3).childNode(0).toString().trim();
					System.out.println("Address: " + address);
					event.setAddress(address);
					venue.setAddress(address);
				} catch (IndexOutOfBoundsException e) {
					event.setAddress(venueName);
					venue.setAddress(venueName);
				}
			}
			
			
			String city = "";
			
			try {
				city = venueDiv.childNodes().get(3).childNode(5).childNode(0).toString().trim();
				System.out.println("City: " + city);
				venue.setCity(city);
			} catch (IndexOutOfBoundsException e) {}
			
			if(city.isEmpty()){
				city = venueDiv.childNodes().get(1).childNode(5).childNode(0).toString().trim();
				System.out.println("City: " + city);
				venue.setCity(city);
			}
			
			User owner = new User();
			owner.setId(OWNER_ID);
			
			event.setOwner(owner);
			
			save(event, venue, venueName);
			
			
		} catch (IOException e) {
			System.out.println("deu merlim");
			buildEvent(link);
		}
	}

	private void save(Event event, Venue venue, String venueName) {
		try {
			
			Venue dbVenue = venueRepo.findVenueByName(venueName);
			
			if(dbVenue != null){
				Venue newVenue = new Venue();
				newVenue.setId(dbVenue.getId());
				event.setVenue(newVenue);
			}
			else{
				venue = venueRepo.save(venue);
				event.setVenue(venue);
			}
			
			Event oldEvent = eventRepo.findEventByNameAndStartDateAndEndDate(event.getName(), event.getStartDate(), event.getEndDate());
			
			if(oldEvent == null){
				eventRepo.save(event);
			}
			else{
				System.out.println("duplicated: " + event.getName());
			}
			
			
		} catch (DataIntegrityViolationException  e) {
			System.out.println("duplicated: " + event.getName());
		}
	}

	private Timestamp buildDateWithDateAndTime(Timestamp date, Timestamp time) {
		
		LocalDateTime ldtDate = date.toLocalDateTime();
		LocalDateTime ldtTime = time.toLocalDateTime();
		
		return Timestamp.valueOf(LocalDateTime.of(ldtDate.toLocalDate(), ldtTime.toLocalTime()));
	}

	private Timestamp buildDateNoHourNoMinute(Timestamp time) {
		LocalDateTime original = time.toLocalDateTime();
		
		LocalTime newTime = LocalTime.of(0, 0);
		
		return Timestamp.valueOf(LocalDateTime.of(original.toLocalDate(), newTime));
	}

	private boolean hasEndDate(String dateEnd) {
		return !dateEnd.isEmpty();
	}

	private boolean hasEndTime(String startDate) {
		return startDate.length() > HAS_END_DATE;
	}
}