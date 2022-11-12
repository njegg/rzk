package rzk;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OglasiBeanRemote oglasiBean = (OglasiBeanRemote) request.getSession().getAttribute("sessionBean");
		
		if (oglasiBean == null) {
			request.setAttribute("msg", "Morate biti ulogovani da se izlogujete");
			Forwarder.forward(request, response, "/error.jsp");
			return;
		}
		
		request.getSession().removeAttribute("sessionBean");
		request.getSession().removeAttribute("username");
		
		oglasiBean.remove();
		
		response.sendRedirect("/OglasiWEB/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
