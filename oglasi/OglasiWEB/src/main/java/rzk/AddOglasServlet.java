package rzk;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddOglasServlet
 */
@WebServlet("/AddOglasServlet")
public class AddOglasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOglasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OglasiBeanRemote oglasiBean = (OglasiBeanRemote) request.getSession().getAttribute("sessionBean");
		
		if (oglasiBean == null) {
			request.setAttribute("msg", "Morate biti ulogovani da dodajete oglase");
			Forwarder.forward(request, response, "/error.jsp");
			return;
		}
		
		Forwarder.forward(request, response, "/add-oglas.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OglasiBeanRemote oglasiBean = (OglasiBeanRemote) request.getSession().getAttribute("sessionBean");
		
		if (oglasiBean == null) {
			request.setAttribute("msg", "Morate biti ulogovani da dodajete oglase");
			Forwarder.forward(request, response, "/error.jsp");
			return;
		}
		
		if (!oglasiBean.addOglas(request.getParameter("text"))) {
			request.setAttribute("msg", "Something went wrong");
			Forwarder.forward(request, response, "/error.jsp");
			return;
		}
		
		response.sendRedirect("/OglasiWEB/SearchOglasiServlet");
	}

}
