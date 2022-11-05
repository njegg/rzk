package rzk;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.User;

/**
 * Session Bean implementation class RegisterBean
 */
@Stateless
@LocalBean
public class AccountBean implements AccountBeanRemote {

	@PersistenceContext
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public AccountBean() {}

	@Override
	public boolean createAccount(String firstName, String lastName, String email, String password) {
		Query q = em.createQuery("SELECT u FROM User u WHERE u.email LIKE :email ");
		q.setParameter("email", email);
		
		if (q.getResultList().size() > 0) {
			return false;
		}
		
		User newUser = new User();
		newUser.setEmail(email);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setPassword(password);

		em.persist(newUser);
		
		return true;
	}
}
