package rzk;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Ogla;
import model.OglasPrijava;

/**
 * Servlet implementation class OglasPrijavaServlet
 */
@WebServlet("/OglasPrijavaServlet")
public class OglasPrijavaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OglasPrijavaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OglasiBeanRemote oglasiBean = (OglasiBeanRemote) request.getSession().getAttribute("sessionBean");
		
		if (oglasiBean == null) {
			request.setAttribute("msg", "Morate biti ulogovani da se prijavite na oglas");
			Forwarder.forward(request, response, "/error.jsp");
			return;
		}

		int oglasID = Integer.parseInt(request.getParameter("oglasID"));
		
		GetOglasiBeanRemote getOglasiBean = (GetOglasiBeanRemote) JNDILookuper
				.lookup(GetOglasiBean.class, GetOglasiBeanRemote.class);
		
		Ogla oglas = getOglasiBean.getOglas(oglasID);
		
		if (oglas == null) {
			request.setAttribute("msg", "Oglas nije pronadjen");
			Forwarder.forward(request, response, "/error.jsp");
			return;
		}

		request.setAttribute("oglas", oglas);
		request.setAttribute("prijave", getOglasiBean.getOglasPrijave(oglasID));
		
		Forwarder.forward(request, response, "/oglas-prijava.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OglasiBeanRemote oglasiBean = (OglasiBeanRemote) request.getSession().getAttribute("sessionBean");
		
		if (oglasiBean == null) {
			request.setAttribute("msg", "Morate biti ulogovani da se prijavite na oglas");
			Forwarder.forward(request, response, "/error.jsp");
			return;
		}
		
		int oglasID = Integer.parseInt(request.getParameter("oglasID"));
		String text = request.getParameter("text");
		
		OglasPrijava oglasPrijava =  oglasiBean.prijavaNaOglas(oglasID, text);
		
		if (oglasPrijava == null) {
			request.setAttribute("msg", "Doslo je do greske");
			Forwarder.forward(request, response, "/error.jsp");
			return;
		}

		response.sendRedirect("/OglasiWEB/OglasPrijavaServlet?oglasID=" + oglasID);
	}

}
