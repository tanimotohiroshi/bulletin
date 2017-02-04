package bulletinBoard.controller;

import java.io.IOException;
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
	private static final long serialversionUID = 1L;

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

//		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();


		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int departmentId = Integer.parseInt(request.getParameter("departmentId"));


		User user = new User();
		user.setLoginId(request.getParameter("loginId"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("name"));
		user.setBranchId(branchId);
		user.setDepartmentId(departmentId);



		System.out.println(user.getName());

		new UserService().register(user);

		response.sendRedirect("./signUp");
	}
}
