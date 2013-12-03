package fr.alma.middleware1314.services;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import fr.alma.middleware1314.api.Article;
import fr.alma.middleware1314.api.FluxRSS;

@Entity
public class FluxRSSBean implements Serializable, FluxRSS {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1269542453504111716L;
	
	private List<Article> articles;
	private String url;
	private String author;
	private String title;
	private String description;
	private Date publishDate; 
	
	
	public FluxRSSBean(String rssUrl) {
		super();
		URL source;
		try {
			source = new URL(rssUrl);
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(source));
			
			//construction as it
			this.url = rssUrl;
			this.author = feed.getAuthor();
			this.title = feed.getTitle();
			this.description = feed.getDescription();
			this.publishDate = feed.getPublishedDate();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FeedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean MAJArticles() throws IllegalArgumentException, MalformedURLException, FeedException, IOException{
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new XmlReader(new URL(url)));
		List<?> entries = feed.getEntries();
		Iterator<SyndEntry> i = (Iterator<SyndEntry>) entries.iterator();
		while(i.hasNext()){
			SyndEntry entry = i.next();
			ArticleBean article = new ArticleBean(entry);
			
		}
		return false;
		
	}


	@OneToMany(mappedBy="article")
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Id
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
