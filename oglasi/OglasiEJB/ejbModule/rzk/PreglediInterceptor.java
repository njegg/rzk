package rzk;

import java.util.List;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import model.Ogla;

public class PreglediInterceptor {

	@EJB
	Statistika statistika;
	
	@AroundInvoke
	public Object presretniPreglede(InvocationContext ctx) throws Exception {
		List<Ogla> oglasi = (List<Ogla>) ctx.getMethod().invoke(ctx.getTarget(), ctx.getParameters());

		for (Ogla o : oglasi) {
			statistika.updateMap(o);
		}
		
		return ctx.proceed();
	}
}
