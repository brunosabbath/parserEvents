package com.bruno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.bruno.dao.VenueRepository;
import com.bruno.model.Venue;
import com.bruno.model.VenueMongo;

@SpringBootApplication
public class ParserEventsApplication implements CommandLineRunner{

	@Autowired
	private VenueRepository dao;
	
    public static void main(String[] args) {
        SpringApplication.run(ParserEventsApplication.class, args);
    }

	@Override
	public void run(String... arg0) throws Exception {
		
		RestTemplate template = new TestRestTemplate();
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
		}

	}
}
