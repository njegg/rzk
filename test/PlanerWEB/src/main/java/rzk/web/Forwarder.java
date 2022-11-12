package rzk.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Forwarder {
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher(path).forward(request, response);
	}
}
