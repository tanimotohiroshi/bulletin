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


		String category = request.getParameter("category");
		String date1 = request.getParameter("startDate");
		String date2 = request.getParameter("endDate");

		List<UserPostings> postings = new PostingService().getPostings();
		/*投稿のupdateの時間をもつリスト*/
		List<UserPostings> datePostings = new PostingService().datePostings();
		if (date1.length() == 0 ) {
			UserPostings startDate = datePostings.get(0);
		} else {
			String startDate = date1;
		}



		List<UserPostings> validPostings = new PostingService().validPosting
				(startDate, endDate, category);





//		System.out.println(postingDate);
//		System.out.println(postings.get(2));
		UserPostings datePosting = datePostings.get(0);

		HttpSession session = request.getSession();

		if ( category == null) {
			session.setAttribute("postings", postings);
			session.setAttribute("datePostings", datePostings);
			request.setAttribute("homePosting", homePostings);
		} else {
//			session.setAttribute("postings", validPostings);
			request.setAttribute("homePosting", homePostings);
		}


		List<UserComment> comments = new CommentService().getComment();
		session.setAttribute("comments", comments);

		request.getRequestDispatcher("/home.jsp").forward(request, response);

	}







}
