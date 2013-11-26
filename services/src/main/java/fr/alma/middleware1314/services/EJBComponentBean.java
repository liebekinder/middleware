package fr.alma.middleware1314.services;

import java.util.List;

import javax.ejb.Stateless;

import fr.alma.middleware1314.api.EJBComponent;


public @Stateless
class EJBComponentBean implements EJBComponent {

	@Override
	public boolean registerUser(String mail, String mdp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(String mail, String mdp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String login(String mail, String mdp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean logout(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addRSS(String token, String rssUrl) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delRSS(String token, String rssUrl) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getRssInformation(String token, String rssUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getNewArticles(String token, String rssUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getLastArticles(String token, String rssUrl,
			int nbArticles) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean tagRss(String token, String rssUrl, String catName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> getRSS(String token, String catName) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
