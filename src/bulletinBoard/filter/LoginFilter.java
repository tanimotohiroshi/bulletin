package bulletinBoard.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bulletinBoard.beans.User;
import bulletinBoard.service.UserService;

@WebFilter(urlPatterns = {"/*"})

public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
		try {
			/*targetにrequestのURIがあるからログイン画面のURIとまっちするか*/
			String target = ((HttpServletRequest)request).getRequestURI();
			HttpSession session = ((HttpServletRequest) request).getSession();
			User loginCheck = (User) session.getAttribute("loginUser");


			if ( loginCheck == null ){
				if (target.equals("/bulletinBoard/css/style.css") || target.equals("/bulletinBoard/login")){
					chain.doFilter(request, response);
				}else {
					((HttpServletResponse) response).sendRedirect("login");
					return;
				}
			} else {
				int id = loginCheck.getId();
				UserService userService = new UserService();
				User user = userService.getUserId(id);
				if ( user == null) {
					session.invalidate();
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}

				if ( user.getIsStopped() == 1) {
					session.invalidate();
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}

				chain.doFilter(request, response);
			}

		} catch (ServletException se) {
		} catch (IOException e) {
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
