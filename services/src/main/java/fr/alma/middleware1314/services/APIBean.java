package fr.alma.middleware1314.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.alma.middleware1314.api.API;
import fr.alma.middleware1314.api.Article;
import fr.alma.middleware1314.api.FluxRSS;
import fr.alma.middleware1314.api.User;

@Stateless
public class APIBean implements API {

	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean registerUser(String mail, String mdp) {
		// already existing user?
		Query q = em.createQuery("from UserBean u where u.mail= :mail");
		q.setParameter("mail", mail);
		List<?> users = q.getResultList();
		if (users.size() == 0) {
			//user doesn't exist
			if(mdp != null && !mdp.isEmpty()){
				UserBean newUser = new UserBean(mail, mdp);
				em.persist(newUser);
			}
		} else {
			return false;
		}
		return true;
	}

	@Override
	public String login(String mail, String mdp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FluxRSS addRSS(String token, FluxRSS rss) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delRSS(String token, FluxRSS rss) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Article> getNewArticles(String token, FluxRSS rss) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FluxRSS> getRSS(String token, String catName) {
		// TODO Auto-generated method stub
		return null;
	}

}
