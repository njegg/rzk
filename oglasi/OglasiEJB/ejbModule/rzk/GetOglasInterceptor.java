package rzk;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Ogla;

public class GetOglasInterceptor {
	
	@EJB
	private Statistika statistika;
	
	@PersistenceContext
	private EntityManager em;

	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {
		Ogla oglas = (Ogla) ctx.proceed(); // Povratna vrednost od getOglas(id)
		
		if (oglas != null) {
			statistika.updateMap(oglas);
		}
		
		return oglas;
	}
}
