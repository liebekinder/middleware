package fr.alma.middleware1314.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.syndication.feed.synd.SyndEntry;

import fr.alma.middleware1314.api.Article;

@Entity
@Table(name = "ARTICLE")
public class ArticleBean implements Serializable,Article, Comparable<ArticleBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2597415844214936502L;
	private String id;
	private String nom;
	private Date date;
	private List<?> contenu;
	
	public ArticleBean() {
		super();
	}


	public ArticleBean(SyndEntry entry) {
		super();
		this.nom = entry.getTitle();
		this.id = entry.getUri();
		this.date = entry.getUpdatedDate();
		this.contenu = entry.getContents();
		
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
	public List<?> getContenu() {
		return contenu;
	}
	public void setContenu(List<?> contenu) {
		this.contenu = contenu;
	}

	@Override
	public int compareTo(ArticleBean a) {
		if(this.date.compareTo(a.date) <0){
			return -1;
		}
		if(this.date.compareTo(a.date) >0){
			return 1;
		}
		return 0;
	}
	
	public boolean equals(ArticleBean a){
		return this.id.equals(a.id);
	}
	
	
	
	
	
}
