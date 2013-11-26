package fr.alma.middleware1314.services;

import java.util.List;

import javax.ejb.Stateless;

import fr.alma.middleware1314.api.EJBComponent;
import fr.alma.middleware1314.api.IArticle;
import fr.alma.middleware1314.api.IFluxRSS;


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
	public IFluxRSS addRSS(String token, IFluxRSS rss) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delRSS(String token, IFluxRSS rss) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IFluxRSS getFluxRSS(String token, IFluxRSS rss) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IArticle> getNewArticles(String token, IFluxRSS rss) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IArticle> getLastArticles(String token, IFluxRSS rss,
			int nbArticles) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean tagRss(String token, IFluxRSS rss, String catName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<IFluxRSS> getRSS(String token, String catName) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
