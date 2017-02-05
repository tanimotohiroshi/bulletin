package bulletinBoard.controller;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/logout" })

public class LogoutServlet extends HttpServlet{

	@Override
	protected void doGet( HttpServletRequest request, HttpServletResponse response)
		throws ServerException, IOException{

		HttpSession session = request.getSession();

		session.invalidate();

		response.sendRedirect("./home");

	}
	

}

