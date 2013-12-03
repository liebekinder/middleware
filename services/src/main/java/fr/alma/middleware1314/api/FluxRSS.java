package fr.alma.middleware1314.api;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface FluxRSS {


	public List<Article> getArticles();
	public String getUrl();
	
}
