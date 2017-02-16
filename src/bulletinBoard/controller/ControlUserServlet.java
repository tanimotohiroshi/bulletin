package bulletinBoard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

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
		HttpSession session = request.getSession();
		List<ControlUser> controlUser = new ControlUserService().getControlUser();


		session.setAttribute("userList", controlUser);
		request.getRequestDispatcher("controlUser.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		/*ユーザーの削除*/

		int id = Integer.parseInt(request.getParameter("id"));

		if ( StringUtils.isEmpty(request.getParameter("delete")) == false) {

			new UserService().deleteUser(id);

		}



		/* ユーザーの停止復活処理 */

		User userStopId = new User();

		if (request.getParameter("stopId") != null) {
			int permitId = Integer.parseInt(request.getParameter("stopId"));
			userStopId.setId(id);
			userStopId.setIsStopped(permitId);
			new UserService().stoppedId(userStopId);
		}

		if(request.getParameter("permitId") != null) {
			int stopId = Integer.parseInt(request.getParameter("permitId"));
			userStopId.setId(id);
			userStopId.setIsStopped(stopId);
			new UserService().stoppedId(userStopId);
		}



		response.sendRedirect("./controlUser");

	}

}