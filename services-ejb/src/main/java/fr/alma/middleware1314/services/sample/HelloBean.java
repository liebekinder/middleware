package fr.alma.middleware1314.services.sample;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Arnaud Thimel : thimel@codelutin.com
 */
@Stateless
@Remote(HelloRemote.class)
public class HelloBean implements HelloRemote {

    @PersistenceContext
    private EntityManager em;

    @Override
    public String sayHello(String name) {
        Country country = new Country();
        country.setName(name);
        em.persist(country);
        return "Hello " + name;
    }

}
