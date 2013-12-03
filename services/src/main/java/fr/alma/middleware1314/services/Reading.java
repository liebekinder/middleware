package fr.alma.middleware1314.services;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

import fr.alma.middleware1314.api.Article;
import fr.alma.middleware1314.api.User;

@Entity
/**
 * Only store what the user have read. Allow to give him what he hasn't already read
 * @author seb
 *
 */
public class Reading {

	private long id;
	private User user;
	private Article article;

	@Id
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Basic
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Basic
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}
