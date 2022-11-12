package rzk;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Ogla;
import model.OglasPrijava;

/**
 * Session Bean implementation class AllOglasiBean
 */
@Singleton
@LocalBean
public class GetOglasiBean implements GetOglasiBeanRemote {

	@PersistenceContext
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public GetOglasiBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Ogla> getAllOglasi() {
		return em.createNamedQuery("Ogla.findAll", Ogla.class).getResultList();
	}

	@Override
	public Ogla getOglas(int idOglas) {
		return em.find(Ogla.class, idOglas);
	}

	@Override
	public List<OglasPrijava> getOglasPrijave(int idOglas) {
		String query = 
				"select p from OglasPrijava p "
			  + "where p.ogla.idOglas like :idOglas";
		
		return em.createQuery(query, OglasPrijava.class)
			.setParameter("idOglas", idOglas)
			.getResultList();
	}
}
