package bulletinBoard.filter;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

@WebFilter(urlPatterns = {"/controlUser", "/signUp", "/editUser"})

public class ValidFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
		try {
			HttpSession session = ((HttpServletRequest) request).getSession();
			List<String> messages = new ArrayList<String>();
			User user = (User) session.getAttribute("loginUser");
			if (user.getDepartmentId() == 1) {

				chain.doFilter(request, response);

			} else {
				messages.add("管理画面には入れません");
				session.setAttribute("controlErrorMessages", messages);
				((HttpServletResponse) response).sendRedirect("./");
				return;
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
