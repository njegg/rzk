package rzk.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rzk.PlannerBeanRemote;

/**
 * Servlet implementation class AddEventServlet
 */
@WebServlet("/AddEventServlet")
public class AddEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEventServlet() {
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
		
		request.getSession().setAttribute("types", plannerBean.getTypes());
		
		// Za postavljanje datuma u formi na trenutno vreme
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");		
        Date today = Calendar.getInstance().getTime();
        request.setAttribute("today", df.format(today));
		
		request.getServletContext().getRequestDispatcher("/add-event.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PlannerBeanRemote plannerBean = (PlannerBeanRemote) request.getSession().getAttribute("sessionBean");
		
		if (plannerBean == null) {
			request.setAttribute("msg", "You must be loged in to do that!");
			Forwarder.forward(request, response, "/error.jsp");
		}

		String fromDateParam = request.getParameter("fromDate");
		String toDateParam = request.getParameter("toDate");
		
		int eventTypeId = Integer.parseInt(request.getParameter("eventTypeId"));
		String description = request.getParameter("description");
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
		Date fromDate = null; 
		Date toDate = null; 
		try {
			fromDate = simpleDateFormat.parse(fromDateParam);
			toDate = simpleDateFormat.parse(toDateParam);
		} catch (ParseException e) {
			request.setAttribute("msg", "Error while processing the date");
			Forwarder.forward(request, response, "/error.jsp");
		}

		String msg = plannerBean.createEvent(description, fromDate, toDate, eventTypeId) ?
			"Success!" :
			"Oops! Something went wrong.";
		
		request.setAttribute("msg", msg);
		Forwarder.forward(request, response, "/add-event.jsp");
	}

}
