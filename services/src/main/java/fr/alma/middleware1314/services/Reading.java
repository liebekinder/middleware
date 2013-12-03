package fr.alma.middleware1314.services;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Only store what the user have read. Allow to give him what he hasn't already read
 * @author seb
 *
 */
@Entity
public class Reading implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 174642223984550851L;
	private long id;
	private User user;
	private Article article;

	public Reading(){
		
	}

	
	public Reading(User user, Article article) {
		this.user = user;
		this.article = article;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see fr.alma.middleware1314.services.Reading#getUser()
	 */
	@Basic
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/* (non-Javadoc)
	 * @see fr.alma.middleware1314.services.Reading#getArticle()
	 */
	@Basic
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}
