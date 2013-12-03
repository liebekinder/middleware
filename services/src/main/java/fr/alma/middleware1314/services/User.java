package fr.alma.middleware1314.services;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8690045689335999761L;
	private String mail;
	private String mdp;// IMPOSSIBLE IN PRODUCTION!!!
	private ArrayList<FluxRSS> flux = new ArrayList<FluxRSS>();

	public User(String mail, String mdp) {
		super();
		this.mail = mail;
		this.mdp = mdp;
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

	@Basic
	public ArrayList<FluxRSS> getFlux() {
		return flux;
	}
	public void setFlux(ArrayList<FluxRSS> flux) {
		this.flux = flux;
	}
	
	
	public void addFlux(FluxRSS flux) {
		this.flux.add(flux);
	}

}
