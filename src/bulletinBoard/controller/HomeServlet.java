package bulletinBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bulletinBoard.beans.User;

@WebServlet(urlPatterns = { "/home" })

public class HomeServlet extends HttpServlet{

	@Override

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException,ServletException{
		
		User user = (User) request.getSession().getAttribute("loginUser");
		
		boolean homePostings;
		if (user != null) {
			homePostings = true;
		} else {
			homePostings = false;
		}


		request.getRequestDispatcher("/home.jsp").forward(request, response);

	}

}
