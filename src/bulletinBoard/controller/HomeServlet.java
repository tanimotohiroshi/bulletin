package bulletinBoard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bulletinBoard.beans.User;
import bulletinBoard.beans.UserComment;
import bulletinBoard.beans.UserPostings;
import bulletinBoard.service.CommentService;
import bulletinBoard.service.PostingService;

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

		List<UserPostings> postings = new PostingService().getPostings();

		HttpSession session = request.getSession();

		session.setAttribute("postings", postings);
		request.setAttribute("homePosting", homePostings);

		List<UserComment> comments = new CommentService().getComment();


		session.setAttribute("comments", comments);



		request.getRequestDispatcher("/home.jsp").forward(request, response);

	}







}
