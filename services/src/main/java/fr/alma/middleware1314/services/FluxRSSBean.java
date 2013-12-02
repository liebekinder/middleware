package fr.alma.middleware1314.services;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	
	
	public FluxRSSBean() {
		super();
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
