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

		/*ページの切り替えのたびにユーザー情報を更新*/
		User user1 = (User) request.getSession().getAttribute("loginUser");
		int userId = user1.getId();
		UserService userService = new UserService();
		User user2 = userService.getUserId(userId);
		request.setAttribute("loginUser", user2);

		/* 支店、役職のセレクトボックスのsession */
		HttpSession branchSession = request.getSession();
		HttpSession departmentSession = request.getSession();

		List<Branch> branchList = new BranchService().getBranch();
		branchSession.setAttribute("branchList", branchList);

		List<Department> departmentList = new DepartmentService().getDepartment();
		departmentSession.setAttribute("departmentList", departmentList);


		if ( StringUtils.isEmpty(request.getParameter("id")) == false && request.getParameter("id").matches("[0-9]+$")) {
			int id = Integer.parseInt(request.getParameter("id"));
			User user = new UserService().getUserId(id);

			request.setAttribute("editUserReading", user);
			request.getRequestDispatcher("editUser.jsp").forward(request, response);
		} else {
			List<String> messages = new ArrayList<String>();
			messages.add("不正なユーザー情報です");
			request.setAttribute("urlErrorMessage", messages);
			request.getRequestDispatcher("controlUser.jsp").forward(request, response);
		}

	}



	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {


		List<String> messages = new ArrayList<String>();

		int id = Integer.parseInt(request.getParameter("id"));
		String loginId = request.getParameter("loginId");
		String name = request.getParameter("name");
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int departmentId = Integer.parseInt(request.getParameter("departmentId"));
		String password1 = request.getParameter("password1");// 入力用パスワード
		String password2 = request.getParameter("password2");// 確認用パスワード

		User user = new User();

		user.setId(id);
		user.setLoginId(loginId);
		user.setName(name);
		user.setBranchId(branchId);
		user.setDepartmentId(departmentId);



		if (isValidation(request, messages) == true) {

			if (password1.length() == 0 || password2.length() == 0) {

				try{
					new UserService().update(user);
					response.sendRedirect("./controlUser");
				} catch ( RuntimeException e){
					messages.add("ログインIDもしくは名前が既に使用されています");
					request.setAttribute("editErrorMessages", messages);
					request.setAttribute("editUserReading", user);
					request.getRequestDispatcher("editUser.jsp").forward(request, response);

				}
			} else {
				try{
					user.setPassword(password2);
					new UserService().passwordUpdate(user);
					response.sendRedirect("./controlUser");
				} catch ( RuntimeException e) {
					messages.add("ログインIDもしくは名前が既に使用されています");
					request.setAttribute("editErrorMessages", messages);
					request.setAttribute("editUserReading", user);
					request.getRequestDispatcher("editUser.jsp").forward(request, response);
				}
			}


		} else {
			request.setAttribute("editErrorMessages", messages);
			request.setAttribute("editUserReading", user);
			request.getRequestDispatcher("editUser.jsp").forward(request, response);
		}
	}

	private boolean isValidation(HttpServletRequest request, List<String> messages) {

		String loginId = request.getParameter("loginId");
		String name = request.getParameter("name");
		String password1 = request.getParameter("password1");// 入力用パスワード
		String password2 = request.getParameter("password2");// 確認用パスワード


		if (!loginId.matches("[a-zA-Z0-9]{6,20}")) {
			messages.add("ログインIDは半角英数字6文字以上20文字以内で");
		}


		if (!password1.equals(password2)) {
			messages.add("パスワードが一致しません");
		}

		if (password1.length() != 0 || password2.length() != 0) {
			if (!password1.matches("[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]{6,255}")) {
				messages.add("パスワードは半角英数字6文字以上255文字以内で");
			}
		}


		if (name.length() < 1 || name.length() >11) {
			messages.add("名前は1文字以上もしくは10文字以内で");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}






}
