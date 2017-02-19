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

import org.apache.commons.lang.StringUtils;

import bulletinBoard.beans.Category;
import bulletinBoard.beans.Posting;
import bulletinBoard.beans.User;
import bulletinBoard.service.CategoryService;
import bulletinBoard.service.PostingService;
import bulletinBoard.service.UserService;

@WebServlet(urlPatterns = { "/posting" })

public class PostingServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		/*ページの切り替えのたびにユーザー情報を更新*/
		User user = (User) request.getSession().getAttribute("loginUser");
		int id = user.getId();
		UserService userService = new UserService();
		User user1 = userService.getUserId(id);
		request.setAttribute("loginUser", user1);


		/*カテゴリー表示*/
		List<Category> categoryList = new CategoryService().getCategory();
		request.setAttribute("categoryList", categoryList);


		request.getRequestDispatcher("posting.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request
			, HttpServletResponse response) throws IOException,ServerException{

		HttpSession session = request.getSession();

		List<String> messages= new ArrayList<String>();

		User user = (User) session.getAttribute("loginUser");

		Posting posting = new Posting();
		posting.setUserId(user.getId());
		posting.setTitle( request.getParameter("title"));
		posting.setMessage(request.getParameter("message"));
		String category = request.getParameter("category");
		String selectCategory = request.getParameter("getCategory");


		if (isValid(request, messages) == true) {


			if (StringUtils.isEmpty(category) == true) {
				posting.setCategory(selectCategory);
			} else if (StringUtils.isEmpty(selectCategory) == true) {
				posting.setCategory(category);
			}


			new PostingService().register(posting);

			response.sendRedirect("./");

		} else {
			session.setAttribute("errorMessages", messages);
			session.setAttribute("posting", posting);
			response.sendRedirect("./posting");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String title = request.getParameter("title");
		String message = request.getParameter("message");
		String category = request.getParameter("category");
		String selectCategory = request.getParameter("getCategory");



		if (title.length() < 1) {
			messages.add("タイトルを入力してください");
		} else if (title.length() > 51) {
			messages.add("50文字以下で入力してください");
		}


		if (message.length() < 1) {
			messages.add("投稿内容を入力してください");
		} else if (message.length() > 1001) {
			messages.add("投稿内容は1000文字以下で入力してください");
		}


		if (StringUtils.isEmpty(category) == true && StringUtils.isEmpty(selectCategory) == true) {
			messages.add("カテゴリーは新規で入力もしくは既存のどちらかを選択してください");
		} else if (StringUtils.isEmpty(category) == false && StringUtils.isEmpty(selectCategory) == false) {
			messages.add("カテゴリーは新規で入力もしくは既存のどちらかを選択してください");
		}


		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}


	}

}

