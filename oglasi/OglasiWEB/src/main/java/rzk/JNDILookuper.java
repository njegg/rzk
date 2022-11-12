package rzk;

import java.util.Properties;

import javax.ejb.Stateful;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JNDILookuper {

	private static Context initialContext;

	private static final String appName = "OglasiEAR";
	private static final String moduleName = "OglasiEJB";
	
	private static final String PKG_INTERFACES = "org.jboss.ejb.client.naming";

	static {
		Properties properties = new Properties();
		properties.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);
		
		try {
			initialContext = new InitialContext(properties);
		} catch (NamingException e) {
			e.printStackTrace();
			System.err.println("Unable to create InitialContext");
		}
	}
	
	@SuppressWarnings(value = {"unchecked", "rawtypes"})
	public static Object lookup(Class beanClass, Class remoteBeanClass) {
		boolean isStateful = beanClass.getAnnotation(Stateful.class) != null;
		
		final String beanName = beanClass.getSimpleName();
		final String interfaceName = remoteBeanClass.getName();
		
		String lookupName = String.format("ejb:%s/%s//%s!%s%s",
				appName, moduleName, beanName, interfaceName, isStateful ? "?stateful" : "");
		
		Object bean = null;
		
		try {
			bean = initialContext.lookup(lookupName);
		} catch (NamingException e) {
			e.printStackTrace();
			System.err.println("Lookup error");
		}
		
		return bean;
	}
}
