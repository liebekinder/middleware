package fr.alma.middleware1314.api;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface EJBComponent {

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
	public boolean deleteUser(String mail, String mdp);

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
	public boolean logout(String token);

	/**
	 * Store a new RSS in the user database
	 * 
	 * @param token
	 *            the identification token
	 * @param rssUrl
	 *            the RSS URL
	 * @return true if added successfully, false if RSS already exists or token
	 *         invalid;
	 */
	public boolean addRSS(String token, String rssUrl);

	/**
	 * delete RSS from database.
	 * 
	 * @param token
	 *            the identification token
	 * @param rssUrl
	 *            the RSS URL
	 * @return true if the deletion ended successfully, false if RSS doesn't
	 *         exist or token invalid.
	 */
	public boolean delRSS(String token, String rssUrl);

	/**
	 * Get RSS information as title, author, description.
	 * 
	 * @param token
	 *            the identification token
	 * @param rssUrl
	 *            the RSS URL
	 * @return the information or null if the RSS doesn't exist or invalid
	 *         token.
	 */
	public String getRssInformation(String token, String rssUrl);

	/**
	 * Get RSS unread articles. These article are marked as read.
	 * 
	 * @param token
	 *            the identification token
	 * @param rssUrl
	 *            the RSS URL
	 * @return the unread article list or null if RSS doesn't exist or token invalid.
	 */
	public List<String> getNewArticles(String token, String rssUrl);

	/**
	 * Get X latest RSS articles. These article are marked as read if not already.
	 * 
	 * @param token
	 *            the identification token
	 * @param rssUrl
	 *            the RSS URL
	 * @param nbArticles
	 * 			  The number of articles started from the latest
	 * @return the nbArticles latest article list or null if RSS doesn't exist or token invalid.
	 */
	public List<String> getLastArticles(String token, String rssUrl, int nbArticles);
	
	/**
	 * Allow to tag a RSS with a category. The category is created if it doesn't
	 * already exist.
	 * 
	 * @param token
	 *            the identification token
	 * @param rssUrl
	 *            the RSS URL
	 * @param catName
	 *            the category name
	 * @return true if tagged, false if RSS doesn't exist or invalid token.
	 */
	public boolean tagRss(String token, String rssUrl, String catName);

	/**
	 * Get RSS by category.
	 * @param token
	 *            the identification token
	 * @param catName
	 *            the category name
	 * @return a list of RSS URL.
	 */
	public List<String> getRSS(String token, String catName);	

//	/**
//	 * Update RSS in database, by reading remote content and storing new
//	 * content.
//	 * 
//	 * @param token
//	 *            the identification token
//	 * @return true if gather successfully ended, false if ended with error or
//	 *         token invalid.
//	 */
//	public boolean gatherRss(String token);
}
