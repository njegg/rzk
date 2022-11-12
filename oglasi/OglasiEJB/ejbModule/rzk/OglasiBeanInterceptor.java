package rzk;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class OglasiBeanInterceptor {
	@AroundInvoke
	public Object info(InvocationContext ctx) throws Exception {
		System.out.println("OglasiBean." + ctx.getMethod().getName() + " : Called");
		return ctx.proceed();
	}
}
