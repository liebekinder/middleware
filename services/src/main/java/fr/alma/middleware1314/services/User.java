package fr.alma.middleware1314.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 317541137568949546L;
	private String mail;
	private String mdp;// IMPOSSIBLE IN PRODUCTION!!!
	private List<FluxRSS> flux;

	public User(String mail, String mdp) {
		super();
		this.mail = mail;
		this.mdp = mdp;
		this.flux = new ArrayList<FluxRSS>();
	}

	public User() {
		super();
	}



	@Id
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	@Basic
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	@ManyToMany
	public List<FluxRSS> getFlux() {
		return flux;
	}
	public void setFlux(List<FluxRSS> listeFlux) {
		this.flux = listeFlux;
	}
	
	
	public void addFlux(FluxRSS flux) {
		this.flux.add(flux);
	}

}
