package com.bruno.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class TestHtmlParser {
	
	private static final int BARRIER = 10;
	
	public static void main(String[] args) {
		
		/*try{
			Document html = Jsoup.connect("http://www.lincoln.org/play/event/64526-european-motorcycle-night").get();
			//Document html = Jsoup.connect("http://www.lincoln.org/play/event/69766-gateway-to-glory-pep-rally").get();
			
			Element startDate = html.select("div.prop.display_date").first();
			System.out.println(startDate.text());
			
			Element venues = html.select("div.prop.venues").first();
			System.out.println(venues.text());
			
			Element location = html.select("div.prop.location").first();
			System.out.println(location.text());
			
			Element cost = html.select("div.prop.cost").first();
			System.out.println(cost.text());
			
			Element time = html.select("div.prop.time").first();
			System.out.println(time.text());
			
			Element website = html.select("div.prop.website").first();
			System.out.println(website.text());
			
			Element phone = html.select("div.prop.phone").first();
			System.out.println(phone.text());
			int index = phone.text().indexOf(":");
			System.out.println(phone.text().substring(index+2));
			
			
		} catch (IOException e){}
		  catch (NullPointerException n){}*/
		
		try {
			int increments = 0;
			int total = 100;
			
			Document html = Jsoup.connect("http://lincoln.org/?dstart=10%2F16%2F2015&os="+increments+"&cat=&dend=10%2F31%2F2015&keyword=").get();
			
			for(int i = 0; i < total; i++){
				
				if(increments < BARRIER ){
					Element event = html.select("div.info").get(increments);
					//System.out.println(event.select("h3").first());
					String url = event.select("h3").first().toString();
					
					url = getEventUrl(url);
					System.out.println(url);
					
					increments++;
				}
				else{
					html = Jsoup.connect("http://lincoln.org/?dstart=10%2F16%2F2015&os=" + i + "&cat=&dend=10%2F31%2F2015&keyword=").get();
					increments = 0;
				}
				
			}
			
		} catch (IOException e) {}
		
	}

	private static String getEventUrl(String url) {
		int start = url.indexOf("http");
		int end = url.lastIndexOf("title");
		
		return url.substring(start, end-1);
	}

	
}
