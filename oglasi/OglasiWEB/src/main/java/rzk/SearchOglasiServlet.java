package rzk;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Ogla;

/**
 * Servlet implementation class SearchOglasiServlet
 */
@WebServlet("/SearchOglasiServlet")
public class SearchOglasiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchOglasiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OglasiBeanRemote oglasiBean = (OglasiBeanRemote) request.getSession().getAttribute("sessionBean");
		
		if (oglasiBean == null) {
			request.setAttribute("msg", "Morate biti ulogovani da pretrazujete oglase");
			Forwarder.forward(request, response, "/error.jsp");
			return;
		}
		
		String text = request.getParameter("all") != null ?
				"" : request.getParameter("text");
		
		request.setAttribute("oglasi", oglasiBean.pretraziOglase(text));
		
		Forwarder.forward(request, response, "/search-oglas.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
