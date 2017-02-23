package bulletinBoard.controller;

import java.io.IOException;
import java.rmi.ServerException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import bulletinBoard.beans.Category;
import bulletinBoard.beans.User;
import bulletinBoard.beans.UserComment;
import bulletinBoard.beans.UserPostings;
import bulletinBoard.service.CategoryService;
import bulletinBoard.service.CommentService;
import bulletinBoard.service.PostingService;
import bulletinBoard.service.UserService;

@WebServlet(urlPatterns = { "/index.jsp" })

public class HomeServlet extends HttpServlet {

	@Override

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		/* ページの切り替えのたびにユーザー情報を更新 */
		HttpSession session = request.getSession();
		User user = (User) request.getSession().getAttribute("loginUser");
		int id = user.getId();
		UserService userService = new UserService();
		User user1 = userService.getUserId(id);

		if ( user1 == null){
			session.invalidate();
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		} else {
			session.setAttribute("loginUser", user1);
		}


		/* カテゴリー表示 */
		List<Category> categoryList = new CategoryService().getCategory();
		request.setAttribute("categoryList", categoryList);

		boolean homePostings;
		homePostings = true;

		/* 入力値の保持 */
		String category = request.getParameter("getCategory");
		String date1 = request.getParameter("startDate");
		String date2 = request.getParameter("endDate");

		request.setAttribute("reCategory", category);
		request.setAttribute("date1", date1);
		request.setAttribute("date2", date2);

		/* 投稿のupdateの時間をもつリスト */
		UserPostings datePostings = new PostingService().datePostings();
		String startDate = String.valueOf(datePostings.getInsertDate());

		/* 今の日付と時間を文字列で */
		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String endDate = sdf1.format(date);

		if (!StringUtils.isEmpty(request.getParameter("startDate")) == true) {
			startDate = request.getParameter("startDate") + " 00:00:00";
		}

		if (!StringUtils.isEmpty(request.getParameter("endDate")) == true) {
			endDate = request.getParameter("endDate") + " 23:59:59";
		}

		/* 検索開始時間を調べる */
		List<String> timeErrorMessages = new ArrayList<String>();

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Date compareDate1 = sdf.parse(startDate);
			Date compareDate2 = sdf.parse(endDate);

			if (compareDate1.getTime() > compareDate2.getTime() || compareDate1.getTime() > date.getTime()) {

				timeErrorMessages.add("検索開始日時を確認してください");
				request.setAttribute("controlErrorMessages", timeErrorMessages);
				request.getRequestDispatcher("home.jsp").forward(request, response);
				return;
			}

		} catch (ParseException e) {
			timeErrorMessages.add("予期せぬエラーが発生しました。");
			request.setAttribute("controlErrorMessages", timeErrorMessages);
		}

		List<UserPostings> validPostings = new PostingService().validPosting(startDate, endDate, category);

		session.setAttribute("postings", validPostings);
		request.setAttribute("homePosting", homePostings);

		List<UserComment> comments = new CommentService().getComment();
		session.setAttribute("comments", comments);

		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServerException {

		if (StringUtils.isEmpty(request.getParameter("deletePosting")) == false) {
			int deletePostingId = Integer.parseInt(request.getParameter("deletePosting"));
			new PostingService().deletePosting(deletePostingId);
		}

		if (StringUtils.isEmpty(request.getParameter("deleteComment")) == false) {
			int deleteCommentId = Integer.parseInt(request.getParameter("deleteComment"));
			new CommentService().deleteComment(deleteCommentId);
		}

		response.sendRedirect("./");
	}
}
