package rzk.web;

import java.io.IOException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rzk.PlannerBean;
import rzk.PlannerBeanRemote;
import rzk.AccountBean;
import rzk.AccountBeanRemote;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Context initialContext;

	private static final String PKG_INTERFACES = "org.jboss.ejb.client.naming";

	public static Context getInitialContext() throws NamingException {
		if (initialContext == null) {
			Properties properties = new Properties();
			properties.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);
			initialContext = new InitialContext(properties);
		}
		return initialContext;
	}

	private static String getLookupName() {
		final String appName = "PlanerEAR";
		final String moduleName = "PlanerEJB";
		final String distinctName = "";
		final String beanName = AccountBean.class.getSimpleName();
		final String interfaceName = AccountBeanRemote.class.getName();
		String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + interfaceName;
		return name;
	}

	private static AccountBeanRemote doLookup() {
		Context context = null;
		AccountBeanRemote bean = null;
		try {
			context = getInitialContext();
			String lookupName = getLookupName();
			System.out.println("JNDI ime:   " + lookupName);
			bean = (AccountBeanRemote) context.lookup(lookupName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return bean;
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBeanRemote registerBean = doLookup();

		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		
		String redirectPath = registerBean.createAccount(firstName, lastName, email, password) ? "/index.jsp" : "/error.jsp";
		Forwarder.forward(request, response, redirectPath);
	}
}
