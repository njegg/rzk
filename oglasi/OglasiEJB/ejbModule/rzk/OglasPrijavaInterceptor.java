package rzk;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class OglasPrijavaInterceptor {
	
	@EJB
	private Statistika statistika;
	
	@AroundInvoke
	private Object intercept(InvocationContext ctx) throws Exception {
		statistika.increaseDailyApplications();

		return ctx.proceed();
	}
}
