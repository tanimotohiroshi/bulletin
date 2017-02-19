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

@WebServlet(urlPatterns = { "/signUp" })

public class SignUpServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException,ServletException {

		/*ページの切り替えのたびにユーザー情報を更新*/
		User user = (User) request.getSession().getAttribute("loginUser");
		int id = user.getId();
		UserService userService = new UserService();
		User user1 = userService.getUserId(id);
		request.setAttribute("loginUser", user1);

		HttpSession branchSession = request.getSession();
		HttpSession departmentSession = request.getSession();

		List<Branch> branchList = new BranchService().getBranch();
		branchSession.setAttribute("branchList", branchList);

		List<Department> departmentList = new DepartmentService().getDepartment();
		departmentSession.setAttribute("departmentList", departmentList);


		request.getRequestDispatcher("signUp.jsp").forward(request, response);

	}



	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();


		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int departmentId = Integer.parseInt(request.getParameter("departmentId"));


		User user = new User();
		user.setLoginId(request.getParameter("loginId"));
		user.setName(request.getParameter("name"));
		user.setBranchId(branchId);
		user.setDepartmentId(departmentId);


		if (isValidation(request, messages) == true) {

			user.setPassword(request.getParameter("password2"));

			try{
				new UserService().register(user);
				response.sendRedirect("./controlUser");

			} catch (RuntimeException e) {
				messages.add("ログインIDもしくは名前が既に使用されています");
				request.setAttribute("errorMessages", messages);
				request.setAttribute("editUser", user);
				request.getRequestDispatcher("signUp.jsp").forward(request, response);;

			}


		} else {
			request.setAttribute("errorMessages", messages);
			request.setAttribute("editUser", user);
			request.getRequestDispatcher("signUp.jsp").forward(request, response);
		}
	}

	private boolean isValidation(HttpServletRequest request, List<String> messages) {
		String loginId = request.getParameter("loginId");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String name = request.getParameter("name");

		if (!loginId.matches("[a-zA-Z0-9]{6,20}")) {
			messages.add("ログインIDは半角英数字6文字以上20文字以内で");
		}

		if (!password1.equals(password2)) {
			messages.add("パスワードが一致しません");
		}

		if (!password1.matches("[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]{6,255}")) {
			messages.add("パスワードは半角英数字6文字以上255文字以内で");
		}


		if (name.length() < 1 || name.length() > 11) {
			messages.add("名前は1文字以上10文字以内で");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
