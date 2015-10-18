package com.bruno.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestHtmlParser {

	private static final int BARRIER = 10;
	private static final String VENUE = "div.prop.venues";
	private static final String ADDRESS = "div.prop.location";
	private static final String PRICE = "div.prop.cost";
	private static final String TIME = "div.prop.time";
	private static final String WEBSITE = "div.prop.website";
	private static final String PHONE = "div.prop.phone";
	private static final String START_DATE = "div.prop.display_date";

	public static void main(String[] args) {

		try {
			int increments = 0;
			int total = 100;

			Document html = Jsoup
					.connect(
							"http://lincoln.org/?dstart=10%2F16%2F2015&os="
									+ increments
									+ "&cat=&dend=10%2F31%2F2015&keyword=")
					.get();

			for (int i = 0; i < total; i++) {

				if (increments < BARRIER) {

					// get an event
					Element singleEvent = html.select("div.info").get(
							increments);

					// select links from the selected event
					Elements links = singleEvent.select("a");

					// select link to current event
					String link = links.get(0).attr("href");

					Document eventPage = Jsoup.connect(link).get();

					StringBuilder event = buildEvent(eventPage);
					System.out.println(event);

					increments++;
				} else {
					html = Jsoup.connect(
							"http://lincoln.org/?dstart=10%2F16%2F2015&os=" + i
									+ "&cat=&dend=10%2F31%2F2015&keyword=")
							.get();
					increments = 0;
				}

			}

		} catch (IOException e) {
		}

	}

	private static StringBuilder buildEvent(Element eventPage) {

		StringBuilder sb = new StringBuilder();

		try {
			Element startDate = eventPage.select(START_DATE).first();
			sb.append(startDate.text());
		} catch (NullPointerException e) {}
		

		try {
			Element venue = eventPage.select(VENUE).first();
			sb.append(venue.text());
		} catch (NullPointerException e) {}
		

		try {
			Element address = eventPage.select(ADDRESS).first();
			sb.append(address.text());
		} catch (NullPointerException e) {}
		

		try {
			Element cost = eventPage.select(PRICE).first();
			sb.append(cost.text());
		} catch (NullPointerException e) {
		}

		try {
			Element time = eventPage.select(TIME).first();
			sb.append(time.text());
		} catch (NullPointerException e) {}
		

		try {
			Element website = eventPage.select(WEBSITE).first();
			sb.append(website.text());
		} catch (NullPointerException e) {}
		

		try {
			Element phone = eventPage.select(PHONE).first();
			int index = phone.text().indexOf(":");
			sb.append(phone.text().substring(index + 2));
		} catch (NullPointerException e) {}
		

		return sb;
	}

}
