package com.bruno.parser.unl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.bruno.dao.EventRepository;
import com.bruno.dao.VenueRepository;
import com.bruno.model.Event;
import com.bruno.model.Venue;

public class UnlParser {

	private static final int INVALID_FOR_DATETIME = 6;
	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private VenueRepository venueRepo;
	private String url;
	private static final int EVENT_VENUE = 0;
	private static final String EVENT_TYPE_NAME = "EventTypeName";

	public UnlParser(EventRepository eventRepo, VenueRepository venueRepo, String url){
		this.eventRepo = eventRepo;
		this.venueRepo = venueRepo;
		this.url = url;
	}
	
	public UnlParser(String url){
		this.url = url;
	}
	
	public void parse(){
		try {

			String genreJson = IOUtils.toString(new URI(url),"UTF-8");
            JSONObject jsonObject = (JSONObject) JSONValue.parseWithException(genreJson);
			
			JSONArray events = (JSONArray) jsonObject.get("Events");
			
			for(int i = 0; i < events.size(); i++){
				JSONObject eventJson = (JSONObject) events.get(i);
	
				JSONArray arrayWebsites = (JSONArray) eventJson.get("WebPages");
				JSONArray eventTypeJson = (JSONArray) eventJson.get("EventTypes");
				
				if(eventHasValidWebsite(arrayWebsites) || eventHasValidType(eventTypeJson)){

					Event event = new Event();
					Venue venue = new Venue();
					
					String eventName = (String) eventJson.get("EventTitle");
					String description = (String) eventJson.get("Description");
					
					JSONObject venueJson = getVenueJson(eventJson);
					String venueName = (String) venueJson.get("LocationName");
					String city = "Lincoln, NE";
					
					JSONObject dateTime = getDateTimeJson(eventJson);
					String startStr = (String) dateTime.get("Start");
					String endStr = (String) dateTime.get("End");

					event.setStartDate(getCorrectDate(startStr)).setEndDate(getCorrectDate(endStr)).setAddress(venueName)
					.setDescription(description).setName(eventName);
					
					venue.setName(venueName).setAddress(venueName).setCity(city);
					
					save(venue, event);
					
					System.out.println();
				}
				
			}
			

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		} catch (URISyntaxException ex){}
	}

	private void save(Venue oldVenue, Event event) {
		try {
			
			Venue venue = venueRepo.findVenueByName(oldVenue.getName());
			
			if(venue == null){
				venue = oldVenue;
				venue = venueRepo.save(venue);
			}
			
			event.setVenue(venue);
			eventRepo.save(event);
			
		} catch (DataIntegrityViolationException e) {
			System.out.println("duplicated: " + event.getName());
		}
		
	}

	private Timestamp getCorrectDate(String date) {
		LocalDateTime ldt = LocalDateTime.parse(date.substring(0, date.length()-INVALID_FOR_DATETIME));
		return Timestamp.valueOf(ldt);
	}

	private JSONObject getDateTimeJson(JSONObject eventJson) {
		JSONObject dateTime = (JSONObject) eventJson.get("DateTime");
		return dateTime;
	}

	private JSONObject getVenueJson(JSONObject eventJson) {
		JSONArray arrayLocation = (JSONArray) eventJson.get("Locations");
		JSONObject venueJson = (JSONObject) arrayLocation.get(EVENT_VENUE);
		return venueJson;
	}

	private boolean eventHasValidType(JSONArray eventTypeJson) {
		JSONObject eventType = (JSONObject) eventTypeJson.get(0);
		String type = (String) eventType.get(EVENT_TYPE_NAME);
		
		if(type.contains("Arts") || type.contains("Activity") || type.contains("Social"))
			return true;
		
		return false;
	}

	private boolean eventHasValidWebsite(JSONArray arrayWebsites) {
		
		for(int j = 0; j < arrayWebsites.size(); j++){
			JSONObject website = (JSONObject) arrayWebsites.get(j);
			String websiteUrl = (String) website.get("URL");
			
			if(websiteUrl.contains("huskers") || websiteUrl.contains("eventbrite"))
				return true;
		}
		
		return false;
	}
	
}
