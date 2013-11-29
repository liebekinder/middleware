package fr.alma.middleware1314.services;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import fr.alma.middleware1314.api.User;

@Entity
public class UserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8690045689335999761L;
	private String mail;
	private String mdp;// IMPOSSIBLE IN PRODUCTION!!!

	public UserBean(String mail, String mdp) {
		super();
		this.mail = mail;
		this.mdp = mdp;
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

}
