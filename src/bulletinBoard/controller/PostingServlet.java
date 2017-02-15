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

import bulletinBoard.beans.Posting;
import bulletinBoard.beans.User;
import bulletinBoard.service.PostingService;

@WebServlet(urlPatterns = { "/posting" })

public class PostingServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.getRequestDispatcher("posting.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request
			, HttpServletResponse response) throws
	IOException,ServerException{

		HttpSession session = request.getSession();

		List<String> messages= new ArrayList<String>();

		User user = (User) session.getAttribute("loginUser");

		Posting posting = new Posting();
		posting.setUserId(user.getId());
		posting.setTitle( request.getParameter("title"));
		posting.setMessage( request.getParameter("message"));
		posting.setCategory( request.getParameter("category"));

		if (isValid(request, messages) == true) {

			new PostingService().register(posting);

			response.sendRedirect("./home");

		} else {
			session.setAttribute("errorMessages", messages);
			request.setAttribute("posting", posting);
			response.sendRedirect("./posting");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String title = request.getParameter("title");
		String message = request.getParameter("message");
		String category = request.getParameter("category");

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


		if (category.length() == 0) {
			messages.add("カテゴリーを入力してください");
		} else if (category.length() > 11) {
			messages.add("カテゴリーは10文字以内で入力してください");
		}


		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}


	}

}

