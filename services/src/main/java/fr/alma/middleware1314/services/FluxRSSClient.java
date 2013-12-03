package fr.alma.middleware1314.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FluxRSSClient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2859523652529772253L;
	private List<ArticleClient> articles = new ArrayList<ArticleClient>();
	private String url;
	private String author;
	private String title;
	private String description;
	private Date publishDate;
	
	
	public FluxRSSClient(List<ArticleClient> articles, String url,
			String author, String title, String description, Date publishDate) {
		super();
		this.articles = articles;
		this.url = url;
		this.author = author;
		this.title = title;
		this.description = description;
		this.publishDate = publishDate;
	}
	
	public FluxRSSClient(FluxRSS f){
		this.articles = new ArrayList<ArticleClient>();
		for(Article a:f.getArticles()){
			this.articles.add(new ArticleClient(a));
		}
		this.url = f.getUrl();
		this.author = f.getAuthor();
		this.title = f.getTitle();
		this.description = f.getDescription();
		this.publishDate = f.getPublishDate();
	}
	
	public List<ArticleClient> getArticles() {
		return articles;
	}
	public void setArticles(List<ArticleClient> articles) {
		this.articles = articles;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	
	
	
	
}
