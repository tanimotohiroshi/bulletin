package bulletinBoard.controller;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bulletinBoard.beans.Posting;
import bulletinBoard.service.PostingService;

@WebServlet(urlPatterns = { "/posting" })

public class PostingServlet extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException,ServletException{

		request.getRequestDispatcher("posting.jsp").forward(request, response);
	}



	@Override
	protected void doPost(HttpServletRequest request
			, HttpServletResponse response) throws
	IOException,ServerException{

		Posting posting = new Posting();

		posting.setTitle( request.getParameter("title"));
		posting.setMessage( request.getParameter("message"));
		posting.setCategory( request.getParameter("category"));

		new PostingService().register(posting);

		response.sendRedirect("./home");




	}



}
