package fr.alma.middleware1314.services;

import java.net.URL;
import java.util.Iterator;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * @author Arnaud Thimel <thimel@codelutin.com>
 */
public class TestRss {

	public static void main(String[] args) throws Exception {
//		URL source = new URL("http://linuxfr.org/news.atom");
		URL source = new URL("http://boards.4chan.org/board/index.rss");
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new XmlReader(source));
		// System.out.println(feed);

		System.out.println("Feed Title: " + feed.getAuthor());

		for (Iterator<?> i = feed.getEntries().iterator(); i.hasNext();) {
			SyndEntry entry = (SyndEntry) i.next();
			System.out.println(entry.getTitle());
			System.out.println("---"+entry.getUri());
			System.out.println(entry.getContents().get(0).getClass());
		}

	}

}
