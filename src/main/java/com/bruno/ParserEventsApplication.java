package com.bruno;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bruno.dao.EventRepository;
import com.bruno.dao.VenueRepository;
import com.bruno.parser.ParserJournalStar;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@SpringBootApplication
public class ParserEventsApplication implements CommandLineRunner{

	@Autowired
	private EventRepository dao;
	@Autowired
	private VenueRepository venueRepo;
	
    public static void main(String[] args) {
        SpringApplication.run(ParserEventsApplication.class, args);
    }

	@Override
	public void run(String... arg0) throws Exception {
		
		/*RestTemplate template = new TestRestTemplate();
		VenueMongo[] venueList = template.getForEntity("http://eventslnk.elasticbeanstalk.com/venue", VenueMongo[].class).getBody();
		
		for(VenueMongo v : venueList){
			Venue venue = new Venue();
			venue.setAddress(v.getAddress());
			venue.setDescription(v.getVenueDescription());
			venue.setEmail(v.getEmail());
			venue.setFacebook(v.getFacebook());
			venue.setName(v.getVenueName());
			
			if(v.getTelephone().length() > 0)
				venue.setTelephone(v.getTelephone());
			
			venue.setTwitter(v.getTwitter());
			venue.setWebsite(v.getWebsite());
			venue.setCity("Lincoln, NE");
			dao.save(venue);
		}*/
		
		URL url = new URL("http://journalstar.com/calendar/search/?f=rss&c=calendar*&d1=now&s=start_time&sd=asc&unrolled=1&l=30");

		XmlReader reader = null;

		try {

			reader = new XmlReader(url);
			SyndFeed feed = new SyndFeedInput().build(reader);

			int total = 0;
			
			ParserJournalStar parser = new ParserJournalStar(dao,venueRepo);
			
			for(Object obj : feed.getEntries()){
				SyndEntry entry = (SyndEntry) obj;
				String link = entry.getLink();
				
				parser.buildEvent(link);
				
				total++;
				System.out.println("----------------");
			}
			System.out.println(total);
		} finally {
			if (reader != null)
				reader.close();
		}

	}
}
