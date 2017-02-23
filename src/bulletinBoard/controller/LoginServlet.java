package bulletinBoard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import bulletinBoard.beans.User;
import bulletinBoard.service.LoginService;

@WebServlet(urlPatterns = { "/login"} )

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID =1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException,ServletException{

		HttpSession session = request.getSession();
		Object loginCheck = session.getAttribute("loginUser");

		if ( loginCheck != null) {
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}

		request.getRequestDispatcher("login.jsp").forward(request, response);
	}




	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {


		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();

		if ( StringUtils.isEmpty(loginId)){
			messages.add("ログインIDを入力してください");
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		} else if ( StringUtils.isEmpty(password)){
			messages.add("パスワードを入力してください");
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

		LoginService loginService = new LoginService();
		User user = loginService.login(loginId, password);


		if (user != null) {
			if (user.getIsStopped() == 1) {
				messages.add("ログインに失敗しました");
				session.setAttribute("errorMessages", messages);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				session.setAttribute("loginUser", user);
				response.sendRedirect("./");
			}
		} else {
			messages.add("ログインに失敗しました");
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}
