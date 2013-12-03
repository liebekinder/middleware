package fr.alma.middleware1314.services;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Entity
public class FluxRSS implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1269542453504111716L;

	private List<Article> articles = new ArrayList<Article>();
	private String url;
	private String author;
	private String title;
	private String description;
	private Date publishDate;

	public FluxRSS(){
		
	}
	
	public FluxRSS(String rssUrl) {
		super();
		URL source;
		try {
			source = new URL(rssUrl);
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(source));

			// construction as it
			this.url = rssUrl;
			this.author = feed.getAuthor();
			this.title = feed.getTitle();
			this.description = feed.getDescription();
			this.publishDate = feed.getPublishedDate();

			MAJArticles();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (FeedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void MAJArticles() {
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed;
		try {
			feed = input.build(new XmlReader(new URL(url)));
			List<?> entries = feed.getEntries();
			Iterator<?> i = entries.iterator();
			while (i.hasNext()) {
				SyndEntry entry = (SyndEntry) i.next();
				Article article = new Article(entry);
				// nouvel article: je récupère les article de ce flux, je
				// vérifie que ce nouveau existe ou non
				// s'il existe, je regarde sa date et met à jour si besoin
				// sinon, je l'ajoute
				int courant = articles.indexOf(article);
				if (courant > 0) {
					if (article.compareTo((Article) articles.get(courant)) > 0) {
						// update
						articles.set(courant, article);
					}
				} else {
					// add
					articles.add(article);
				}
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
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

	@Basic
	@OneToMany
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

	@Basic
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Basic
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Basic
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Basic
	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

}
