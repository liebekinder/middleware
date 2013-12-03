package fr.alma.middleware1314.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.alma.middleware1314.api.FluxRSS;
import fr.alma.middleware1314.api.User;

@Entity
public class UserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8690045689335999761L;
	private String mail;
	private String mdp;// IMPOSSIBLE IN PRODUCTION!!!
	private List<FluxRSS> flux;

	public UserBean(String mail, String mdp) {
		super();
		this.mail = mail;
		this.mdp = mdp;
		this.flux = new ArrayList<FluxRSS>();
	}

	public UserBean() {
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

	@OneToMany(mappedBy="articles")
	public List<FluxRSS> getFlux() {
		return flux;
	}
	public void setFlux(List<FluxRSS> flux) {
		this.flux = flux;
	}
	public void addFlux(FluxRSS flux) {
		this.flux.add(flux);
	}

}
