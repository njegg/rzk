package rzk;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptor;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.security.auth.spi.Users.User;

import model.OglasKorisnik;

/**
 * Session Bean implementation class RegisterBean
 */
@Stateless
@LocalBean
public class RegisterBean implements RegisterBeanRemote {
	
	@PersistenceContext
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public RegisterBean() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public boolean register(String username, String password, String nickname) {
    	Query q = em.createQuery("SELECT u FROM OglasKorisnik u WHERE u.username LIKE :username ")
    			.setParameter("username", username);
		
		if (q.getResultList().size() > 0) {
			return false;
		}
		
		OglasKorisnik newUser = new OglasKorisnik();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setNickname(nickname);
		
		em.persist(newUser);
		
		return true;
	}
}
