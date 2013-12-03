package fr.alma.middleware1314.services;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
		
		return Tokens.requestNewToken(user);
	}

	@Override
	public FluxRSS addRSS(String token, String rssUrl) {
		User user = Tokens.getUserFromToken(token);
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
			}
			else {
				//TODO register in user
				//updtate existong articles ?
				//méthode de maj et retour tout
				return (FluxRSS) fluxRssList.get(0);
			}
			
//			for(ArticleBean article : generateArticlesFromRssFeed(rss)) {
//				Query q = em.createQuery("from ArticleBean a where a.id= :id");
//				q.setParameter("id", article.getId());
//				List<ArticleBean> articles = q.getResultList();
//				
//				//Max 1 article
//				if(articles.size()==0) {
//					em.persist(article);
//					retour.
//				}
//				else {
//					if(articles.get(0).getDate().getTime()<article.getDate().getTime()) em.persist(article);
//				}
//				
//			}
		}		
		return retour;
	}

	private List<Article> generateArticlesFromRssFeed(String rss) {
		URL source;
//		try {
//			source = new URL(rss);
//		} catch (MalformedURLException e) {
//			return null;
//		}
//		
		List<Article> returnList = new ArrayList<Article>();
//		
//		SyndFeedInput input = new SyndFeedInput();
//		SyndFeed feed;
//		try {
//			feed = input.build(new XmlReader(source));
//		} catch (Exception e) {
//			return null;
//		}
//
////		System.out.println("Feed Title: " + feed.getAuthor());
//
//		for (Iterator<?> i = feed.getEntries().iterator(); i.hasNext();) {
//			SyndEntry entry = (SyndEntry) i.next();
//			returnList.add(new Article(entry));
////			System.out.println(entry.getTitle());
////			System.out.println("---"+entry.getForeignMarkup().hashCode());
//		}
		return returnList;
	}

	@Override
	public boolean delRSS(String token, String rss) {
		User user = Tokens.getUserFromToken(token);
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
		User user = Tokens.getUserFromToken(token);
		if(user!=null) {
			for(FluxRSS flux : user.getFlux()) {
				if(flux.getUrl().equals(rss)) {
					//process : get new articles
					List<Article> articlesToReturn = new ArrayList<Article>();
					
					for(Article article : flux.getArticles()) {
						Query q = em.createQuery("from Reading r where r.article= :article AND r.user = :user");				
						q.setParameter("article", article);
						q.setParameter("user", user);
						List<Article> articles = q.getResultList();
						if(articles.size()==0) {
							//not read
							articlesToReturn.add(article);
							
							//marked readed
							em.persist(new Reading(user, article));
						}
					}
					return articlesToReturn;
				}
			}
		}
		return null;
	}

	@Override
	public List<FluxRSS> getRSS(String token, String catName) {
		// TODO Auto-generated method stub
		return null;
	}

}
