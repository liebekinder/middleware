package fr.alma.middleware1314.client;

import java.util.Hashtable;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;

import fr.alma.middleware1314.api.API;

/**
 * @author Arnaud Thimel : thimel@codelutin.com
 */
public class HelloEjbTest {

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
        	saisie = scan.nextInt();
        	System.out.println("(1) - ajout d'un utilisateur");
        	System.out.println("(2) - login d'un utilisateur");
        	System.out.println("(3) - ajout d'un flux");
        	System.out.println("(4) - vide");
        	System.out.println("(5) - vide");
        	System.out.println("(1) - vide");
        	
        	
        }
        boolean result = middleware.registerUser("afza", "fe");
        //HelloRemote helloService = (HelloRemote) context.lookup("ejb:/reader-services-ejb-0.1-SNAPSHOT/HelloBean!fr.alma.middleware1314.services.sample.HelloRemote");
        
        
        System.out.println(result);
    }

}
