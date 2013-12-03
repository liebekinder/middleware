package fr.alma.middleware1314.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import fr.alma.middleware1314.api.API;
import fr.alma.middleware1314.api.Article;
import fr.alma.middleware1314.api.FluxRSS;

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
		Query q = em.createQuery("from UserBean u where u.mail= :mail");
		q.setParameter("mail", mail);
		List<?> users = q.getResultList();
		
		if(users.size()==0) return "ERR,0, user doesn't exist";
		
		//In theory we got 1 user
		UserBean user = (UserBean) users.get(0);
		if(user.getMdp().equals(mdp)) return "ERR,1,Wrong password";
		
		return Tokens.requestNewToken(user);
	}

	@Override
	public FluxRSS addRSS(String token, String rssUrl) {
		UserBean user = Tokens.getUserFromToken(token);
		FluxRSSBean retour = new FluxRSSBean();
		if(user!=null)
		{
			//requete sur les flux...
			Query q = em.createQuery("from FluxRSSBean a where a.url= :url");
			q.setParameter("url", rssUrl);
			List<FluxRSSBean> fluxRssList = q.getResultList();			
			
			if(fluxRssList.size()==0) {
				//TODO register in user
				//create
				FluxRSS newFlux = new FluxRSSBean();
			}
			else {
				//TODO register in user
				//updtate existong articles ?
				//méthode de maj et retour tout
				return fluxRssList.get(0);
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
		
		
		
		return null;
	}

	private List<ArticleBean> generateArticlesFromRssFeed(String rss) {
		URL source;
		try {
			source = new URL(rss);
		} catch (MalformedURLException e) {
			return null;
		}
		
		List<ArticleBean> returnList = new ArrayList<ArticleBean>();
		
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed;
		try {
			feed = input.build(new XmlReader(source));
		} catch (Exception e) {
			return null;
		}

//		System.out.println("Feed Title: " + feed.getAuthor());

		for (Iterator<?> i = feed.getEntries().iterator(); i.hasNext();) {
			SyndEntry entry = (SyndEntry) i.next();
			returnList.add(new ArticleBean(entry));
//			System.out.println(entry.getTitle());
//			System.out.println("---"+entry.getForeignMarkup().hashCode());
		}
		return returnList;
	}

	@Override
	public boolean delRSS(String token, String rss) {
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
