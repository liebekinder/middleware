package fr.alma.middleware1314.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import fr.alma.middleware1314.api.API;

/**
 * @author Arnaud Thimel : thimel@codelutin.com
 */
public class HelloEjbTest {

    public static void main(String[] args) throws Exception {
        Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);
        // Nom de la classe d'implémentation + /local ou /remote
        API helloService = (API) context.lookup("ejb:/reader-services-0.1-SNAPSHOT/APIBean!fr.alma.middleware1314.api.API");
        boolean result = helloService.registerUser("afza", "fe");
        //HelloRemote helloService = (HelloRemote) context.lookup("ejb:/reader-services-ejb-0.1-SNAPSHOT/HelloBean!fr.alma.middleware1314.services.sample.HelloRemote");
        System.out.println(result);
    }

}
