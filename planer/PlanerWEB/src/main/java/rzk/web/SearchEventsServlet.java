package rzk.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Event;
import rzk.PlannerBean;
import rzk.PlannerBeanRemote;

/**
 * Servlet implementation class SearchEventsServlet
 */
@WebServlet("/SearchEventsServlet")
public class SearchEventsServlet extends HttpServlet {
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
		final String beanName = PlannerBean.class.getSimpleName();
		final String interfaceName = PlannerBeanRemote.class.getName();
		String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + interfaceName
				+ "?stateful";
		return name;
	}

	private static PlannerBeanRemote doLookup() {
		Context context = null;
		PlannerBeanRemote bean = null;
		try {
			context = getInitialContext();
			String lookupName = getLookupName();
			System.out.println("JNDI ime:   " + lookupName);
			bean = (PlannerBeanRemote) context.lookup(lookupName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return bean;
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEventsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PlannerBeanRemote plannerBean = (PlannerBeanRemote) request.getSession().getAttribute("sessionBean");

		if (plannerBean == null) {
			request.setAttribute("msg", "You must be loged in to do that!");
			Forwarder.forward(request, response, "/error.jsp");
		}
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date searchDate = null; 
		
		try {
			searchDate = simpleDateFormat.parse(request.getParameter("date"));
		} catch (ParseException e) {
			request.setAttribute("msg", "Error while processing the date");
			Forwarder.forward(request, response, "/error.jsp");
		}
		
		List<Event> searchResult = plannerBean.searchEvents(searchDate);
		request.setAttribute("searchResult", searchResult);
		Forwarder.forward(request, response, "/search-events.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
