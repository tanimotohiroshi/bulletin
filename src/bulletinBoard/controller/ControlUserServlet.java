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

		/*ページの切り替えのたびにユーザー情報を更新*/
		HttpSession session = request.getSession();
		User user2 = (User) request.getSession().getAttribute("loginUser");
		int id = user2.getId();
		UserService userService = new UserService();
		User user1 = userService.getUserId(id);
		if ( user1 == null){
			request.getRequestDispatcher("login.jsp").forward(request, response);
			session.invalidate();
			return;
		} else {
			session.setAttribute("loginUser", user1);
		}

		/* ユーザーの一覧表示 */
		List<ControlUser> controlUser = new ControlUserService().getControlUser();
		User user = (User) request.getSession().getAttribute("loginUser");

		session.setAttribute("userList", controlUser);
		session.setAttribute("loginUser", user);
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