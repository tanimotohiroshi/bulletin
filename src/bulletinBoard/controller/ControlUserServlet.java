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

import bulletinBoard.beans.ControlUser;
import bulletinBoard.beans.User;
import bulletinBoard.service.ControlUserService;
import bulletinBoard.service.UserService;

@WebServlet(urlPatterns = { "/controlUser" })

public class ControlUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		/* ユーザーの一覧表示 */
		List<ControlUser> controlUser = new ControlUserService().getControlUser();
		HttpSession session = request.getSession();

		session.setAttribute("usersList", controlUser);

		/* ここから総務以外を絞る部分 */
		List<String> messages = new ArrayList<String>();

		if (isValid(request, messages) == true) {

			request.getRequestDispatcher("controlUser.jsp").forward(request, response);

		} else {
			session.setAttribute("controlErrorMessages", messages);
			response.sendRedirect("./home");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");

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

		/* ユーザーの停止復活処理 */
		int id = Integer.parseInt(request.getParameter("id"));

		User userStopId = new User();

		if (request.getParameter("permitId") != null) {
			int permitId = Integer.parseInt(request.getParameter("permitId"));
			userStopId.setId(id);
			userStopId.setIsStopped(permitId);
		} else {
			int stopId = Integer.parseInt(request.getParameter("stopId"));
			userStopId.setId(id);
			userStopId.setIsStopped(stopId);
		}

		new UserService().stoppedId(userStopId);

		response.sendRedirect("./controlUser");

	}

}