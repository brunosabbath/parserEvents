package com.bruno.parser;

import java.io.IOException;

import java.net.URL;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class ReadTest {
	public static void main(String[] args) throws IOException,
			IllegalArgumentException, FeedException {

		// RSSFeedParser parser = new RSSFeedParser("http://www.vogella.com/article.rss");
		// RSSFeedParser parser = new RSSFeedParser("https://events.unl.edu/upcoming/?format=rss&limit=100");
		//RSSFeedParser parser = new RSSFeedParser("http://journalstar.com/calendar/search/?f=rss&c=calendar*&d1=now&s=start_time&sd=asc&unrolled=1&l=25");

		/*Feed feed = parser.readFeed();

		for (FeedMessage message : feed.getMessages())
		 System.out.println(message);
		*/

		URL url = new URL("http://journalstar.com/calendar/search/?f=rss&c=calendar*&d1=now&s=start_time&sd=asc&unrolled=1&l=30");

		XmlReader reader = null;

		try {

			reader = new XmlReader(url);
			SyndFeed feed = new SyndFeedInput().build(reader);

			int total = 0;
			
			for(Object obj : feed.getEntries()){
				SyndEntry entry = (SyndEntry) obj;
				String link = entry.getLink();
				//System.out.println(link);
				//ParserJournalStar.buildEvent(link);
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
