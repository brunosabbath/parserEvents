package com.bruno.parser;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.LocalDateTime;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.bruno.model.Event;

public class TestHtmlParser {

	private static final int START_VALID_TIME = 7;
	private static final int END_VALID_TIME = 9;
	private static final int REMOVE_AMPM = 2;
	private static final int BARRIER = 10;
	private static final String EVENT_NAME = "h1.name.h2";
	private static final String VENUE = "div.prop.venues";
	private static final String ADDRESS = "div.prop.location";
	private static final String PRICE = "div.prop.cost";
	private static final String TIME = "div.prop.time";
	private static final String DATE = "div.prop.display_date";
	private static final String DESCRIPTION = "p.description";
	private static final String YEAR = "div.date";
	private static final int START_MONTH = 0;
	private static final int END_MONTH = 3;
	private static final int START_DAY = 1;
	private static final int END_DAY = 4;
	private static final int START_TIME = 0;
	private static final int END_TIME = 1;

	public static void main(String[] args) {
		
		LocalDateTime date = LocalDateTime.now();
		
		try {
			int increments = 0;
			int total = 100;

			Document html = Jsoup.connect("http://lincoln.org/?dstart="+date.getMonthValue()+"%2F"+date.getDayOfMonth()+"%2F"+date.getYear()+"&os=" + increments + "&cat=&dend="+date.getMonthValue()+"%2F"+date.getDayOfYear()+"%2F"+date.getYear()+"&keyword=").get();

			for (int i = 0; i < total; i++) {

				if (increments < BARRIER) {

					// get an event
					Element singleEvent = html.select("div.info").get(increments);

					// select links from the selected event
					Elements links = singleEvent.select("a");

					// select link to current event
					String link = links.get(0).attr("href");

					Document eventPage = Jsoup.connect(link).get();

					String events[] = buildEvent(eventPage);

					//Event event = new Event();
					
					Event event = new Event();
					
					getStartDate(event, events[Constant.EVENT_DATE], events[Constant.EVENT_TIME], events[Constant.EVENT_YEAR]);
					
					/*LocalDateTime start = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
					LocalDateTime end = LocalDateTime.of(year, month, dayOfMonth, hour, minute);*/

					increments++;
					
				} else {
					
					html = Jsoup.connect("http://lincoln.org/?dstart="+date.getMonthValue()+"%2F"+date.getDayOfMonth()+"%2F"+date.getYear()+"&os=" + i + "&cat=&dend="+date.getMonthValue()+"%2F"+date.getDayOfYear()+"%2F"+date.getYear()+"&keyword=").get();
					increments = 0;
				}

			}

		} catch (IOException e) {
		}

	}

	private static void getStartDate(Event event, String date, String time, String year) {
		String dateStr[] = date.split(" ");
		
		int intYear = Integer.parseInt(year);
		
		Month startMonth;
		Month endMonth;
		
		startMonth = getMonth(dateStr, START_MONTH);
		endMonth = getMonth(dateStr, END_MONTH);
		
		LocalDateTime startDate, endDate;
		
		if(time.length() >= START_VALID_TIME && time.length() <= END_VALID_TIME){
			int startHour = getHour(time, START_TIME);
			int endHour = getHour(time, END_TIME);
			startDate = LocalDateTime.of(intYear, startMonth, Integer.parseInt(dateStr[START_DAY]), startHour, 0);
			endDate = LocalDateTime.of(intYear, endMonth, Integer.parseInt(dateStr[END_DAY]), endHour, 0);
		}
		else{
			startDate = LocalDateTime.of(intYear, startMonth, Integer.parseInt(dateStr[START_DAY]), 0, 0);
			endDate = LocalDateTime.of(intYear, endMonth, Integer.parseInt(dateStr[END_DAY]), 0, 0);
		}
		
		event.setStartDate(Timestamp.valueOf(startDate)).setEndDate(Timestamp.valueOf(endDate));
		
	}

