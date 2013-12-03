package fr.alma.middleware1314.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;

import fr.alma.middleware1314.api.API;
import fr.alma.middleware1314.services.ArticleClient;
import fr.alma.middleware1314.services.FluxRSSClient;

/**
 * @author Arnaud Thimel : thimel@codelutin.com
 */
public class HelloEjbTest {

	public static String token;
	
    public static void main(String[] args) throws Exception {
        Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);
        // Nom de la classe d'implémentation + /local ou /remote
        API middleware = (API) context.lookup("ejb:/reader-services-0.1-SNAPSHOT/APIBean!fr.alma.middleware1314.api.API");
        
        System.out.println("Que faire?");
        Scanner scan = new Scanner(System.in);
        int saisie = 1;
        while(saisie > 0){
        	System.out.println("(1) - ajout d'un utilisateur");
        	System.out.println("(2) - login d'un utilisateur");
        	System.out.println("(3) - ajout d'un flux");
        	System.out.println("(4) - lecture flux non lus");
        	System.out.println("(5) - list flux");
        	System.out.println("(0) - quitter");
        	saisie = scan.nextInt();
        	
        	switch(saisie){
        	case 1:
        		ajout(middleware);
        		break;
        	case 2:
        		login(middleware);
        		break;
        	case 3:
        		rss(middleware);
        		break;
        	case 4:
        		readNoRead(middleware);
        		break;
        	case 5:
        		recupereFlux(middleware);
        		break;
        	default:
        		break;
        	}
        	
        	
        }
        //HelloRemote helloService = (HelloRemote) context.lookup("ejb:/reader-services-ejb-0.1-SNAPSHOT/HelloBean!fr.alma.middleware1314.services.sample.HelloRemote");
    }

	private static List<FluxRSSClient> recupereFlux(API middleware) {
		if(token == null || token.isEmpty()) return null;		
		List<FluxRSSClient> flux = middleware.getRSS(token, null);
		for(FluxRSSClient f: flux){
			System.out.println(f.getTitle());
		}
		return flux;
	}

	private static void readNoRead(API middleware) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Quel flux recupérer?");
		List<FluxRSSClient> flux = recupereFlux(middleware);
		for(FluxRSSClient f: flux){
			System.out.println(f.getTitle());
		}
		int pass = scan.nextInt();
		if(pass <0 || pass >1) return;
		if(token == null || token.isEmpty()) return;
		

		List<ArticleClient> articles = (List<ArticleClient>) middleware.getNewArticles(token, flux.get(pass).getUrl());
		for(ArticleClient a:articles){
			System.out.println(a.getNom());
		}
	}

	private static void rss(API middleware) {
		List<String> fluxTemp = new ArrayList<String>(Arrays.asList("http://boards.4chan.org/b/index.rss","http://carottescuites.canalblog.com/rss.xml", "http://linuxfr.org/news.atom"));
		Scanner scan = new Scanner(System.in);
		System.out.println("Quel flux ajouter?");
		for(String url: fluxTemp){
			System.out.println(url);
		}
		int pass = scan.nextInt();
		if(pass <0 || pass > fluxTemp.size()) return;
		if(token == null || token.isEmpty()) return;
		
		
		FluxRSSClient monFlux = middleware.addRSS(token, fluxTemp.get(pass));
		System.out.println(monFlux.getTitle());
		
	}

	private static void login(API middleware) {
		Scanner scan = new Scanner(System.in);
		System.out.println("le nom");
		String nom = scan.next();
		if(nom == null || nom.isEmpty()) return;
		System.out.println("le password");
		String pass = scan.next();
		if(pass == null || pass.isEmpty()) return;
		
		token = middleware.login(nom, pass);
		
		System.out.println("@"+nom+"@"+pass+"@");
		System.err.println(token);
	}

	private static void ajout(API middleware) {
		Scanner scan = new Scanner(System.in);
		System.out.println("le nom");
		String nom = scan.next();
		if(nom == null || nom.isEmpty()) return;
		System.out.println("le password");
		String pass = scan.next();
		if(pass == null || pass.isEmpty()) return;
		
		System.err.println(middleware.registerUser(nom, pass));
		System.out.println("@"+nom+"@"+pass+"@");
	}

}
