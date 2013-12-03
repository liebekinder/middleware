package fr.alma.middleware1314.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.alma.middleware1314.api.API;

@Stateless
public class APIBean implements API {

	@PersistenceContext
	private EntityManager em;
	

	@Override
	public boolean registerUser(String mail, String mdp) {
//		em.refresh(user);
		// already existing user?
		Query q = em.createQuery("from User u where u.mail= :mail");
		q.setParameter("mail", mail);
		List<?> users = q.getResultList();
		if (users.size() == 0) {
			//user doesn't exist
			if(mdp != null && !mdp.isEmpty()){
				User newUser = new User(mail, mdp);
				em.persist(newUser);
			}
		} else {
			return false;
		}
		return true;
	}

	@Override
	public String login(String mail, String mdp) {
		Query q = em.createQuery("from User u where u.mail= :mail");
		q.setParameter("mail", mail);
		List<?> users = q.getResultList();
		
		if(users.size()==0) return "ERR,0, user doesn't exist";
		
		//In theory we got 1 user
		User user = (User) users.get(0);
		if(!user.getMdp().equals(mdp)) return "ERR,1,Wrong password "+user.getMail()+" "+user.getMdp();
		
		return this.requestNewToken(user);
	}

	@Override
	public FluxRSS addRSS(String token, String rssUrl) {
		User user = this.getUserFromToken(token);
		System.out.println(user.getMail());
		FluxRSS retour = null;
		if(user!=null)
		{
			//requete sur les flux...
			Query q = em.createQuery("from FluxRSS a where a.url= :url");
			q.setParameter("url", rssUrl);
			List<?> fluxRssList = q.getResultList();			
			
			if(fluxRssList.size()==0) {
				//TODO register in user
				//create
				retour = new FluxRSS(rssUrl);
				em.persist(retour);
			}
			else {
				retour = (FluxRSS) fluxRssList.get(0);
				retour.MAJArticles();
			}

			user.addFlux(retour);
		}
		return retour;
//		return new FluxRSSClient(retour);
	}

	@Override
	public boolean delRSS(String token, String rss) {
		User user = this.getUserFromToken(token);
		if(user!=null) {
			List<FluxRSS> listeFlux = user.getFlux();
			
			FluxRSS toDelete = null;
			for(FluxRSS fRss : listeFlux) {
				if(fRss.getUrl().equals(rss)) {
					toDelete = fRss;
					break;
				}
			}
			if(toDelete==null) return false;
			
			listeFlux.remove(toDelete);
			user.setFlux(listeFlux);
			return true;
		}
		return false;
	}

	@Override
	public List<Article> getNewArticles(String token, String rss) {
		User user = this.getUserFromToken(token);
		if(user!=null) {
			for(FluxRSS flux : user.getFlux()) {
				if(flux.getUrl().equals(rss)) {
					//process : get new articles
					List<Article> articlesToReturn = new ArrayList<Article>();
					
					for(Article article : flux.getArticles()) {
						Query q = em.createQuery("from Reading r where r.article= :article AND r.user = :user");				
						q.setParameter("article", article);
						q.setParameter("user", user);
						List<Article> articles = (ArrayList<Article>) q.getResultList();
						if(articles.size()==0) {
							//not read
							articlesToReturn.add(article);
							
							//marked readed
							em.persist(new Reading(user, article));
						}
					}
//					List<ArticleClient> ac = new ArrayList<ArticleClient>();
//					for(Article a: articlesToReturn){
//						ac.add(new ArticleClient(a));
//					}
//					return ac;
					return articlesToReturn;
				}
			}
		}
		return null;
	}

	@Override
	public List<FluxRSS> getRSS(String token, String catName) {
		User user = this.getUserFromToken(token);
		if(user!=null) {
			if(catName==null) {
//				List<FluxRSSClient> ac = new ArrayList<FluxRSSClient>();
//				for(FluxRSS a: user.getFlux()){
//					ac.add(new FluxRSSClient(a));
//				}
//				return ac;
				return user.getFlux();
			}
		}
		return null;
	}
	
	
	
	//TOken management
	
	/**
	 * Timeout in seconds
	 */
	protected final int timeout = 10*60;
	/**
	 * Check time in seconds
	 */
	protected final int checkTime = 60;

	protected Map<String,User> correlation = new HashMap<String,User>();
	protected Map<String,Long> timeRemaining = new HashMap<String,Long>();
	private TokenCleaner cleaner = new TokenCleaner();
	
	
	public String requestNewToken(User user) {
		if(correlation.size()==0) launchCleaner();
		UUID uuid = UUID.randomUUID();
		String token = uuid.toString();
		correlation.put(token, user);
		timeRemaining.put(token, new Date().getTime());
		return token;
	}

	private void launchCleaner() {
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(cleaner, 0, timeout, TimeUnit.SECONDS);
	}

	public User getUserFromToken(String token) {
		System.out.println(token);
		for(String t:correlation.keySet()){
			System.out.println(t);
		}
		if(correlation.containsKey(token)) {
			timeRemaining.put(token, new Date().getTime());
			User user = correlation.get(token);
//			em.refresh(user);
			return user;
		}
		return null;
	}

	private class TokenCleaner implements Runnable {

		@Override
		public void run() {
			List<String> markedForRemove = new ArrayList<String>();
			for(String token : timeRemaining.keySet()) {
				if(timeRemaining.get(token)+timeout*1000>new Date().getTime()) markedForRemove.add(token);
			}
			for(String token : markedForRemove) {
				timeRemaining.remove(token);
				correlation.remove(token);
			}
		}
		
	}
	
	//end token management

}
