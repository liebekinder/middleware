package fr.alma.middleware1314.services;

import java.io.Serializable;
import java.util.Date;

public class ArticleClient implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7355420506548880754L;
	private String id;
	private String nom;
	private String author;
	private Date date;
	private String contenu;
	
	
	public ArticleClient(String id, String nom, String author, Date date,
			String contenu) {
		super();
		this.id = id;
		this.nom = nom;
		this.author = author;
		this.date = date;
		this.contenu = contenu;
	}
	
	public ArticleClient(Article a){
		this.id = a.getId();
		this.nom = a.getNom();
		this.author = a.getAuthor();
		this.date = a.getDate();
		this.contenu = a.getContenu();		
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getContenu() {
		return contenu;
	}


	public void setContenu(String contenu) {
		this.contenu = contenu;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
