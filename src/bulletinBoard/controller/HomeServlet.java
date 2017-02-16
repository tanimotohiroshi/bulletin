package bulletinBoard.controller;

import java.io.IOException;
import java.rmi.ServerException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import bulletinBoard.beans.User;
import bulletinBoard.beans.UserComment;
import bulletinBoard.beans.UserPostings;
import bulletinBoard.beans.Valid;
import bulletinBoard.service.CommentService;
import bulletinBoard.service.PostingService;

@WebServlet(urlPatterns = { "/home" })

public class HomeServlet extends HttpServlet {

	@Override

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {


		if ( StringUtils.isEmpty(request.getParameter("deletePosting")) == false) {
			int deletePostingId = Integer.parseInt(request.getParameter("deletePosting"));
			new PostingService().deletePosting(deletePostingId);
		}

		if ( StringUtils.isEmpty(request.getParameter("deleteComment")) == false) {
			int deleteCommentId = Integer.parseInt(request.getParameter("deleteComment"));
			new CommentService().deleteComment(deleteCommentId);
		}

		User user = (User) request.getSession().getAttribute("loginUser");

		boolean homePostings;
		if (user != null) {
			homePostings = true;
		} else {
			homePostings = false;
		}

		/*入力値の保持*/
		String category = request.getParameter("category");
		String date1 = request.getParameter("startDate");
		String date2 = request.getParameter("endDate");

		request.setAttribute("category", category);
		request.setAttribute("date1", date1);
		request.setAttribute("date2", date2);



		/* 投稿のupdateの時間をもつリスト */
		UserPostings datePostings = new PostingService().datePostings();
		String startDate = String.valueOf(datePostings.getInsertDate());

		/*今の日付と時間を文字列で*/
		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String endDate = sdf1.format(date);

		if (! StringUtils.isEmpty(request.getParameter("startDate")) == true){
			startDate = request.getParameter("startDate") + " 00:00:00";
		}

		if (! StringUtils.isEmpty(request.getParameter("endDate")) == true) {
				endDate = request.getParameter("endDate") + " 23:59:59";
		}



		List<UserPostings> validPostings = new PostingService().validPosting(startDate, endDate, category);

		HttpSession session = request.getSession();


		Valid valid = new Valid();

		valid.setCategory(category);
		valid.setStartDate(startDate);
		valid.setEndDate(endDate);


		session.setAttribute("postings", validPostings);
		request.setAttribute("homePosting", homePostings);


		List<UserComment> comments = new CommentService().getComment();
		session.setAttribute("comments", comments);

		request.getRequestDispatcher("/home.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException,ServerException{


	}
}
