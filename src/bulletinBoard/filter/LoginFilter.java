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

@WebFilter(urlPatterns = {"/*"})

public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
		try {
			/*targetにrequestのURIがあるからログイン画面のURIとまっちするか*/
			String target = ((HttpServletRequest)request).getRequestURI();
			HttpSession session = ((HttpServletRequest) request).getSession();
			Object loginCheck = session.getAttribute("loginUser");

			if (loginCheck == null ){
				if (target.equals("/bulletinBoard/css/style.css") || target.equals("/bulletinBoard/login")){
					chain.doFilter(request, response);
				}else {
					((HttpServletResponse) response).sendRedirect("login");
					return;
				}
			} else {
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
