package fr.alma.middleware1314.services.sample;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * @author Arnaud Thimel : thimel@codelutin.com
 */
@Stateless
@Remote(HelloRemote.class)
public class HelloBean implements HelloRemote {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

}
