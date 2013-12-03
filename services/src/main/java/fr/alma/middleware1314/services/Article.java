package fr.alma.middleware1314.services;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.syndication.feed.synd.SyndEntry;

@Entity
public class Article implements Serializable, Comparable<Article>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2597415844214936502L;
	private String id;
	private String nom;
	private String author;
	private Date date;
	private String contenu;
	
	public Article() {
		super();
	}


	public Article(SyndEntry entry) {
		super();
		this.nom = entry.getTitle();
		this.id = entry.getUri();
		this.date = entry.getUpdatedDate();
		this.contenu = entry.getContents().toString();
		this.author = entry.getAuthor();
		
		
		//TODO beaucoup à ajouter http://www.docjar.org/docs/api/com/sun/syndication/feed/synd/SyndEntry.html
	}
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	
	@Basic
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	

	@Override
	public int compareTo(Article a) {
		if(this.date.compareTo(a.date) <0){
			return -1;
		}
		if(this.date.compareTo(a.date) >0){
			return 1;
		}
		return 0;
	}
	


	public boolean equals(Article a){
		return this.id.equals(a.id);
	}	
	
}
