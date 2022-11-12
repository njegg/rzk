package rzk;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Ogla;
import model.OglasKorisnik;
import model.OglasPrijava;

/**
 * Session Bean implementation class OglasiBean
 */
@Stateful
@LocalBean
@Interceptors(OglasiBeanInterceptor.class)
public class OglasiBean implements OglasiBeanRemote {
	
	@PersistenceContext
	private EntityManager em;
	
	private OglasKorisnik loggedUser;
		
    /**
     * Default constructor. 
     */
    public OglasiBean() {
        // TODO Auto-generated constructor stub
    }
	
	@Override
	public int login(String username, String password) {
		String query = 
				"select u from OglasKorisnik u "
			  + "where u.username like :username "
			  + "and u.password like :password";
		
		Query q = em.createQuery(query)
				.setParameter("username", username)
				.setParameter("password", password);
		
		if (q.getResultList().size() == 0) return -1;
		loggedUser = (OglasKorisnik) q.getSingleResult();
		
		return loggedUser.getIdKorisnik();
	}

	@Override
	public boolean dodajOglas(String text) {
		if (loggedUser == null) return false;
		
		Ogla ogla = new Ogla();
		ogla.setOglasKorisnik(loggedUser);
		ogla.setText(text);
		ogla.setBrojPregleda(0);
		
		em.persist(ogla);
		
		return true;
	}

	@Override
	@Interceptors(PreglediInterceptor.class)
	public List<Ogla> pretraziOglase(String text) {
		String query = 
				"select o from Ogla o "
			  + "where o.text like :text "
			  + "or o.oglasKorisnik.username like :text ";
		
		List<Ogla> oglasi = em.createQuery(query, Ogla.class)
				.setParameter("text", '%' + text + '%')
//				.setParameter("username", '%' + text + '%')
				.getResultList();
		
		int userID = loggedUser.getIdKorisnik();
		
		// Oglasi logovanog usera na vrhu
		// Ako su razliciti bice 1, veliko -> dno liste, ako su isti bice -1, malo -> vrh liste
		oglasi.sort((a, b) ->
			a.getOglasKorisnik().getIdKorisnik() - userID == 0 ? -1 : 1
		);
		
		return oglasi;
	}
	
	@Override
	public OglasPrijava prijavaNaOglas(int oglasID, String text) {
		if (loggedUser == null) return null;

		Ogla oglas = em.find(Ogla.class, oglasID);
		
		if (oglas == null) return null;
		
		OglasPrijava oglasPrijava = new OglasPrijava();
		oglasPrijava.setOgla(oglas);
		oglasPrijava.setOglasKorisnik(loggedUser);
		oglasPrijava.setText(text);
		
		em.persist(oglasPrijava);
		
		return oglasPrijava;
	}

	public OglasKorisnik getLoggedUser() {
		return loggedUser;
	}

	@PostConstruct
	private void postConstruct() {
		System.out.println(getClass().getSimpleName() + " : Created");
	}

	@Override
	@Remove
	public void remove() {
		loggedUser = null;
	}
}
