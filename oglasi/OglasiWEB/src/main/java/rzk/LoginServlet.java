package rzk;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OglasiBeanRemote oglasiBean = (OglasiBeanRemote) JNDILookuper.lookup(OglasiBean.class, OglasiBeanRemote.class);
		
		if (oglasiBean == null) {
			request.setAttribute("msg", "Lookup greska");
			Forwarder.forward(request, response, "/error.jsp");
			return;
		}
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		int userID = oglasiBean.login(username, password);
		
		if (userID < 0) {
			request.setAttribute("msg", "Nesto nije u redu");
			Forwarder.forward(request, response, "/login.jsp");
			return;
		}
		
		request.getSession().setAttribute("sessionBean", oglasiBean);
		request.getSession().setAttribute("username", username);
		request.getSession().setAttribute("userID", userID);

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
