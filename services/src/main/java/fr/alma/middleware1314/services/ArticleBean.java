package fr.alma.middleware1314.services;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import fr.alma.middleware1314.api.Article;

@Entity
@Table(name = "ARTICLE")
public class ArticleBean implements Serializable,Article {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2597415844214936502L;
	private long id;
	private String nom;
	private Date date;
	private String contenu;
	
	public ArticleBean() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Basic
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Basic
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	@Basic
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	
	
	
}
