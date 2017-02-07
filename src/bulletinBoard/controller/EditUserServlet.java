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

import bulletinBoard.beans.User;
import bulletinBoard.service.UserService;

@WebServlet(urlPatterns = { "/editUser" })

public class EditUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException{


		System.out.println(request.getParameter("id"));
		
		
		

		/*ここからURL直打ちのバリデーション*/
		HttpSession sessionValid = request.getSession();
		List<String> messages = new ArrayList<String>();

		if (isValid(request, messages) == true) {

			request.getRequestDispatcher("editUser.jsp").forward(request, response);
		} else {
			sessionValid.setAttribute("controlErrorMessages", messages);
			response.sendRedirect("./home");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");

		String departmentId = String.valueOf(user.getDepartmentId());

		if ( departmentId == null) {
			messages.add("ログインしてください");
		}

		if (user.getDepartmentId() != 1 ) {
			messages.add("管理画面には入れません");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		/*ユーザー項目に適しているか*/
		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();

		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int departmentId = Integer.parseInt(request.getParameter("departmentId"));

		if (isValidation(request, messages) == true) {

			User user = new User();
			user.setLoginId(request.getParameter("loginId"));
			user.setPassword(request.getParameter("password"));
			user.setName(request.getParameter("name"));
			user.setBranchId(branchId);
			user.setDepartmentId(departmentId);

			new UserService().register(user);

			response.sendRedirect("./controlUser");
		} else {
			session.setAttribute("errorMessages", messages);

			response.sendRedirect("./editUser");
		}
	}

	private boolean isValidation(HttpServletRequest request, List<String> messages) {
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");

		if (!loginId.matches("[a-zA-Z0-9]{6,20}")) {
			messages.add("半角英数字6文字以上で");
		}
		if (!password.matches("[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]{6,255}")) {
			messages.add("半角英数字6文字以上255文字以内で");
		}
		if (name.length() < 1) {
			messages.add("1文字以上で");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
