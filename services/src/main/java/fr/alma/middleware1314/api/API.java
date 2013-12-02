package fr.alma.middleware1314.api;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface API {

	/**
	 * Register new user.
	 * 
	 * @param mail
	 *            the mail of the user : unique
	 * @param mdp
	 *            the password associate to the mail
	 * @return true if registration OK, false if the user already exist.
	 */
	public boolean registerUser(String mail, String mdp);

	/**
	 * Delete existing user.
	 * 
	 * @param mail
	 *            the mail of the user : unique
	 * @param mdp
	 *            the password associate to the mail
	 * @return true if deletion OK, false if the user doesn't exist.
	 */
	//public boolean deleteUser(String mail, String mdp);

	/**
	 * User login.
	 * 
	 * @param mail
	 *            the mail of the user : unique
	 * @param mdp
	 *            the password associate to the mail
	 * @return an identification token or null if the identification failed.
	 */
	public String login(String mail, String mdp);

	/**
	 * Logout user.
	 * 
	 * @param token
	 *            the identification token
	 * @return true if logout successful, false else.
	 */
	//public boolean logout(String token);

	/**
	 * Store a new RSS in the user database
	 * 
	 * @param token
	 *            the identification token
	 * @param rss
	 *            the RSS
	 * @return true if added successfully, false if RSS already exists or token
	 *         invalid;
	 */
	public FluxRSS addRSS(String token, String rss);

	/**
	 * delete RSS from database.
	 * 
	 * @param token
	 *            the identification token
	 * @param rss
	 *            the RSS
	 * @return true if the deletion ended successfully, false if RSS doesn't
	 *         exist or token invalid.
	 */
	public boolean delRSS(String token, String rss);

	/**
	 * Get RSS information as title, author, description.
	 * 
	 * @param token
	 *            the identification token
	 * @param rss
	 *            the RSS
	 * @return the information or null if the RSS doesn't exist or invalid
	 *         token.
	 */
	//public FluxRSS getFluxRSS(String token, FluxRSS rss);

	/**
	 * Get RSS unread articles. These article are marked as read.
	 * 
	 * @param token
	 *            the identification token
	 * @param rss
	 *            the RSS
	 * @return the unread article list or null if RSS doesn't exist or token invalid.
	 */
	public List<Article> getNewArticles(String token, FluxRSS rss);

	/**
	 * Get X latest RSS articles. These article are marked as read if not already.
	 * 
	 * @param token
	 *            the identification token
	 * @param rss
	 *            the RSS
	 * @param nbArticles
	 * 			  The number of articles started from the latest
	 * @return the nbArticles latest article list or null if RSS doesn't exist or token invalid.
	 */
	//public List<Article> getLastArticles(String token, FluxRSS rss, int nbArticles);
	
	/**
	 * Allow to tag a RSS with a category. The category is created if it doesn't
	 * already exist.
	 * 
	 * @param token
	 *            the identification token
	 * @param rss
	 *            the RSS
	 * @param catName
	 *            the category name
	 * @return true if tagged, false if RSS doesn't exist or invalid token.
	 */
	//public boolean tagRss(String token, FluxRSS rss, String catName);

	/**
	 * Get RSS by category.
	 * @param token
	 *            the identification token
	 * @param catName
	 *            the category name
	 * @return a list of RSS. If catName is null, returns all FluxRSS
	 */
	public List<FluxRSS> getRSS(String token, String catName);	
}
