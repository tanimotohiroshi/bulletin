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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,

			ServletException {

		List<Branch> branchList = new BranchService().getBranch();
		request.setAttribute("branchList", branchList);

		List<Department> departmentList = new DepartmentService().getDepartment();
		request.setAttribute("departmentList", departmentList);

		request.getRequestDispatcher("signUp.jsp").forward(request, response);
	}



	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();


		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int departmentId = Integer.parseInt(request.getParameter("departmentId"));

		if (isValid( request, messages) == true) {


			User user = new User();
			user.setLoginId(request.getParameter("loginId"));
			user.setPassword(request.getParameter("password"));
			user.setName(request.getParameter("name"));
			user.setBranchId(branchId);
			user.setDepartmentId(departmentId);

			new UserService().register(user);

		response.sendRedirect("./home");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./signUp");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");

		if ( ! loginId.matches ("[a-zA-Z0-9]{6,20}")) {
			messages.add("半角英数字6文字以上で");
		}
		if ( ! password.matches("[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]{6,255}")){
			messages.add("半角英数字6文字以上255文字以内で");
		}
		if ( name.length() < 1){
			messages.add("1文字以上で");
		}


		if ( messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
