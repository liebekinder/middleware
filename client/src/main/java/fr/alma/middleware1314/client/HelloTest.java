package fr.alma.middleware1314.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import fr.alma.middleware1314.services.sample.HelloRemote;

/**
 * @author Arnaud Thimel : thimel@codelutin.com
 */
public class HelloTest {

    public static void main(String[] args) throws Exception {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);
// Nom de la classe d'implémentation + /local ou /remote
        HelloRemote helloService = (HelloRemote) context.lookup("ejb:/reader-services-ejb-0.1-SNAPSHOT/HelloBean!fr.alma.middleware1314.services.sample.HelloRemote");
        String result = helloService.sayHello("Arno");
        System.out.println(result);
    }

}
