package bulletinBoard.controller;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bulletinBoard.beans.Comment;
import bulletinBoard.beans.User;
import bulletinBoard.service.CommentService;

@WebServlet(urlPatterns = { "/comment" })

public class CommentServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request
			, HttpServletResponse response) throws
	IOException,ServerException{

		HttpSession session = request.getSession();

		List<String> messages= new ArrayList<String>();



		if (isValid(request, messages) == true) {

			User user = (User) session.getAttribute("loginUser");

			System.out.println(request.getParameter("postingId"));
			System.out.println(request.getParameter("comment"));

			int postingId = Integer.parseInt(request.getParameter("postingId"));

			Comment comment = new Comment();
			comment.setPostingId( postingId);
			comment.setUserId( user.getId());
			comment.setMessage( request.getParameter("comment"));

			new CommentService().register(comment);

			response.sendRedirect("./home");

		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./home");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String message = request.getParameter("comment");


		if (message.length() == 0) {
			messages.add("コメント内容を入力してください");
		}


		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}


	}

}

