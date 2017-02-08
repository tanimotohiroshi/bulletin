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

import bulletinBoard.beans.Branch;
import bulletinBoard.beans.Department;
import bulletinBoard.beans.User;
import bulletinBoard.service.BranchService;
import bulletinBoard.service.DepartmentService;
import bulletinBoard.service.UserService;

@WebServlet(urlPatterns = { "/editUser" })

public class EditUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		/* 支店、役職のセレクトボックスのsession */
		HttpSession branchSession = request.getSession();
		HttpSession departmentSession = request.getSession();

		List<Branch> branchList = new BranchService().getBranch();
		branchSession.setAttribute("branchList", branchList);

		List<Department> departmentList = new DepartmentService().getDepartment();
		departmentSession.setAttribute("departmentList", departmentList);

		int id = Integer.parseInt(request.getParameter("id"));



		User user = new UserService().getUserId(id);

		request.setAttribute("editUserReading", user);
		request.getRequestDispatcher("/editUser.jsp").forward(request, response);

		/* ここからURL直打ちのバリデーション */
		HttpSession sessionValid = request.getSession();
		List<String> messages = new ArrayList<String>();

		if (isValid(request, messages) == true) {

			request.getRequestDispatcher("/editUser.jsp").forward(request, response);
		} else {
			sessionValid.setAttribute("controlErrorMessages", messages);
			response.sendRedirect("./home");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");

		String departmentId = String.valueOf(user.getDepartmentId());

		if (departmentId == null) {
			messages.add("ログインしてください");
		}

		if (user.getDepartmentId() != 1) {
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

		/* ユーザー項目に適しているか */
		List<String> messages = new ArrayList<String>();
//		HttpSession session = request.getSession();

		int id = Integer.parseInt(request.getParameter("id"));
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int departmentId = Integer.parseInt(request.getParameter("departmentId"));
		String loginId = request.getParameter("loginId");
		String password1 = request.getParameter("password1");
		String name = request.getParameter("name");
		String password2 = request.getParameter("password2");
		String password3 = request.getParameter("password3");


		User user = new User();

		/* パスワードが空白の時 */
		if (password2.length() == 0 || password3.length() == 0) {
			String password = password1;
			user.setPassword(password);

		} else {
			String password = password2;
			if (!password.matches("[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]{6,255}")) {
				messages.add("半角英数字6文字以上255文字以内で");
			} else {
				user.setPassword(password2);
			}
		}



		user.setId(id);
		user.setLoginId(loginId);
		user.setName(name);
		user.setBranchId(branchId);
		user.setDepartmentId(departmentId);


		if (isValidation(request, messages) == true) {
			new UserService().update(user);
			response.sendRedirect("./controlUser");
		} else {
			request.setAttribute("editErrorMessages", messages);
			request.setAttribute("editUserReading", user );
			request.getRequestDispatcher("./editUser.jsp").forward(request, response);
		}
	}

	private boolean isValidation(HttpServletRequest request, List<String> messages) {
		String loginId = request.getParameter("loginId");
		String name = request.getParameter("name");
		String password2 = request.getParameter("password2");
		String password3 = request.getParameter("password3");





		/* 確認用のパスと入力のパスが同じかどうか */
		if (! password2.equals(password3) ) {
			messages.add("パスワードが一致しません");
		}


		if (!loginId.matches("[a-zA-Z0-9]{6,20}")) {
			messages.add("半角英数字6文字以上で");
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