	private static int getHour(String time, int index) {
		
		String strTime[] = time.split("-");
		
		int length = strTime[index].length();
		
		if(strTime[index].substring(length-REMOVE_AMPM, length).equalsIgnoreCase("am"))
			return Integer.parseInt(strTime[index].substring(0, length-REMOVE_AMPM));
		else 
			return 12 + Integer.parseInt(strTime[index].substring(0, length-REMOVE_AMPM));
		
	}

	private static Month getMonth(String[] dateStr, int index) {
		
		Month month;
		
		if(dateStr[index].equalsIgnoreCase("Jan"))
			month = Month.JANUARY;
		else if(dateStr[index].equalsIgnoreCase("Feb"))
			month = Month.FEBRUARY;
		else if(dateStr[index].equalsIgnoreCase("Mar"))
			month = Month.MARCH;
		else if(dateStr[index].equalsIgnoreCase("Apr"))
			month = Month.APRIL;
		else if(dateStr[index].equalsIgnoreCase("May"))
			month = Month.MAY;
		else if(dateStr[index].equalsIgnoreCase("Jun"))
			month = Month.JUNE;
		else if(dateStr[index].equalsIgnoreCase("Jul"))
			month = Month.JULY;
		else if(dateStr[index].equalsIgnoreCase("Aug"))
			month = Month.AUGUST;
		else if(dateStr[index].equalsIgnoreCase("Sep"))
			month = Month.SEPTEMBER;
		else if(dateStr[index].equalsIgnoreCase("Oct"))
			month = Month.OCTOBER;
		else if(dateStr[index].equalsIgnoreCase("Nov"))
			month = Month.NOVEMBER;
		else
			month = Month.DECEMBER;
		
		return month;
	}

	private static String[] buildEvent(Element eventPage) {

		//StringBuilder sb = new StringBuilder();
		String event[] = new String[8];
		
		try {
			Element year = eventPage.select(YEAR).first();
			//System.out.println(eventName.childNodes().get(0));
			//sb.append(eventName.childNode(0));
			event[Constant.EVENT_YEAR] = year.childNodes().get(4).childNode(0).toString();
		} catch (NullPointerException e) {}
		
		try {
			Element eventName = eventPage.select(EVENT_NAME).first();
			//System.out.println(eventName.childNodes().get(0));
			//sb.append(eventName.childNode(0));
			event[Constant.EVENT_NAME] = eventName.childNode(0).toString();

		} catch (NullPointerException e) {}
		
		try {
			Element description = eventPage.select(DESCRIPTION).first();
			//System.out.println(description.childNode(0));
			//sb.append(description.childNode(0));
			event[Constant.EVENT_DESCRIPTION] = description.childNode(0).toString(); 
		} catch (NullPointerException e) {}
		
		try {
			Element startDate = eventPage.select(DATE).first();
			//System.out.println(startDate.childNode(3).childNode(0));
			event[Constant.EVENT_DATE] = startDate.childNode(3).childNode(0).toString();
		} catch (NullPointerException e) {}

		try {
			Element venue = eventPage.select(VENUE).first();
			//System.out.println(venue.childNode(3).childNode(0));
			event[Constant.EVENT_VENUE] = venue.childNode(3).childNode(0).toString();
		} catch (NullPointerException e) {}

		try {
			Element address = eventPage.select(ADDRESS).first();
			//sb.append(address.text());
			//sb.append(address.childNode(3).childNode(0));
			event[Constant.EVENT_ADDRESS] = address.childNode(3).childNode(0).toString(); 
		} catch (NullPointerException e) {}
		
		try {
			Element cost = eventPage.select(PRICE).first();
			//sb.append(cost.text());
			//sb.append(cost.childNode(3).childNode(0));
			event[Constant.EVENT_PRICE] = cost.childNode(3).childNode(0).toString(); 
		} catch (NullPointerException e) {}

		try {
			Element time = eventPage.select(TIME).first();
			//sb.append(time.text());
			//sb.append(time.childNode(3).childNode(0));
			//System.out.println(time.childNode(3).childNode(0));
			event[Constant.EVENT_TIME] = time.childNode(3).childNode(0).toString();
		} catch (NullPointerException e) {}
		

		return event;
	}

}
